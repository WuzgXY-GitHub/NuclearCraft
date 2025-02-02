package nc.container.multiblock.controller;

import nc.multiblock.machine.Machine;
import nc.network.multiblock.MachineUpdatePacket;
import nc.tile.TileContainerInfo;
import nc.tile.machine.*;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerDistillerController extends ContainerMultiblockController<Machine, IMachinePart, MachineUpdatePacket, TileDistillerController, TileContainerInfo<TileDistillerController>> {
	
	public ContainerDistillerController(EntityPlayer player, TileDistillerController controller) {
		super(player, controller);
	}
}
