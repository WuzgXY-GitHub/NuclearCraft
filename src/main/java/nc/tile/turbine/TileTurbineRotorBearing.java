package nc.tile.turbine;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.turbine.Turbine;

import java.util.Iterator;

public class TileTurbineRotorBearing extends TileTurbinePart {
	
	public TileTurbineRotorBearing() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public void onMachineAssembled(Turbine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
	}
	
	public void onBearingFailure(Iterator<TileTurbineRotorBearing> bearingIterator) {
		Turbine turbine = getMultiblock();
		if (turbine != null) {
			bearingIterator.remove();
			world.removeTileEntity(pos);
			world.setBlockToAir(pos);
			world.createExplosion(null, pos.getX() + turbine.rand.nextDouble() - 0.5D, pos.getY() + turbine.rand.nextDouble() - 0.5D, pos.getZ() + turbine.rand.nextDouble() - 0.5D, 4F, false);
		}
	}
}
