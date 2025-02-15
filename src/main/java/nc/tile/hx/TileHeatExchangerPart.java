package nc.tile.hx;

import nc.multiblock.cuboidal.*;
import nc.multiblock.hx.HeatExchanger;

public abstract class TileHeatExchangerPart extends TileCuboidalMultiblockPart<HeatExchanger, IHeatExchangerPart> implements IHeatExchangerPart {
	
	public TileHeatExchangerPart(CuboidalPartPositionType positionType) {
		super(HeatExchanger.class, IHeatExchangerPart.class, positionType);
	}
	
	@Override
	public HeatExchanger createNewMultiblock() {
		return new HeatExchanger(world);
	}
}
