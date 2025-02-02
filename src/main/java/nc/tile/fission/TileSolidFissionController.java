package nc.tile.fission;

import nc.handler.TileInfoHandler;
import nc.multiblock.cuboidal.CuboidalPartPositionType;
import nc.multiblock.fission.FissionReactor;
import nc.tile.TileContainerInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Iterator;

import static nc.block.property.BlockProperties.FACING_ALL;

public class TileSolidFissionController extends TileFissionPart implements IFissionController<TileSolidFissionController> {
	
	protected final TileContainerInfo<TileSolidFissionController> info = TileInfoHandler.getTileContainerInfo("solid_fission_controller");
	
	public TileSolidFissionController() {
		super(CuboidalPartPositionType.WALL);
	}
	
	@Override
	public String getLogicID() {
		return "solid_fuel";
	}
	
	@Override
	public TileContainerInfo<TileSolidFissionController> getContainerInfo() {
		return info;
	}
	
	@Override
	public void onMachineAssembled(FissionReactor multiblock) {
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
		FissionReactor multiblock = getMultiblock();
		if (multiblock != null) {
			multiblock.updateActivity();
		}
	}
	
	@Override
	public void doMeltdown(Iterator<IFissionController<?>> controllerIterator) {
		controllerIterator.remove();
		world.removeTileEntity(pos);
		
		IBlockState corium = FluidRegistry.getFluid("corium").getBlock().getDefaultState();
		world.setBlockState(pos, corium);
	}
}
