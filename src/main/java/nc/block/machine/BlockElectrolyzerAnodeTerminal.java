package nc.block.machine;

import nc.block.tile.IDynamicState;
import nc.tile.machine.TileElectrolyzerAnodeTerminal;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static nc.block.property.BlockProperties.FACING_ALL;

public class BlockElectrolyzerAnodeTerminal extends BlockMachinePart implements IDynamicState {
	
	public BlockElectrolyzerAnodeTerminal() {
		super();
		setDefaultState(getDefaultState().withProperty(FACING_ALL, EnumFacing.NORTH));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING_ALL);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING_ALL, EnumFacing.byIndex(meta & 7));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING_ALL).getIndex();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileElectrolyzerAnodeTerminal();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING_ALL, placer.posY + placer.getEyeHeight() > pos.getY() + 0.5D ? EnumFacing.DOWN : EnumFacing.UP);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		// BlockHelper.setDefaultFacing(world, pos, state, FACING_ALL);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || player.isSneaking()) {
			return false;
		}
		return rightClickOnPart(world, pos, player, hand, facing);
	}
}
