package nc.recipe;

import nc.util.*;
import net.minecraft.nbt.NBTTagCompound;

public class RecipeUnitInfo {
	
	public static final RecipeUnitInfo DEFAULT = new RecipeUnitInfo("R/t", 0, 1);
	
	public final String unit;
	public final int startingPrefix;
	public final int rateMultiplier;
	
	public RecipeUnitInfo(String unit, int startingPrefix, int rateMultiplier) {
		this.unit = unit;
		this.startingPrefix = startingPrefix;
		this.rateMultiplier = rateMultiplier;
	}
	
	public void writeToNBT(NBTTagCompound nbt, String name) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("unit", unit);
		tag.setInteger("startingPrefix", startingPrefix);
		tag.setInteger("rateMultiplier", rateMultiplier);
		nbt.setTag(name, tag);
	}
	
	public static RecipeUnitInfo readFromNBT(NBTTagCompound nbt, String name) {
		if (nbt.hasKey(name, 10)) {
			NBTTagCompound tag = nbt.getCompoundTag(name);
			return new RecipeUnitInfo(tag.getString("unit"), tag.getInteger("startingPrefix"), tag.getInteger("rateMultiplier"));
		}
		return DEFAULT;
	}
	
	public String getString(Double processTime, int maxLength) {
		double rate = processTime == null ? 0 : rateMultiplier / processTime;
		if (unit.equals("B") || unit.equals("B/t")) {
			return UnitHelper.prefix(rate, maxLength, unit, startingPrefix);
		}
		else {
			return NCMath.sigFigs(rate, maxLength) + " " + unit;
		}
	}
}
