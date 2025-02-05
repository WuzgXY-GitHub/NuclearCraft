package nc.tile.machine;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import nc.*;
import nc.block.property.*;
import nc.config.NCConfig;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.*;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.fluid.*;
import nc.tile.internal.inventory.*;
import nc.tile.inventory.ITileInventory;
import nc.util.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.*;
import java.util.*;

import static nc.block.property.BlockProperties.AXIS_ALL;
import static nc.config.NCConfig.enable_mek_gas;

public class TileMachineProcessPort extends TileMachinePart implements ITickable, ITileInventory, ITileFluid {
	
	private final @Nonnull String inventoryName;
	
	private @Nonnull NonNullList<ItemStack> backupStacks = NonNullList.withSize(0, ItemStack.EMPTY);
	private @Nonnull List<Tank> backupTanks = Collections.emptyList();
	
	private @Nonnull InventoryConnection[] backupInventoryConnections = ITileInventory.inventoryConnectionAll(Collections.emptyList());
	private @Nonnull FluidConnection[] backupFluidConnections = ITileFluid.fluidConnectionAll(Collections.emptyList());
	
	private @Nonnull final FluidTileWrapper[] fluidSides;
	private @Nonnull final GasTileWrapper gasWrapper;
	
	private int setting = 0, slot = 0, tankIndex = -1;
	
	public TileMachineProcessPort() {
		super(CuboidalPartPositionType.WALL);
		inventoryName = Global.MOD_ID + ".container.machine_port";
		fluidSides = ITileFluid.getDefaultFluidSides(this);
		gasWrapper = new GasTileWrapper(this);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote) {
			EnumFacing facing = getPartPosition().getFacing();
			if (facing != null) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(AXIS_ALL, facing.getAxis()), 2);
			}
		}
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if (!isMultiblockAssembled()) {
				return;
			}
			
			MachineLogic logic = getLogic();
			if (logic == null) {
				return;
			}
			
			EnumFacing facing = getPartPosition().getFacing();
			if (facing != null) {
				if (slot >= 0 && slot < logic.inventorySize()) {
					if (!getStackInSlot(0).isEmpty() && getItemSorption(facing, 0).canExtract()) {
						pushStacksToSide(facing);
					}
				}
				else if (tankIndex >= 0 && tankIndex < logic.tankCount()) {
					if (!getTanks().get(0).isEmpty() && getTankSorption(facing, 0).canDrain()) {
						pushFluidToSide(facing);
					}
				}
			}
		}
	}
	
	public void setItemFluidData() {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return;
		}
		
		if (slot < 0) {
			backupStacks = NonNullList.withSize(0, ItemStack.EMPTY);
			backupTanks = Collections.singletonList(new Tank(1, new ObjectOpenHashSet<>()));
			backupInventoryConnections = ITileInventory.inventoryConnectionAll(Collections.emptyList());
			backupFluidConnections = ITileFluid.fluidConnectionAll(Collections.singletonList(tankIndex < logic.fluidInputSize ? TankSorption.IN : TankSorption.OUT));
		}
		else {
			backupStacks = NonNullList.withSize(1, ItemStack.EMPTY);
			backupTanks = Collections.emptyList();
			backupInventoryConnections = ITileInventory.inventoryConnectionAll(Collections.singletonList(slot < logic.itemInputSize ? ItemSorption.IN : ItemSorption.OUT));
			backupFluidConnections = ITileFluid.fluidConnectionAll(Collections.emptyList());
		}
		
		markDirtyAndNotify(true);
	}
	
	public void incrementItemFluidData(boolean reverse) {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return;
		}
		
		int sorptionSize = logic.inventorySize() + logic.tankCount();
		if (sorptionSize == 0) {
			setting = 0;
			slot = tankIndex = -1;
			return;
		}
		
		if (reverse) {
			--setting;
		}
		else {
			++setting;
		}
		setting = (sorptionSize + setting % sorptionSize) % sorptionSize;
		
		if (setting < logic.itemInputSize) {
			slot = setting;
			tankIndex = -1;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize) {
			slot = -1;
			tankIndex = setting - logic.itemInputSize;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize + logic.itemOutputSize) {
			slot = setting - logic.fluidInputSize;
			tankIndex = -1;
		}
		else {
			slot = -1;
			tankIndex = setting - logic.itemInputSize - logic.itemOutputSize;
		}
		
		setItemFluidData();
	}
	
	public @Nonnull MachinePortSorption getMachinePortSorption() {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return MachinePortSorption.ITEM_IN;
		}
		
		if (setting < logic.itemInputSize) {
			return MachinePortSorption.ITEM_IN;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize) {
			return MachinePortSorption.FLUID_IN;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize + logic.itemOutputSize) {
			return MachinePortSorption.ITEM_OUT;
		}
		else {
			return MachinePortSorption.FLUID_OUT;
		}
	}
	
	public int getMachinePortSorptionIndex() {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return 0;
		}
		
		if (setting < logic.itemInputSize) {
			return setting;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize) {
			return setting - logic.itemInputSize;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize + logic.itemOutputSize) {
			return setting - logic.itemInputSize - logic.fluidInputSize;
		}
		else {
			return setting - logic.itemInputSize - logic.fluidInputSize - logic.itemOutputSize;
		}
	}
	
	public int getMachinePortSorptionSize() {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return 0;
		}
		
		if (setting < logic.itemInputSize) {
			return logic.itemInputSize;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize) {
			return logic.fluidInputSize;
		}
		else if (setting < logic.itemInputSize + logic.fluidInputSize + logic.itemOutputSize) {
			return logic.itemOutputSize;
		}
		else {
			return logic.fluidOutputSize;
		}
	}
	
	public String getMachinePortSettingString() {
		String str = switch (getMachinePortSorption()) {
			case ITEM_IN -> TextFormatting.BLUE + Lang.localize("nc.block.port_mode.item_input");
			case FLUID_IN -> TextFormatting.DARK_AQUA + Lang.localize("nc.block.port_mode.fluid_input");
			case ITEM_OUT -> TextFormatting.GOLD + Lang.localize("nc.block.port_mode.item_output");
			case FLUID_OUT -> TextFormatting.RED + Lang.localize("nc.block.port_mode.fluid_output");
		};
		
		if (getMachinePortSorptionSize() > 1) {
			str = Lang.localize("nc.sf.ordinal" + (1 + getMachinePortSorptionIndex())) + " " + str;
		}
		
		return str;
	}
	
	// Inventory
	
	@Override
	public String getName() {
		return inventoryName;
	}
	
	@Override
	public @Nonnull NonNullList<ItemStack> getInventoryStacks() {
		MachineLogic logic = getLogic();
		return logic != null ? logic.getProcessPortInventoryStacks(backupStacks, slot) : backupStacks;
	}
	
	@Override
	public @Nonnull InventoryConnection[] getInventoryConnections() {
		MachineLogic logic = getLogic();
		return logic != null ? logic.getProcessPortInventoryConnections(backupInventoryConnections, slot) : backupInventoryConnections;
	}
	
	@Override
	public void setInventoryConnections(@Nonnull InventoryConnection[] connections) {}
	
	@Override
	public ItemOutputSetting getItemOutputSetting(int slot) {
		return ItemOutputSetting.DEFAULT;
	}
	
	@Override
	public void setItemOutputSetting(int slot, ItemOutputSetting setting) {}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = ITileInventory.super.decrStackSize(slot, amount);
		if (!getTileWorld().isRemote) {
			if (this.slot < 0) {
				return stack;
			}
			
			MachineLogic logic = getLogic();
			if (logic == null) {
				return stack;
			}
			
			if (this.slot < logic.itemInputSize) {
				logic.refreshRecipe();
				logic.refreshActivity();
			}
			else if (this.slot < logic.itemInputSize + logic.itemOutputSize) {
				logic.refreshActivity();
			}
		}
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		ITileInventory.super.setInventorySlotContents(slot, stack);
		if (!getTileWorld().isRemote) {
			if (this.slot < 0) {
				return;
			}
			
			MachineLogic logic = getLogic();
			if (logic == null) {
				return;
			}
			
			if (this.slot < logic.itemInputSize) {
				logic.refreshRecipe();
				logic.refreshActivity();
			}
			else if (this.slot < logic.itemInputSize + logic.itemOutputSize) {
				logic.refreshActivity();
			}
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (this.slot < 0) {
			return false;
		}
		
		MachineLogic logic = getLogic();
		if (logic == null) {
			return false;
		}
		
		if (stack.isEmpty() || (this.slot >= logic.itemInputSize && this.slot < logic.itemInputSize + logic.itemOutputSize)) {
			return false;
		}
		
		if (NCConfig.smart_processor_input) {
			return logic.recipeHandler.isValidItemInput(stack, this.slot, logic.getItemInputs(false), logic.getFluidInputs(false), logic.recipeInfo);
		}
		else {
			return logic.recipeHandler.isValidItemInput(stack, this.slot);
		}
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
		return ITileInventory.super.canInsertItem(slot, stack, side) && isItemValidForSlot(slot, stack);
	}
	
	@Override
	public void clearAllSlots() {
		ITileInventory.super.clearAllSlots();
		Collections.fill(backupStacks, ItemStack.EMPTY);
		
		MachineLogic logic = getLogic();
		if (logic != null) {
			logic.refreshAll();
		}
	}
	
	// Fluids
	
	@Override
	public @Nonnull List<Tank> getTanks() {
		MachineLogic logic = getLogic();
		return logic != null ? logic.getProcessPortTanks(backupTanks, tankIndex) : backupTanks;
	}
	
	@Override
	@Nonnull
	public FluidConnection[] getFluidConnections() {
		MachineLogic logic = getLogic();
		return logic != null ? logic.getProcessPortFluidConnections(backupFluidConnections, tankIndex) : backupFluidConnections;
	}
	
	@Override
	public void setFluidConnections(@Nonnull FluidConnection[] connections) {}
	
	@Override
	@Nonnull
	public FluidTileWrapper[] getFluidSides() {
		return fluidSides;
	}
	
	@Override
	public @Nonnull GasTileWrapper getGasWrapper() {
		return gasWrapper;
	}
	
	@Override
	public boolean getInputTanksSeparated() {
		return false;
	}
	
	@Override
	public void setInputTanksSeparated(boolean separated) {}
	
	@Override
	public boolean getVoidUnusableFluidInput(int tankNumber) {
		return false;
	}
	
	@Override
	public void setVoidUnusableFluidInput(int tankNumber, boolean voidUnusableFluidInput) {}
	
	@Override
	public TankOutputSetting getTankOutputSetting(int tankNumber) {
		return TankOutputSetting.DEFAULT;
	}
	
	@Override
	public void setTankOutputSetting(int tankNumber, TankOutputSetting setting) {}
	
	@Override
	public boolean hasConfigurableFluidConnections() {
		return true;
	}
	
	@Override
	public boolean isFluidValidForTank(int tankNumber, FluidStack stack) {
		MachineLogic logic = getLogic();
		if (logic == null) {
			return false;
		}
		
		if (stack == null || stack.amount <= 0 || (this.tankIndex >= logic.fluidInputSize && this.tankIndex < logic.fluidInputSize + logic.fluidOutputSize)) {
			return false;
		}
		
		if (NCConfig.smart_processor_input) {
			return logic.recipeHandler.isValidFluidInput(stack, this.tankIndex, logic.getFluidInputs(false), logic.getItemInputs(false), logic.recipeInfo);
		}
		else {
			return logic.recipeHandler.isValidFluidInput(stack, this.tankIndex);
		}
	}
	
	@Override
	public void clearAllTanks() {
		ITileFluid.super.clearAllTanks();
		for (Tank tank : backupTanks) {
			tank.setFluidStored(null);
		}
		
		MachineLogic logic = getLogic();
		if (logic != null) {
			logic.refreshAll();
		}
	}
	
	// IMultitoolLogic
	
	@Override
	public boolean onUseMultitool(ItemStack multitool, EntityPlayerMP player, World worldIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (getMultiblock() != null) {
			incrementItemFluidData(player.isSneaking());
			setProperty(BlockProperties.MACHINE_PORT_SORPTION, getMachinePortSorption());
			player.sendMessage(new TextComponentString(Lang.localize("nc.block.port_toggle") + " " + getMachinePortSettingString() + " " + TextFormatting.WHITE + Lang.localize("nc.block.port_toggle.mode")));
			markDirtyAndNotify(true);
			return true;
		}
		return super.onUseMultitool(multitool, player, worldIn, facing, hitX, hitY, hitZ);
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		nbt.setInteger("setting", setting);
		nbt.setInteger("slot", slot);
		nbt.setInteger("tankIndex", tankIndex);
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		setting = nbt.getInteger("setting");
		slot = nbt.getInteger("slot");
		tankIndex = nbt.getInteger("tankIndex");
		setItemFluidData();
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return slot >= 0 && hasInventorySideCapability(side);
		}
		else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || (ModCheck.mekanismLoaded() && enable_mek_gas && capability == CapabilityHelper.GAS_HANDLER_CAPABILITY)) {
			return tankIndex >= 0 && hasFluidSideCapability(side);
		}
		return super.hasCapability(capability, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (slot >= 0 && hasInventorySideCapability(side)) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getItemHandler(side));
			}
			return null;
		}
		else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (tankIndex >= 0 && hasFluidSideCapability(side)) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getFluidSide(nonNullSide(side)));
			}
			return null;
		}
		else if (ModCheck.mekanismLoaded() && capability == CapabilityHelper.GAS_HANDLER_CAPABILITY) {
			if (enable_mek_gas && tankIndex >= 0 && hasFluidSideCapability(side)) {
				return CapabilityHelper.GAS_HANDLER_CAPABILITY.cast(getGasWrapper());
			}
			return null;
		}
		return super.getCapability(capability, side);
	}
}
