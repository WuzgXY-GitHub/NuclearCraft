package nc.recipe.processor;

import net.minecraft.item.crafting.FurnaceRecipes;

import static nc.config.NCConfig.*;

public class ElectricFurnaceRecipes extends BasicProcessorRecipeHandler {
	
	public ElectricFurnaceRecipes() {
		super("electric_furnace", 1, 0, 1, 0);
	}
	
	@Override
	public void addRecipes() {
		if (!default_processor_recipes_global || !default_processor_recipes[19]) {
			return;
		}
	}
	
	@Override
	public void init() {
		FurnaceRecipes.instance().getSmeltingList().forEach((k, v) -> {
			if (!k.isEmpty() && k.getItem() != null && !v.isEmpty() && v.getItem() != null) {
				addRecipe(k, v, 1D, 1D);
			}
		});
		super.init();
	}
}
