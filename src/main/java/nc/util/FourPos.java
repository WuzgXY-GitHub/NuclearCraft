package nc.util;

import com.google.common.base.MoreObjects;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class FourPos {
	
	private final BlockPos pos;
	private final int dim;
	
	public FourPos(BlockPos pos, int dim) {
		this.pos = pos;
		this.dim = dim;
	}
	
	public BlockPos getBlockPos() {
		return pos;
	}
	
	public int getDimension() {
		return dim;
	}
	
	public FourPos add(BlockPos added) {
		return new FourPos(pos.add(added), dim);
	}
	
	public FourPos add(int x, int y, int z) {
		return new FourPos(pos.add(x, y, z), dim);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FourPos fourPos)) {
			return false;
		}
		
		if (dim != fourPos.dim) {
			return false;
		}
		return Objects.equals(pos, fourPos.pos);
		
	}
	
	@Override
	public int hashCode() {
		int result = pos != null ? pos.hashCode() : 0;
		result = 31 * result + dim;
		return result;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("dim", dim).add("x", pos.getX()).add("y", pos.getY()).add("z", pos.getZ()).toString();
	}
}
