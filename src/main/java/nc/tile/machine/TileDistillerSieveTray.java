package nc.tile.machine;

import nc.block.property.BlockProperties;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;

public class TileDistillerSieveTray extends TileMachinePart {
	
	public TileDistillerSieveTray() {
		super(CuboidalPartPositionType.INTERIOR);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockProperties.INVISIBLE, true), 2);
		}
	}
	
	@Override
	public void onMachineBroken() {
		if (!world.isRemote) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockProperties.INVISIBLE, false), 2);
		}
		super.onMachineBroken();
	}
}
