package nc.block.property;

import net.minecraft.util.IStringSerializable;

public enum MachinePortSorption implements IStringSerializable {
	ITEM_IN("item_in"),
	FLUID_IN("fluid_in"),
	ITEM_OUT("item_out"),
	FLUID_OUT("fluid_out");
	
	private final String name;
	
	MachinePortSorption(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
