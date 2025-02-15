package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.hx.HeatExchanger;
import nc.tile.hx.*;
import nc.tile.TileContainerInfo;
import net.minecraft.util.math.BlockPos;

public class HeatExchangerUpdatePacket extends MultiblockUpdatePacket {
	
	public boolean isExchangerOn;
	
	public HeatExchangerUpdatePacket() {
		super();
	}
	
	public HeatExchangerUpdatePacket(BlockPos pos, boolean isExchangerOn) {
		super(pos);
		this.isExchangerOn = isExchangerOn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isExchangerOn = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isExchangerOn);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<HeatExchanger, IHeatExchangerPart, HeatExchangerUpdatePacket, TileHeatExchangerController, TileContainerInfo<TileHeatExchangerController>, HeatExchangerUpdatePacket> {
		
		public Handler() {
			super(TileHeatExchangerController.class);
		}
		
		@Override
		protected void onPacket(HeatExchangerUpdatePacket message, HeatExchanger multiblock) {
			multiblock.onMultiblockUpdatePacket(message);
		}
	}
}
