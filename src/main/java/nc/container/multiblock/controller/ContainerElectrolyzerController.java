package nc.container.multiblock.controller;

import nc.multiblock.machine.Machine;
import nc.network.multiblock.MachineUpdatePacket;
import nc.tile.TileContainerInfo;
import nc.tile.machine.*;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerElectrolyzerController extends ContainerMultiblockController<Machine, IMachinePart, MachineUpdatePacket, TileElectrolyzerController, TileContainerInfo<TileElectrolyzerController>> {
	
	public ContainerElectrolyzerController(EntityPlayer player, TileElectrolyzerController controller) {
		super(player, controller);
	}
}
