package nc.recipe.multiblock;

import it.unimi.dsi.fastutil.objects.*;
import nc.recipe.*;
import net.minecraftforge.fluids.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class ElectrolyzerElectrolyteRecipeHandler extends BasicRecipeHandler {
	
	public final Object2DoubleMap<Fluid> electrolyteMap = new Object2DoubleLinkedOpenHashMap<>();
	public final List<Pair<Fluid, Double>> electrolyteList = new ArrayList<>();
	
	public ElectrolyzerElectrolyteRecipeHandler(String group) {
		super("electrolyzer_electrolyte_" + group, 0, 1, 0, 0);
	}
	
	@Override
	public void addRecipes() {
	
	}
	
	protected void initRecipeIngredients() {
		super.initRecipeIngredients();
		
		electrolyteMap.clear();
		for (BasicRecipe recipe : recipeList) {
			for (FluidStack stack : recipe.getFluidIngredients().get(0).getInputStackList()) {
				electrolyteMap.put(stack.getFluid(), recipe.getElectrolyzerElectrolyteEfficiency());
			}
		}
		
		electrolyteList.clear();
		for (Object2DoubleMap.Entry<Fluid> entry : electrolyteMap.object2DoubleEntrySet()) {
			electrolyteList.add(Pair.of(entry.getKey(), entry.getDoubleValue()));
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 1D);
		return fixer.fixed;
	}
}
