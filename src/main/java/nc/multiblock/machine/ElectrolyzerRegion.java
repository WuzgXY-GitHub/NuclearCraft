package nc.multiblock.machine;

import it.unimi.dsi.fastutil.objects.*;
import nc.util.Vec2i;

public class ElectrolyzerRegion {
	
	public final Object2DoubleMap<Vec2i> cathodes = new Object2DoubleOpenHashMap<>();
	public final Object2DoubleMap<Vec2i> anodes = new Object2DoubleOpenHashMap<>();
	public final Object2DoubleMap<Vec2i> diaphragms = new Object2DoubleOpenHashMap<>();
	
	public double efficiencyMult;
}
