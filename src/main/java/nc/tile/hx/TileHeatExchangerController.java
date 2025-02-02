package nc.tile.hx;

import nc.handler.TileInfoHandler;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.hx.*;
import nc.tile.TileContainerInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static nc.block.property.BlockProperties.FACING_ALL;

public class TileHeatExchangerController extends TileHeatExchangerPart implements IHeatExchangerController<TileHeatExchangerController> {
	
	protected final TileContainerInfo<TileHeatExchangerController> info = TileInfoHandler.getTileContainerInfo("heat_exchanger_controller");
	
	public TileHeatExchangerController() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public String getLogicID() {
		return "heat_exchanger";
	}
	
	@Override
	public TileContainerInfo<TileHeatExchangerController> getContainerInfo() {
		return info;
	}
	
	@Override
	public void onMachineAssembled(HeatExchanger multiblock) {
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
	public void onBlockNeighborChanged(IBlockState state, World worldIn, BlockPos posIn, BlockPos fromPos) {
		super.onBlockNeighborChanged(state, worldIn, posIn, fromPos);
		HeatExchangerLogic logic = getLogic();
		if (logic != null) {
			logic.setIsHeatExchangerOn();
		}
	}
	
	@Override
	public int[] weakSidesToCheck(World worldIn, BlockPos posIn) {
		return new int[] {2, 3, 4, 5};
	}
}
