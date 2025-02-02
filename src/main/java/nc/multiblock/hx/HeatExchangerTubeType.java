package nc.multiblock.hx;

import nc.enumm.ITileEnum;
import nc.tile.hx.*;

import static nc.config.NCConfig.heat_exchanger_conductivity;

public enum HeatExchangerTubeType implements ITileEnum<TileHeatExchangerTube.Variant> {
	
	COPPER("copper", heat_exchanger_conductivity[0], TileHeatExchangerTube.Copper.class, TileCondenserTube.Copper.class),
	HARD_CARBON("hard_carbon", heat_exchanger_conductivity[1], TileHeatExchangerTube.HardCarbon.class, TileCondenserTube.HardCarbon.class),
	THERMOCONDUCTING("thermoconducting", heat_exchanger_conductivity[2], TileHeatExchangerTube.Thermoconducting.class, TileCondenserTube.Thermoconducting.class);
	
	private final String name;
	private final double conductivity;
	private final Class<? extends TileHeatExchangerTube.Variant> tileClass;
	private final Class<? extends TileCondenserTube.Variant> condenserTileClass;
	
	HeatExchangerTubeType(String name, double conductivity, Class<? extends TileHeatExchangerTube.Variant> tileClass, Class<? extends TileCondenserTube.Variant> condenserTileClass) {
		this.name = name;
		this.conductivity = conductivity;
		this.tileClass = tileClass;
		this.condenserTileClass = condenserTileClass;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public double getConductivity() {
		return conductivity;
	}
	
	@Override
	public Class<? extends TileHeatExchangerTube.Variant> getTileClass() {
		return tileClass;
	}
	
	public Class<? extends TileCondenserTube.Variant> getCondenserTileClass() {
		return condenserTileClass;
	}
}
