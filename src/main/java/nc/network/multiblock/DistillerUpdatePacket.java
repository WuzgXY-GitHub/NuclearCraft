package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.machine.Machine;
import nc.recipe.RecipeUnitInfo;
import nc.tile.TileContainerInfo;
import nc.tile.internal.fluid.Tank;
import nc.tile.machine.*;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class DistillerUpdatePacket extends MachineUpdatePacket {
	
	public DistillerUpdatePacket() {
		super();
	}
	
	public DistillerUpdatePacket(BlockPos pos, boolean isMachineOn, boolean isProcessing, double time, double baseProcessTime, double baseProcessPower, List<Tank> tanks, double baseSpeedMultiplier, double basePowerMultiplier, RecipeUnitInfo recipeUnitInfo) {
		super(pos, isMachineOn, isProcessing, time, baseProcessTime, baseProcessPower, tanks, baseSpeedMultiplier, basePowerMultiplier, recipeUnitInfo);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<Machine, IMachinePart, MachineUpdatePacket, TileDistillerController, TileContainerInfo<TileDistillerController>, DistillerUpdatePacket> {
		
		public Handler() {
			super(TileDistillerController.class);
		}
		
		@Override
		protected void onPacket(DistillerUpdatePacket message, Machine multiblock) {
			multiblock.onMultiblockUpdatePacket(message);
		}
	}
}
