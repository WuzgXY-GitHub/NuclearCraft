package nc.multiblock.machine;

import it.unimi.dsi.fastutil.objects.*;
import nc.util.Vec2i;

public class ElectrolyzerRegion {
	
	public final Object2DoubleMap<Vec2i> cathodeMap = new Object2DoubleOpenHashMap<>();
	public final Object2DoubleMap<Vec2i> anodeMap = new Object2DoubleOpenHashMap<>();
	public final Object2DoubleMap<Vec2i> diaphragmMap = new Object2DoubleOpenHashMap<>();
	
	public double efficiencyMult;
}
