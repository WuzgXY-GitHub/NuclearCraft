package nc.tile.fission;

import gregtech.api.capability.GregtechCapabilities;
import ic2.api.energy.tile.*;
import nc.ModCheck;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.fission.*;
import nc.tile.energy.ITileEnergy;
import nc.tile.internal.energy.*;
import nc.util.Lang;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.*;

import static nc.block.property.BlockProperties.FACING_ALL;
import static nc.config.NCConfig.enable_gtce_eu;

@Optional.InterfaceList({@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "ic2"), @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "ic2")})
public class TileFissionPowerPort extends TileFissionPart implements ITickable, ITileEnergy, IEnergySink, IEnergySource {
	
	protected final EnergyStorage backupStorage = new EnergyStorage(0L);
	
	protected final EnergyConnection[] energyConnections = ITileEnergy.energyConnectionAll(EnergyConnection.IN);
	
	protected final EnergyTileWrapper[] energySides = ITileEnergy.getDefaultEnergySides(this);
	protected final EnergyTileWrapperGT[] energySidesGT = ITileEnergy.getDefaultEnergySidesGT(this);
	
	protected boolean ic2reg = false;
	
	public TileFissionPowerPort() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public void onMachineAssembled(FissionReactor multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote) {
			EnumFacing facing = getPartPosition().getFacing();
			if (facing != null) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING_ALL, facing), 2);
			}
		}
	}
	
	@Override
	public void update() {
		if (!world.isRemote && getEnergyStored() > 0) {
			EnumFacing facing = getPartPosition().getFacing();
			if (facing != null && getEnergyConnection(facing).canExtract()) {
				pushEnergyToSide(facing);
			}
		}
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		if (ModCheck.ic2Loaded()) {
			addTileToENet();
		}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		if (ModCheck.ic2Loaded()) {
			removeTileFromENet();
		}
	}
	
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		if (ModCheck.ic2Loaded()) {
			removeTileFromENet();
		}
	}
	
	@Override
	public EnergyStorage getEnergyStorage() {
		FissionReactorLogic logic = getLogic();
		return logic != null ? logic.getPowerPortEnergyStorage(backupStorage) : backupStorage;
	}
	
	@Override
	public EnergyConnection[] getEnergyConnections() {
		return energyConnections;
	}
	
	@Override
	public @Nonnull EnergyTileWrapper[] getEnergySides() {
		return energySides;
	}
	
	@Override
	public @Nonnull EnergyTileWrapperGT[] getEnergySidesGT() {
		return energySidesGT;
	}
	
	// IC2 Energy
	
	@Override
	public boolean getIC2Reg() {
		return ic2reg;
	}
	
	@Override
	public void setIC2Reg(boolean ic2reg) {
		this.ic2reg = ic2reg;
	}
	
	@Override
	public int getSinkTier() {
		FissionReactorLogic logic = getLogic();
		return logic != null ? logic.getPowerPortEUSinkTier() : 10;
	}
	
	@Override
	public int getSourceTier() {
		FissionReactorLogic logic = getLogic();
		return logic != null ? logic.getPowerPortEUSourceTier() : 1;
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return ITileEnergy.super.acceptsEnergyFrom(emitter, side);
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public double getDemandedEnergy() {
		return ITileEnergy.super.getDemandedEnergy();
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		return ITileEnergy.super.injectEnergy(directionFrom, amount, voltage);
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return ITileEnergy.super.emitsEnergyTo(receiver, side);
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public double getOfferedEnergy() {
		return ITileEnergy.super.getOfferedEnergy();
	}
	
	@Override
	@Optional.Method(modid = "ic2")
	public void drawEnergy(double amount) {
		ITileEnergy.super.drawEnergy(amount);
	}
	
	@Override
	public boolean hasConfigurableEnergyConnections() {
		return true;
	}
	
	// IMultitoolLogic
	
	@Override
	public boolean onUseMultitool(ItemStack multitool, EntityPlayerMP player, World worldIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking()) {
		
		}
		else {
			if (getMultiblock() != null) {
				if (getEnergyConnection(facing) != EnergyConnection.IN) {
					for (EnumFacing side : EnumFacing.VALUES) {
						setEnergyConnection(EnergyConnection.IN, side);
					}
					setActivity(false);
					player.sendMessage(new TextComponentString(Lang.localize("nc.block.port_toggle") + " " + TextFormatting.BLUE + Lang.localize("nc.block.port_mode.input") + " " + TextFormatting.WHITE + Lang.localize("nc.block.port_toggle.mode")));
				}
				else {
					for (EnumFacing side : EnumFacing.VALUES) {
						setEnergyConnection(EnergyConnection.OUT, side);
					}
					setActivity(true);
					player.sendMessage(new TextComponentString(Lang.localize("nc.block.port_toggle") + " " + TextFormatting.RED + Lang.localize("nc.block.port_mode.output") + " " + TextFormatting.WHITE + Lang.localize("nc.block.port_toggle.mode")));
				}
				markDirtyAndNotify(true);
				return true;
			}
		}
		return super.onUseMultitool(multitool, player, worldIn, facing, hitX, hitY, hitZ);
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		writeEnergyConnections(nbt);
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		readEnergyConnections(nbt);
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityEnergy.ENERGY || (ModCheck.gregtechLoaded() && enable_gtce_eu && capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER)) {
			return hasEnergySideCapability(side);
		}
		return super.hasCapability(capability, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityEnergy.ENERGY) {
			if (hasEnergySideCapability(side)) {
				return CapabilityEnergy.ENERGY.cast(getEnergySide(nonNullSide(side)));
			}
			return null;
		}
		else if (ModCheck.gregtechLoaded() && capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
			if (enable_gtce_eu && hasEnergySideCapability(side)) {
				return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(getEnergySideGT(nonNullSide(side)));
			}
			return null;
		}
		return super.getCapability(capability, side);
	}
}
