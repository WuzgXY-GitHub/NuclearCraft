package nc.multiblock.machine;

import nc.config.NCConfig;
import nc.handler.SoundHandler;
import nc.network.multiblock.*;
import nc.recipe.*;
import nc.tile.multiblock.TilePartAbstract.SyncReason;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraftforge.fml.relauncher.*;

public class DistillerLogic extends MachineLogic {
	
	public DistillerLogic(Machine machine) {
		super(machine);
	}
	
	public DistillerLogic(MachineLogic oldLogic) {
		super(oldLogic);
	}
	
	@Override
	public String getID() {
		return "distiller";
	}
	
	@Override
	public BasicRecipeHandler getRecipeHandler() {
		return NCRecipes.multiblock_distiller;
	}
	
	@Override
	public double defaultProcessTime() {
		return NCConfig.machine_distiller_time;
	}
	
	@Override
	public double defaultProcessPower() {
		return NCConfig.machine_distiller_power;
	}
	
	@Override
	public void onMachineBroken() {
		super.onMachineBroken();
		
		if (getWorld().isRemote) {
			clearSounds();
		}
	}
	
	@Override
	public boolean isMachineWhole() {
		if (!super.isMachineWhole() || !multiblock.hasAxialSymmetry(Axis.Y)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onAssimilated(Machine assimilator) {
		super.onAssimilated(assimilator);
		
		if (getWorld().isRemote) {
			clearSounds();
		}
	}
	
	// Server
	
	// Client
	
	@Override
	public void onUpdateClient() {
		super.onUpdateClient();
		
		updateSounds();
	}
	
	@SideOnly(Side.CLIENT)
	protected void updateSounds() {
		/*if (machine_electrolyzer_sound_volume == 0D) {
			clearSounds();
			return;
		}
		
		if (isProcessing && multiblock.isAssembled()) {
		
		}
		else {
			multiblock.refreshSounds = true;
			clearSounds();
		}*/
	}
	
	@SideOnly(Side.CLIENT)
	protected void clearSounds() {
		multiblock.soundMap.forEach((k, v) -> SoundHandler.stopBlockSound(k));
		multiblock.soundMap.clear();
	}
	
	// NBT
	
	@Override
	public void writeToLogicTag(NBTTagCompound logicTag, SyncReason syncReason) {
		super.writeToLogicTag(logicTag, syncReason);
	}
	
	@Override
	public void readFromLogicTag(NBTTagCompound logicTag, SyncReason syncReason) {
		super.readFromLogicTag(logicTag, syncReason);
	}
	
	// Packets
	
	@Override
	public MachineUpdatePacket getMultiblockUpdatePacket() {
		return new DistillerUpdatePacket(multiblock.controller.getTilePos(), multiblock.isMachineOn, isProcessing, time, baseProcessTime, baseProcessPower, tanks, baseSpeedMultiplier, basePowerMultiplier);
	}
	
	@Override
	public void onMultiblockUpdatePacket(MachineUpdatePacket message) {
		super.onMultiblockUpdatePacket(message);
		if (message instanceof DistillerUpdatePacket packet) {
		
		}
	}
	
	@Override
	public DistillerRenderPacket getRenderPacket() {
		return new DistillerRenderPacket(multiblock.controller.getTilePos(), multiblock.isMachineOn, isProcessing);
	}
	
	@Override
	public void onRenderPacket(MachineRenderPacket message) {
		super.onRenderPacket(message);
		if (message instanceof DistillerRenderPacket packet) {
			boolean wasProcessing = isProcessing;
			isProcessing = packet.isProcessing;
			if (wasProcessing != isProcessing) {
				multiblock.refreshSounds = true;
			}
		}
	}
}
