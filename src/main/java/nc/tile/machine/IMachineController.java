package nc.tile.machine;

import nc.multiblock.machine.Machine;
import nc.network.multiblock.MachineUpdatePacket;
import nc.tile.TileContainerInfo;
import nc.tile.multiblock.ILogicMultiblockController;
import net.minecraft.tileentity.TileEntity;

public interface IMachineController<CONTROLLER extends TileEntity & IMachineController<CONTROLLER>> extends IMachinePart, ILogicMultiblockController<Machine, IMachinePart, MachineUpdatePacket, CONTROLLER, TileContainerInfo<CONTROLLER>> {
	
	boolean isRenderer();
	
	void setIsRenderer(boolean isRenderer);
}
