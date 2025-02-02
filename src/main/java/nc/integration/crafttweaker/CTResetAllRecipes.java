package nc.integration.crafttweaker;

import crafttweaker.IAction;
import nc.recipe.BasicRecipeHandler;

public class CTResetAllRecipes implements IAction {
	
	protected final BasicRecipeHandler recipeHandler;
	
	public CTResetAllRecipes(BasicRecipeHandler recipeHandler) {
		this.recipeHandler = recipeHandler;
	}
	
	@Override
	public void apply() {
		recipeHandler.resetAllRecipes();
	}
	
	@Override
	public String describe() {
		return String.format("Reset all %s recipes", recipeHandler.getName());
	}
}
