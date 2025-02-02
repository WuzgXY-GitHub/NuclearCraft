package nc.util;

import com.google.common.base.MoreObjects;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

public class Vec3f {
	
	public static final Vec3f ZERO = new Vec3f(0f, 0f, 0f);
	
	public final float x, y, z;
	
	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3f add(Vec3f other) {
		return new Vec3f(x + other.x, y + other.y, z + other.z);
	}
	
	public Vec3f add(float x, float y, float z) {
		return new Vec3f(this.x + x, this.y + y, this.z + z);
	}
	
	public Vec3f subtract(Vec3f other) {
		return new Vec3f(x - other.x, y - other.y, z - other.z);
	}
	
	public Vec3f subtract(float x, float y, float z) {
		return new Vec3f(this.x - x, this.y - y, this.z - z);
	}
	
	public float absSq() {
		return x * x + y * y + z * z;
	}
	
	public float abs() {
		return MathHelper.sqrt(absSq());
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Vec3f other && x == other.x && y == other.y && z == other.z;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("x", x).add("y", y).add("z", z).toString();
	}
}
