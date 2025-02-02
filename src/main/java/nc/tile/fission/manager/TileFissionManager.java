package nc.tile.fission.manager;

import it.unimi.dsi.fastutil.longs.*;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.fission.FissionReactor;
import nc.tile.fission.TileFissionPart;
import nc.util.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

import static nc.block.property.BlockProperties.FACING_ALL;

public abstract class TileFissionManager<MANAGER extends TileFissionManager<MANAGER, LISTENER>, LISTENER extends IFissionManagerListener<MANAGER, LISTENER>> extends TileFissionPart implements ITickable, IFissionManager<MANAGER, LISTENER> {
	
	protected final Class<MANAGER> managerClass;
	protected LongSet listenerPosSet = new LongOpenHashSet();
	public boolean refreshListenersFlag = false;
	
	public TileFissionManager(Class<MANAGER> managerClass) {
		super(CuboidalPartPositionType.WALL);
		this.managerClass = managerClass;
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
	public LongSet getListenerPosSet() {
		return listenerPosSet;
	}
	
	@Override
	public boolean getRefreshListenersFlag() {
		return refreshListenersFlag;
	}
	
	@Override
	public void setRefreshListenersFlag(boolean flag) {
		refreshListenersFlag = flag;
	}
	
	// Ticking
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if (refreshListenersFlag) {
				refreshListeners();
			}
		}
	}
	
	@Override
	public void markDirty() {
		refreshListenersFlag = true;
		super.markDirty();
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		NBTHelper.writeLongCollection(nbt, listenerPosSet, "listenerPosSet");
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		NBTHelper.readLongCollection(nbt, listenerPosSet, "listenerPosSet");
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		return super.hasCapability(capability, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		return super.getCapability(capability, side);
	}
}
