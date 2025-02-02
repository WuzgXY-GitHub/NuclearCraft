package nc.tile.machine;

import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;
import net.minecraft.util.EnumFacing;

import static nc.block.property.BlockProperties.FACING_ALL;

public class TileElectrolyzerAnodeTerminal extends TileMachinePart {
	
	public TileElectrolyzerAnodeTerminal() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public void onMachineAssembled(Machine multiblock) {
		doStandardNullControllerResponse(multiblock);
		super.onMachineAssembled(multiblock);
		if (!world.isRemote) {
			EnumFacing facing = getPartPosition().getFacing();
			if (facing != null) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING_ALL, facing), 2);
			}
		}
	}
}
