package nc.tile.machine;

import nc.block.property.BlockProperties;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;

public class TileMachineFrame extends TileMachinePart {
	
	public TileMachineFrame() {
		super(CuboidalPartPositionType.EXTERIOR);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote && getPartPosition().isFrame()) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockProperties.FRAME, true), 2);
		}
	}
	
	@Override
	public void onMachineBroken() {
		if (!world.isRemote && getPartPosition().isFrame()) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockProperties.FRAME, false), 2);
		}
		super.onMachineBroken();
	}
}
