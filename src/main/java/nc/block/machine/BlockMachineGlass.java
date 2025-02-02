package nc.block.machine;

import nc.tile.machine.TileMachineGlass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineGlass extends BlockMachinePart.Transparent {
	
	public BlockMachineGlass() {
		super(true);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMachineGlass();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || player.isSneaking()) {
			return false;
		}
		return rightClickOnPart(world, pos, player, hand, facing);
	}
}
