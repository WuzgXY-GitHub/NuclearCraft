package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.machine.Machine;
import nc.tile.TileContainerInfo;
import nc.tile.internal.fluid.Tank;
import nc.tile.internal.fluid.Tank.TankInfo;
import nc.tile.machine.*;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class InfiltratorRenderPacket extends MachineRenderPacket {
	
	public boolean isProcessing;
	public double time;
	public double baseProcessTime;
	public List<TankInfo> tankInfos;
	public List<TankInfo> reservoirTankInfos;
	
	public InfiltratorRenderPacket() {
		super();
	}
	
	public InfiltratorRenderPacket(BlockPos pos, boolean isMachineOn, boolean isProcessing, double time, double baseProcessTime, List<Tank> tanks, List<Tank> reservoirTanks) {
		super(pos, isMachineOn);
		this.isProcessing = isProcessing;
		this.time = time;
		this.baseProcessTime = baseProcessTime;
		tankInfos = TankInfo.getInfoList(tanks);
		reservoirTankInfos = TankInfo.getInfoList(reservoirTanks);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isProcessing = buf.readBoolean();
		time = buf.readDouble();
		baseProcessTime = buf.readDouble();
		tankInfos = readTankInfos(buf);
		reservoirTankInfos = readTankInfos(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isProcessing);
		buf.writeDouble(time);
		buf.writeDouble(baseProcessTime);
		writeTankInfos(buf, tankInfos);
		writeTankInfos(buf, reservoirTankInfos);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<Machine, IMachinePart, MachineUpdatePacket, TileInfiltratorController, TileContainerInfo<TileInfiltratorController>, InfiltratorRenderPacket> {
		
		public Handler() {
			super(TileInfiltratorController.class);
		}
		
		@Override
		protected void onPacket(InfiltratorRenderPacket message, Machine multiblock) {
			multiblock.onRenderPacket(message);
		}
	}
}
