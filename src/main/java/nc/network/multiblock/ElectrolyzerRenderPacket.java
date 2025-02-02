package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.machine.Machine;
import nc.tile.TileContainerInfo;
import nc.tile.internal.fluid.Tank;
import nc.tile.internal.fluid.Tank.TankInfo;
import nc.tile.machine.*;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ElectrolyzerRenderPacket extends MachineRenderPacket {
	
	public boolean isProcessing;
	public List<TankInfo> reservoirTankInfos;
	
	public ElectrolyzerRenderPacket() {
		super();
	}
	
	public ElectrolyzerRenderPacket(BlockPos pos, boolean isMachineOn, boolean isProcessing, List<Tank> reservoirTanks) {
		super(pos, isMachineOn);
		this.isProcessing = isProcessing;
		reservoirTankInfos = TankInfo.getInfoList(reservoirTanks);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isProcessing = buf.readBoolean();
		reservoirTankInfos = readTankInfos(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isProcessing);
		writeTankInfos(buf, reservoirTankInfos);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<Machine, IMachinePart, MachineUpdatePacket, TileElectrolyzerController, TileContainerInfo<TileElectrolyzerController>, ElectrolyzerRenderPacket> {
		
		public Handler() {
			super(TileElectrolyzerController.class);
		}
		
		@Override
		protected void onPacket(ElectrolyzerRenderPacket message, Machine multiblock) {
			multiblock.onRenderPacket(message);
		}
	}
}
