package nc.block.hx;

import nc.block.tile.IActivatable;
import nc.tile.hx.TileHeatExchangerRedstonePort;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static nc.block.property.BlockProperties.ACTIVE;

public class BlockHeatExchangerRedstonePort extends BlockHeatExchangerPart implements IActivatable {
	
	public BlockHeatExchangerRedstonePort() {
		super();
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, Boolean.FALSE));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, ACTIVE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(ACTIVE, meta > 0);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ACTIVE) ? 1 : 0;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileHeatExchangerRedstonePort();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || player.isSneaking()) {
			return false;
		}
		return rightClickOnPart(world, pos, player, hand, facing);
	}
}
