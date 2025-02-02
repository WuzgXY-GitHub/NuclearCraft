package nc.tile.hx;

import nc.capability.radiation.source.IRadiationSource;
import nc.multiblock.cuboidal.*;
import nc.multiblock.hx.HeatExchanger;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public abstract class TileHeatExchangerPart extends TileCuboidalMultiblockPart<HeatExchanger, IHeatExchangerPart> implements IHeatExchangerPart {
	
	public boolean isHeatExchangerOn;
	
	public TileHeatExchangerPart(CuboidalPartPositionType positionType) {
		super(HeatExchanger.class, IHeatExchangerPart.class, positionType);
	}
	
	@Override
	public HeatExchanger createNewMultiblock() {
		return new HeatExchanger(world);
	}
	
	public void setIsHeatExchangerOn() {
		HeatExchanger multiblock = getMultiblock();
		if (multiblock != null) {
			isHeatExchangerOn = multiblock.isHeatExchangerOn;
		}
	}
	
	@Override
	protected @Nullable IRadiationSource getMultiblockRadiationSourceInternal() {
		return null;
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		nbt.setBoolean("isHeatExchangerOn", isHeatExchangerOn);
		return nbt;
	}
	
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		isHeatExchangerOn = nbt.getBoolean("isHeatExchangerOn");
	}
}
