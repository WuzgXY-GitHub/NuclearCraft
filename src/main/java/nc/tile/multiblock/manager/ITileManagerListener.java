package nc.tile.multiblock.manager;

import nc.multiblock.*;
import nc.tile.multiblock.ITileLogicMultiblockPart;
import nc.util.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static nc.util.PosHelper.DEFAULT_NON;

public interface ITileManagerListener<MULTIBLOCK extends Multiblock<MULTIBLOCK, T> & ILogicMultiblock<MULTIBLOCK, LOGIC, T>, LOGIC extends MultiblockLogic<MULTIBLOCK, LOGIC, T>, T extends ITileLogicMultiblockPart<MULTIBLOCK, LOGIC, T>, MANAGER extends ITileManager<MULTIBLOCK, LOGIC, T, MANAGER, LISTENER>, LISTENER extends ITileManagerListener<MULTIBLOCK, LOGIC, T, MANAGER, LISTENER>> extends ITileLogicMultiblockPart<MULTIBLOCK, LOGIC, T> {
	
	BlockPos getManagerPos();
	
	void setManagerPos(BlockPos pos);
	
	MANAGER getManager();
	
	void setManager(MANAGER manager);
	
	default void clearManager() {
		setManagerPos(DEFAULT_NON);
		setManager(null);
	}
	
	@SuppressWarnings("unchecked")
	default void refreshManager() {
		MULTIBLOCK multiblock = getMultiblock();
		MANAGER manager = multiblock == null ? null : (MANAGER) multiblock.getPartMap(getManagerClass()).get(getManagerPos().toLong());
		setManager(manager);
		if (manager == null) {
			setManagerPos(DEFAULT_NON);
		}
	}
	
	boolean onManagerRefresh(MANAGER manager);
	
	void refreshMultiblock();
	
	String getManagerType();
	
	Class<? extends T> getManagerClass();
	
	@Override
	default boolean onUseMultitool(ItemStack multitool, EntityPlayerMP player, World world, EnumFacing facing, float hitX, float hitY, float hitZ) {
		NBTTagCompound nbt = NBTHelper.getStackNBT(multitool, "ncMultitool");
		if (nbt != null) {
			if (player.isSneaking()) {
				if (nbt.hasKey("componentManagerInfo", 10)) {
					NBTTagCompound info = nbt.getCompoundTag("componentManagerInfo");
					if (info.getString("managerType").equals(getManagerType())) {
						int listenerCount = info.getInteger("listenerCount");
						info.setLong("listenerPos" + listenerCount, getTilePos().toLong());
						info.setInteger("listenerCount", listenerCount + 1);
						player.sendMessage(new TextComponentString(Lang.localize("info.nuclearcraft.multitool.append_manager_listener_set", getTileBlockDisplayName())));
						return true;
					}
				}
			}
		}
		return ITileLogicMultiblockPart.super.onUseMultitool(multitool, player, world, facing, hitX, hitY, hitZ);
	}
}
