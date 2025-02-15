package nc.tile.machine;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;

public class TileInfiltratorHeatingUnit extends TileMachinePart {
	
	public TileInfiltratorHeatingUnit() {
		super(CuboidalPartPositionType.INTERIOR);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
	}
}
