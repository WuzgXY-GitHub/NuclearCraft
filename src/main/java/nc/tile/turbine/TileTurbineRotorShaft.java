package nc.tile.turbine;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.turbine.Turbine;

public class TileTurbineRotorShaft extends TileTurbinePart {
	
	public TileTurbineRotorShaft() {
		super(CuboidalPartPositionType.INTERIOR);
	}
	
	@Override
	public void onMachineAssembled(Turbine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
	}
}
