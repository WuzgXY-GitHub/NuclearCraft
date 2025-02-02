package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.machine.Machine;
import nc.tile.TileContainerInfo;
import nc.tile.machine.*;
import net.minecraft.util.math.BlockPos;

public class DistillerRenderPacket extends MachineRenderPacket {
	
	public boolean isProcessing;
	
	public DistillerRenderPacket() {
		super();
	}
	
	public DistillerRenderPacket(BlockPos pos, boolean isMachineOn, boolean isProcessing) {
		super(pos, isMachineOn);
		this.isProcessing = isProcessing;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isProcessing = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isProcessing);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<Machine, IMachinePart, MachineUpdatePacket, TileDistillerController, TileContainerInfo<TileDistillerController>, DistillerRenderPacket> {
		
		public Handler() {
			super(TileDistillerController.class);
		}
		
		@Override
		protected void onPacket(DistillerRenderPacket message, Machine multiblock) {
			multiblock.onRenderPacket(message);
		}
	}
}
