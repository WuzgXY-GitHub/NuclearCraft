package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;

public class MachineRenderPacket extends MultiblockUpdatePacket {
	
	public boolean isMachineOn;
	
	public MachineRenderPacket() {
		super();
	}
	
	public MachineRenderPacket(BlockPos pos, boolean isMachineOn) {
		super(pos);
		this.isMachineOn = isMachineOn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		isMachineOn = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(isMachineOn);
	}
}
