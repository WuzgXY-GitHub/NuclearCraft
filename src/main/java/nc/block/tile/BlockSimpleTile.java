package nc.block.tile;

import nc.block.tile.info.BlockSimpleTileInfo;
import nc.handler.TileInfoHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockSimpleTile<TILE extends TileEntity> extends BlockTile implements ITileType {
	
	protected final BlockSimpleTileInfo<TILE> tileInfo;
	
	public BlockSimpleTile(String name) {
		super(Material.IRON);
		tileInfo = TileInfoHandler.getBlockSimpleTileInfo(name);
		CreativeTabs tab = tileInfo.creativeTab;
		if (tab != null) {
			setCreativeTab(tab);
		}
	}
	
	@Override
	public String getTileName() {
		return tileInfo.name;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return tileInfo.getNewTile();
	}
	
	public static class Transparent<TILE extends TileEntity> extends BlockSimpleTile<TILE> {
		
		protected final boolean smartRender;
		
		public Transparent(String name, boolean smartRender) {
			super(name);
			setHardness(1.5F);
			setResistance(10F);
			this.smartRender = smartRender;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT;
		}
		
		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}
		
		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
			if (!smartRender) {
				return true;
			}
			
			IBlockState otherState = world.getBlockState(pos.offset(side));
			Block block = otherState.getBlock();
			
			if (state != otherState) {
				return true;
			}
			
			return block != this && super.shouldSideBeRendered(state, world, pos, side);
		}
	}
}
