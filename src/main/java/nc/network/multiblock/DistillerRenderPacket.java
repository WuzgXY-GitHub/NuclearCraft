package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntList;
import nc.multiblock.machine.Machine;
import nc.tile.TileContainerInfo;
import nc.tile.internal.fluid.Tank;
import nc.tile.internal.fluid.Tank.TankInfo;
import nc.tile.machine.*;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class DistillerRenderPacket extends MachineRenderPacket {
	
	public boolean isProcessing;
	public List<TankInfo> tankInfos;
	public IntList trayLevels;
	
	public DistillerRenderPacket() {
		super();
	}
	
	public DistillerRenderPacket(BlockPos pos, boolean isMachineOn, boolean isProcessing, List<Tank> tanks, IntList trayLevels) {
		super(pos, isMachineOn);
		this.isProcessing = isProcessing;
		tankInfos = TankInfo.getInfoList(tanks);
		this.trayLevels = trayLevels;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isProcessing = buf.readBoolean();
		tankInfos = readTankInfos(buf);
		trayLevels = readInts(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isProcessing);
		writeTankInfos(buf, tankInfos);
		writeInts(buf, trayLevels);
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
