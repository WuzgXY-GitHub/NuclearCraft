package nc.tile.hx;

import com.google.common.collect.Lists;
import nc.ModCheck;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.hx.HeatExchanger;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.fluid.*;
import nc.util.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.*;
import java.util.List;

import static nc.block.property.BlockProperties.AXIS_ALL;
import static nc.config.NCConfig.*;

public class TileHeatExchangerVent extends TileHeatExchangerPart implements ITickable, ITileFluid {
	
	private final @Nonnull List<Tank> tanks = Lists.newArrayList(new Tank(128000, null));
	
	private @Nonnull FluidConnection[] fluidConnections = ITileFluid.fluidConnectionAll(TankSorption.BOTH);
	
	private final @Nonnull FluidTileWrapper[] fluidSides;
	private final @Nonnull GasTileWrapper gasWrapper;
	
	public TileHeatExchangerVent() {
		super(CuboidalPartPositionType.WALL);
		fluidSides = ITileFluid.getDefaultFluidSides(this);
		gasWrapper = new GasTileWrapper(this);
	}
	
	@Override
	public void onMachineAssembled(HeatExchanger multiblock) {
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
		if (!world.isRemote && !tanks.get(0).isEmpty()) {
			EnumFacing posFacing = getPartPosition().getFacing();
			if (posFacing != null) {
				for (EnumFacing side : PosHelper.getAxialDirs(posFacing)) {
					pushFluidToSide(side);
				}
			}
		}
	}
	
	// Fluids
	
	@Override
	@Nonnull
	public List<Tank> getTanks() {
		return tanks;
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
		if (!getTankSorption(side, 0).canDrain()) {
		}
		
		/*TileEntity tile = getTileWorld().getTileEntity(getTilePos().offset(side));
		if (tile instanceof TileHeatExchangerTube) {
			TileHeatExchangerTube tube = (TileHeatExchangerTube) tile;
			
			if (tube.getTubeSetting(side.getOpposite()) == HeatExchangerTubeSetting.DEFAULT) {
				getTanks().get(0).drain(tube.getTanks().get(0).fill(getTanks().get(0).drain(getTanks().get(0).getCapacity(), false), true), true);
			}
		}
		else if (tile instanceof TileCondenserTube) {
			TileCondenserTube condenserTube = (TileCondenserTube) tile;
			
			if (condenserTube.getTubeSetting(side.getOpposite()) == HeatExchangerTubeSetting.DEFAULT) {
				getTanks().get(0).drain(condenserTube.getTanks().get(0).fill(getTanks().get(0).drain(getTanks().get(0).getCapacity(), false), true), true);
			}
		}*/
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
		writeTanks(nbt);
		writeFluidConnections(nbt);
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		readTanks(nbt);
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
