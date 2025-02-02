package nc.tile.hx;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.hx.HeatExchanger;

public class TileHeatExchangerGlass extends TileHeatExchangerPart {
	
	public TileHeatExchangerGlass() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public void onMachineAssembled(HeatExchanger multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
	}
}
