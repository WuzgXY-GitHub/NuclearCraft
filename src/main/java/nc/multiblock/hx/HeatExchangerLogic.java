package nc.multiblock.hx;

import com.google.common.collect.Lists;
import nc.Global;
import nc.multiblock.*;
import nc.network.multiblock.HeatExchangerUpdatePacket;
import nc.tile.hx.*;
import nc.tile.multiblock.TilePartAbstract.SyncReason;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Stream;

import static nc.config.NCConfig.*;

public class HeatExchangerLogic extends MultiblockLogic<HeatExchanger, HeatExchangerLogic, IHeatExchangerPart> implements IPacketMultiblockLogic<HeatExchanger, HeatExchangerLogic, IHeatExchangerPart, HeatExchangerUpdatePacket> {
	
	public HeatExchangerLogic(HeatExchanger exchanger) {
		super(exchanger);
	}
	
	public HeatExchangerLogic(HeatExchangerLogic oldLogic) {
		super(oldLogic);
	}
	
	@Override
	public String getID() {
		return "heat_exchanger";
	}
	
	// Multiblock Size Limits
	
	@Override
	public int getMinimumInteriorLength() {
		return heat_exchanger_min_size;
	}
	
	@Override
	public int getMaximumInteriorLength() {
		return heat_exchanger_max_size;
	}
	
	// Multiblock Methods
	
	@Override
	public void onMachineAssembled() {
		onExchangerFormed();
	}
	
	@Override
	public void onMachineRestored() {
		onExchangerFormed();
	}
	
	protected void onExchangerFormed() {
		for (IHeatExchangerController<?> contr : getParts(IHeatExchangerController.class)) {
			multiblock.controller = contr;
			break;
		}
		
		if (!getWorld().isRemote) {
			setupExchanger();
			refreshAll();
			setIsExchangerOn();
		}
	}
	
	// TODO
	protected void setupExchanger() {
	
	}
	
	@Override
	public void onMachinePaused() {
		onExchangerBroken();
	}
	
	@Override
	public void onMachineDisassembled() {
		onExchangerBroken();
	}
	
	public void onExchangerBroken() {
		setIsExchangerOn();
	}
	
	@Override
	public boolean isMachineWhole() {
		return !containsBlacklistedPart();
	}
	
	public static final List<Pair<Class<? extends IHeatExchangerPart>, String>> EXCHANGER_PART_BLACKLIST = Lists.newArrayList(Pair.of(TileCondenserTube.class, Global.MOD_ID + ".multiblock_validation.heat_exchanger.prohibit_condenser_tubes"));
	
	@Override
	public List<Pair<Class<? extends IHeatExchangerPart>, String>> getPartBlacklist() {
		return EXCHANGER_PART_BLACKLIST;
	}
	
	@Override
	public void onAssimilate(HeatExchanger assimilated) {
		/*if (getExchanger().isAssembled()) {
			onExchangerFormed();
		}
		else {
			onExchangerBroken();
		}*/
	}
	
	@Override
	public void onAssimilated(HeatExchanger assimilator) {}
	
	// Server
	
	@Override
	public boolean onUpdateServer() {
		boolean flag = true;
		if (multiblock.controller != null) {
			multiblock.sendMultiblockUpdatePacketToListeners();
		}
		return flag;
	}
	
	public void setActivity(boolean isExchangerOn) {
		multiblock.controller.setActivity(isExchangerOn);
	}
	
	public void setIsExchangerOn() {
		boolean oldIsExchangerOn = multiblock.isExchangerOn;
		multiblock.isExchangerOn = (isRedstonePowered() || multiblock.computerActivated) && multiblock.isAssembled();
		if (multiblock.isExchangerOn != oldIsExchangerOn) {
			if (multiblock.controller != null) {
				setActivity(multiblock.isExchangerOn);
				multiblock.sendMultiblockUpdatePacketToAll();
			}
		}
	}
	
	protected boolean isRedstonePowered() {
		return Stream.concat(Stream.of(multiblock.controller), getParts(TileHeatExchangerRedstonePort.class).stream()).anyMatch(x -> x != null && x.checkIsRedstonePowered(getWorld(), x.getTilePos()));
	}
	
	// TODO
	public void refreshAll() {
	
	}
	
	// Client
	
	@Override
	public void onUpdateClient() {}
	
	// NBT
	
	@Override
	public void writeToLogicTag(NBTTagCompound data, SyncReason syncReason) {
	
	}
	
	@Override
	public void readFromLogicTag(NBTTagCompound data, SyncReason syncReason) {
	
	}
	
	// Packets
	
	@Override
	public HeatExchangerUpdatePacket getMultiblockUpdatePacket() {
		return new HeatExchangerUpdatePacket(multiblock.controller.getTilePos(), multiblock.isExchangerOn);
	}
	
	@Override
	public void onMultiblockUpdatePacket(HeatExchangerUpdatePacket message) {
		multiblock.isExchangerOn = message.isExchangerOn;
	}
	
	// Multiblock Validators
	
	@Override
	public boolean isBlockGoodForInterior(World world, BlockPos pos) {
		return true;
	}
	
	// Clear Material
	
	@Override
	public void clearAllMaterial() {}
}
