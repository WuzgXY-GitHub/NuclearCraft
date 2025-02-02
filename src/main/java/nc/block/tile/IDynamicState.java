package nc.block.tile;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IDynamicState {
	
	default <T extends Enum<T> & IStringSerializable> void setProperty(PropertyEnum<T> property, T value, TileEntity tile) {
		World world = tile.getWorld();
		BlockPos pos = tile.getPos();
		IBlockState state = world.getBlockState(pos);
		if (!world.isRemote && getClass().isInstance(state.getBlock())) {
			if (!value.equals(state.getValue(property))) {
				world.setBlockState(pos, state.withProperty(property, value), 2);
			}
		}
	}
}
