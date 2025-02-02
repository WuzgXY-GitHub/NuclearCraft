package nc.tile.machine;

import nc.handler.TileInfoHandler;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.machine.Machine;
import nc.tile.TileContainerInfo;
import net.minecraft.util.EnumFacing;

import static nc.block.property.BlockProperties.FACING_ALL;

public class TileDistillerController extends TileMachinePart implements IMachineController<TileDistillerController> {
	
	protected final TileContainerInfo<TileDistillerController> info = TileInfoHandler.getTileContainerInfo("distiller_controller");
	
	public TileDistillerController() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public String getLogicID() {
		return "distiller";
	}
	
	@Override
	public TileContainerInfo<TileDistillerController> getContainerInfo() {
		return info;
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
	
	@Override
	public boolean isRenderer() {
		return false;
	}
	
	@Override
	public void setIsRenderer(boolean isRenderer) {
	
	}
}
