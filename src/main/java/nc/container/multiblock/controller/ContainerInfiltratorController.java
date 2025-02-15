package nc.container.multiblock.controller;

import nc.multiblock.machine.Machine;
import nc.network.multiblock.MachineUpdatePacket;
import nc.tile.TileContainerInfo;
import nc.tile.machine.*;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerInfiltratorController extends ContainerMultiblockController<Machine, IMachinePart, MachineUpdatePacket, TileInfiltratorController, TileContainerInfo<TileInfiltratorController>> {
	
	public ContainerInfiltratorController(EntityPlayer player, TileInfiltratorController controller) {
		super(player, controller);
	}
}
