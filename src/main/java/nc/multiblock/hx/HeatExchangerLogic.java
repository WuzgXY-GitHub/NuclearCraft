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
			updateHeatExchangerStats();
			setIsHeatExchangerOn();
		}
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
		multiblock.isHeatExchangerOn = false;
		if (multiblock.controller != null) {
			multiblock.controller.setActivity(false);
		}
		multiblock.fractionOfTubesActive = multiblock.efficiency = multiblock.maxEfficiency = 0D;
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
		updateHeatExchangerStats();
		if (multiblock.controller != null) {
			multiblock.sendMultiblockUpdatePacketToListeners();
		}
		return flag;
	}
	
	public void setIsHeatExchangerOn() {
		boolean oldIsHeatExchangerOn = multiblock.isHeatExchangerOn;
		multiblock.isHeatExchangerOn = (isRedstonePowered() || multiblock.computerActivated) && multiblock.isAssembled();
		if (multiblock.isHeatExchangerOn != oldIsHeatExchangerOn) {
			if (multiblock.controller != null) {
				multiblock.controller.setActivity(multiblock.isHeatExchangerOn);
				multiblock.sendMultiblockUpdatePacketToAll();
			}
		}
	}
	
	protected boolean isRedstonePowered() {
		return Stream.concat(Stream.of(multiblock.controller), getParts(TileHeatExchangerRedstonePort.class).stream()).anyMatch(x -> x != null && x.checkIsRedstonePowered(getWorld(), x.getTilePos()));
	}
	
	protected void updateHeatExchangerStats() {
		int totalTubeCount = getPartCount(TileHeatExchangerTube.class) + getPartCount(TileCondenserTube.class);
		if (totalTubeCount < 1) {
			multiblock.fractionOfTubesActive = multiblock.efficiency = multiblock.maxEfficiency = 0D;
			return;
		}
		int activeCount = 0, efficiencyCount = 0, maxEfficiencyCount = 0;
		
		/*for (TileHeatExchangerTube tube : tubes) {
			int[] eff = tube.checkPosition();
			if (eff[0] > 0) {
				++activeCount;
			}
			efficiencyCount += eff[0];
			maxEfficiencyCount += eff[1];
		}
		
		for (TileCondenserTube condenserTube : condenserTubes) {
			int eff = condenserTube.checkPosition();
			if (eff > 0) {
				++activeCount;
			}
			efficiencyCount += eff;
			maxEfficiencyCount += eff;
		}*/
		
		multiblock.fractionOfTubesActive = (double) activeCount / totalTubeCount;
		multiblock.efficiency = activeCount == 0 ? 0D : (double) efficiencyCount / activeCount;
		multiblock.maxEfficiency = (double) maxEfficiencyCount / totalTubeCount;
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
		return new HeatExchangerUpdatePacket(multiblock.controller.getTilePos(), multiblock.isHeatExchangerOn, multiblock.fractionOfTubesActive, multiblock.efficiency, multiblock.maxEfficiency);
	}
	
	@Override
	public void onMultiblockUpdatePacket(HeatExchangerUpdatePacket message) {
		multiblock.isHeatExchangerOn = message.isHeatExchangerOn;
		multiblock.fractionOfTubesActive = message.fractionOfTubesActive;
		multiblock.efficiency = message.efficiency;
		multiblock.maxEfficiency = message.maxEfficiency;
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
