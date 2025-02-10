package nc.block.property;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.*;
import net.minecraft.util.EnumFacing;

public class BlockProperties {
	
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public static final PropertyDirection FACING_HORIZONTAL = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyDirection FACING_ALL = BlockDirectional.FACING;
	public static final PropertyEnum<EnumFacing.Axis> AXIS_ALL = PropertyEnum.create("axis", EnumFacing.Axis.class);
	
	public static final PropertyBool FRAME = PropertyBool.create("frame");
	public static final PropertyBool INVISIBLE = PropertyBool.create("invisible");
	
	public static final PropertyEnum<MachinePortSorption> MACHINE_PORT_SORPTION = PropertyEnum.create("machine_port_sorption", MachinePortSorption.class);
}
