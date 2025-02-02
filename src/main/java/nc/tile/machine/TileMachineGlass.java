package nc.tile.machine;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;

public class TileMachineGlass extends TileMachinePart {
	
	public TileMachineGlass() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
	}
}
