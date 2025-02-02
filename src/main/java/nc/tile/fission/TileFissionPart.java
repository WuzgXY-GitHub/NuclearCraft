package nc.tile.fission;

import nc.capability.radiation.source.IRadiationSource;
import nc.multiblock.cuboidal.*;
import nc.multiblock.fission.FissionReactor;

import javax.annotation.Nullable;

public abstract class TileFissionPart extends TileCuboidalMultiblockPart<FissionReactor, IFissionPart> implements IFissionPart {
	
	public TileFissionPart(CuboidalPartPositionType positionType) {
		super(FissionReactor.class, IFissionPart.class, positionType);
	}
	
	@Override
	public FissionReactor createNewMultiblock() {
		return new FissionReactor(world);
	}
	
	@Override
	protected @Nullable IRadiationSource getMultiblockRadiationSourceInternal() {
		return null;
	}
}
