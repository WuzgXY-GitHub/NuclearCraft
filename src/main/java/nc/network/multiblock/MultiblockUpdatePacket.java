package nc.network.multiblock;

import io.netty.buffer.ByteBuf;
import nc.multiblock.*;
import nc.network.NCPacket;
import nc.tile.TileContainerInfo;
import nc.tile.multiblock.*;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;

public abstract class MultiblockUpdatePacket extends NCPacket {
	
	protected BlockPos pos;
	
	public MultiblockUpdatePacket() {
		super();
	}
	
	public MultiblockUpdatePacket(BlockPos pos) {
		super();
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		pos = readPos(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		writePos(buf, pos);
	}
	
	public static abstract class Handler<MULTIBLOCK extends Multiblock<MULTIBLOCK, T> & IPacketMultiblock<MULTIBLOCK, T, PACKET>, T extends ITileMultiblockPart<MULTIBLOCK, T>, PACKET extends MultiblockUpdatePacket, CONTROLLER extends TileEntity & IMultiblockController<MULTIBLOCK, T, PACKET, CONTROLLER, INFO>, INFO extends TileContainerInfo<CONTROLLER>, MESSAGE extends MultiblockUpdatePacket> implements IMessageHandler<MESSAGE, IMessage> {
		
		protected final Class<CONTROLLER> controllerClass;
		
		public Handler(Class<CONTROLLER> controllerClass) {
			this.controllerClass = controllerClass;
		}
		
		@Override
		public IMessage onMessage(MESSAGE message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft.getMinecraft().addScheduledTask(() -> {
					TileEntity tile = Minecraft.getMinecraft().player.world.getTileEntity(message.pos);
					if (controllerClass.isInstance(tile)) {
						MULTIBLOCK multiblock = controllerClass.cast(tile).getMultiblock();
						if (multiblock != null) {
							onPacket(message, multiblock);
						}
					}
				});
			}
			return null;
		}
		
		protected abstract void onPacket(MESSAGE message, MULTIBLOCK multiblock);
	}
}
