package nc.block.machine;

import nc.block.property.MachinePortSorption;
import nc.block.tile.IDynamicState;
import nc.item.ItemMultitool;
import nc.tile.machine.TileMachineProcessPort;
import nc.util.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

import static nc.block.property.BlockProperties.*;

public class BlockMachineProcessPort extends BlockMachinePart implements IDynamicState {
	
	public BlockMachineProcessPort() {
		super();
		setDefaultState(blockState.getBaseState().withProperty(AXIS_ALL, EnumFacing.Axis.Z).withProperty(MACHINE_PORT_SORPTION, MachinePortSorption.ITEM_IN));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AXIS_ALL, MACHINE_PORT_SORPTION);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AXIS_ALL, PosHelper.AXES[meta & 3]).withProperty(MACHINE_PORT_SORPTION, MachinePortSorption.values()[meta >> 2]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AXIS_ALL).ordinal() + (state.getValue(MACHINE_PORT_SORPTION).ordinal() << 2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMachineProcessPort();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(AXIS_ALL, EnumFacing.getDirectionFromEntityLiving(pos, placer).getAxis());
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND) {
			return false;
		}
		
		if (!ItemMultitool.isMultitool(player.getHeldItem(hand))) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileMachineProcessPort) {
				if (!world.isRemote) {
					TileMachineProcessPort port = (TileMachineProcessPort) tile;
					if (port.getMultiblock() != null) {
						player.sendMessage(new TextComponentString(Lang.localize("nc.block.port_setting") + " " + port.getMachinePortSettingString() + " " + TextFormatting.WHITE + Lang.localize("nc.block.port_setting.mode")));
					}
				}
				return true;
			}
		}
		
		return rightClickOnPart(world, pos, player, hand, facing);
	}
}
