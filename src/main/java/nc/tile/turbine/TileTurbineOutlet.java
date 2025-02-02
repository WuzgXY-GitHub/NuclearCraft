package nc.tile.turbine;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import nc.ModCheck;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.turbine.Turbine;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.fluid.*;
import nc.tile.passive.ITilePassive;
import nc.util.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.*;

import javax.annotation.*;
import java.util.List;

import static nc.block.property.BlockProperties.AXIS_ALL;
import static nc.config.NCConfig.*;

public class TileTurbineOutlet extends TileTurbinePart implements ITickable, ITileFluid {
	
	private final @Nonnull List<Tank> backupTanks = Lists.newArrayList(new Tank(1, new ObjectOpenHashSet<>()));
	
	private @Nonnull FluidConnection[] fluidConnections = ITileFluid.fluidConnectionAll(TankSorption.OUT);
	
	private @Nonnull final FluidTileWrapper[] fluidSides;
	private @Nonnull final GasTileWrapper gasWrapper;
	
	public TileTurbineOutlet() {
		super(CuboidalPartPositionType.WALL);
		fluidSides = ITileFluid.getDefaultFluidSides(this);
		gasWrapper = new GasTileWrapper(this);
	}
	
	@Override
	public void onMachineAssembled(Turbine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote) {
			EnumFacing posFacing = getPartPosition().getFacing();
			if (posFacing != null) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(AXIS_ALL, posFacing.getAxis()), 2);
			}
		}
	}
	
	@Override
	public void update() {
		if (!world.isRemote && !getTanks().get(0).isEmpty()) {
			EnumFacing posFacing = getPartPosition().getFacing();
			if (posFacing != null && getTankSorption(posFacing, 0).canDrain()) {
				pushFluidToSide(posFacing);
			}
		}
	}
	
	// Fluids
	
	@Override
	@Nonnull
	public List<Tank> getTanks() {
		if (!isMultiblockAssembled()) {
			return backupTanks;
		}
		return getMultiblock().tanks.subList(1, 2);
	}
	
	@Override
	@Nonnull
	public FluidConnection[] getFluidConnections() {
		return fluidConnections;
	}
	
	@Override
	public void setFluidConnections(@Nonnull FluidConnection[] connections) {
		fluidConnections = connections;
	}
	
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
	public void pushFluidToSide(@Nonnull EnumFacing side) {
		TileEntity tile = getTileWorld().getTileEntity(getTilePos().offset(side));
		if (tile == null || tile instanceof TileTurbineOutlet) {
			return;
		}
		
		if (tile instanceof ITilePassive tilePassive && !tilePassive.canPushFluidsTo()) {
			return;
		}
		
		IFluidHandler adjStorage = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
		if (adjStorage == null) {
			return;
		}
		
		Tank tank = getTanks().get(0);
		tank.drain(adjStorage.fill(tank.drain(tank.getCapacity(), false), true), true);
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
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		writeFluidConnections(nbt);
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		readFluidConnections(nbt);
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || (ModCheck.mekanismLoaded() && enable_mek_gas && capability == CapabilityHelper.GAS_HANDLER_CAPABILITY)) {
			return !getTanks().isEmpty() && hasFluidSideCapability(side);
		}
		return super.hasCapability(capability, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (!getTanks().isEmpty() && hasFluidSideCapability(side)) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getFluidSide(nonNullSide(side)));
			}
			return null;
		}
		else if (ModCheck.mekanismLoaded() && capability == CapabilityHelper.GAS_HANDLER_CAPABILITY) {
			if (enable_mek_gas && !getTanks().isEmpty() && hasFluidSideCapability(side)) {
				return CapabilityHelper.GAS_HANDLER_CAPABILITY.cast(getGasWrapper());
			}
			return null;
		}
		return super.getCapability(capability, side);
	}
}
