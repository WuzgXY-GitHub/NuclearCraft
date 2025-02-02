package nc.recipe.multiblock;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.*;
import nc.recipe.*;
import nc.util.StreamHelper;

import java.util.*;

import static nc.util.FluidStackHelper.BUCKET_VOLUME;

public class MultiblockElectrolyzerRecipes extends BasicRecipeHandler {
	
	public final Object2ObjectMap<String, ElectrolyzerElectrolyteRecipeHandler> electrolyteRecipeHandlerMap;
	
	public MultiblockElectrolyzerRecipes() {
		super("multiblock_electrolyzer", 2, 2, 4, 4);
		electrolyteRecipeHandlerMap = new Object2ObjectOpenHashMap<>();
		addElectrolytes();
	}
	
	@Override
	public void addRecipes() {
		addFluidRecipe(fluidStack("water", BUCKET_VOLUME / 2), emptyFluidStack(), fluidStack("hydrogen", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), 1D, 1D, 0D, "hydroxide_solution");
		addFluidRecipe(fluidStack("le_water", BUCKET_VOLUME / 2), emptyFluidStack(), fluidStack("hydrogen", 3 * BUCKET_VOLUME / 8), fluidStack("deuterium", BUCKET_VOLUME / 8), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), 1D, 1D, 0D, "hydroxide_solution");
		addFluidRecipe(fluidStack("he_water", BUCKET_VOLUME / 2), emptyFluidStack(), fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("deuterium", BUCKET_VOLUME / 4), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), 1D, 1D, 0D, "hydroxide_solution");
		addFluidRecipe(fluidStack("heavy_water", BUCKET_VOLUME / 2), emptyFluidStack(), fluidStack("deuterium", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), 1D, 1D, 0D, "hydroxide_solution");
		addFluidRecipe(fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 4), emptyFluidStack(), fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), 1D, 1D, 0D, "fluoride_solution");
	}
	
	protected void addFluidRecipe(Object... objects) {
		addRecipe(StreamHelper.flatten(new Object[][] {{emptyItemStack(), emptyItemStack(), objects[0], objects[1], emptyItemStack(), emptyItemStack(), emptyItemStack(), emptyItemStack(), objects[2], objects[3], objects[4], objects[5]}, Arrays.copyOfRange(objects, 6, objects.length)}, Object[]::new));
	}
	
	protected void addElectrolytes() {
		addElectrolyte("hydroxide_solution", "sodium_hydroxide_solution", 0.9D);
		addElectrolyte("hydroxide_solution", "potassium_hydroxide_solution", 1D);
		addElectrolyte("fluoride_solution", "sodium_fluoride_solution", 0.9D);
		addElectrolyte("fluoride_solution", "potassium_fluoride_solution", 1D);
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 0D);
		fixer.add(String.class, "null");
		return fixer.fixed;
	}
	
	@Override
	public List<Object> getFactoredExtras(List<Object> extras, int factor) {
		List<Object> factored = new ArrayList<>(extras);
		factored.set(0, (double) extras.get(0) / factor);
		return factored;
	}
	
	@Override
	public void init() {
		super.init();
		electrolyteRecipeHandlerMap.values().forEach(AbstractRecipeHandler::init);
	}
	
	@Override
	public void postInit() {
		super.postInit();
		electrolyteRecipeHandlerMap.values().forEach(AbstractRecipeHandler::postInit);
	}
	
	@Override
	public void onReload() {
		super.onReload();
		electrolyteRecipeHandlerMap.values().forEach(AbstractRecipeHandler::onReload);
	}
	
	@Override
	public void refreshCache() {
		super.refreshCache();
		electrolyteRecipeHandlerMap.values().forEach(AbstractRecipeHandler::refreshCache);
	}
	
	public void addElectrolyte(String group, Object electrolyte, double efficiency) {
		if (!electrolyteRecipeHandlerMap.containsKey(group)) {
			electrolyteRecipeHandlerMap.put(group, new ElectrolyzerElectrolyteRecipeHandler(group));
		}
		electrolyteRecipeHandlerMap.get(group).addRecipe(electrolyte, efficiency);
	}
	
	public void removeElectrolyte(String group, Object electrolyte) {
		ElectrolyzerElectrolyteRecipeHandler handler = electrolyteRecipeHandlerMap.get(group);
		if (handler != null) {
			handler.removeRecipe(handler.getRecipeFromIngredients(Collections.emptyList(), Lists.newArrayList(buildFluidInputIngredientInternal(electrolyte))));
			if (handler.getRecipeList().isEmpty()) {
				removeElectrolyteGroup(group);
			}
		}
	}
	
	public void removeElectrolyteGroup(String group) {
		electrolyteRecipeHandlerMap.remove(group);
	}
	
	public void removeAllElectrolyteGroups() {
		electrolyteRecipeHandlerMap.clear();
	}
}
