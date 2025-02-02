package nc.recipe.multiblock;

import nc.recipe.BasicRecipeHandler;

import java.util.List;

import static nc.config.NCConfig.machine_cathode_efficiency;

public class ElectrolyzerCathodeRecipes extends BasicRecipeHandler {
	
	public ElectrolyzerCathodeRecipes() {
		super("electrolyzer_cathode", 1, 0, 0, 0);
	}
	
	@Override
	public void addRecipes() {
		for (String s : machine_cathode_efficiency) {
			int index = s.indexOf('@');
			addRecipe("block" + s.substring(0, index), Double.valueOf(s.substring(1 + index)));
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 1D);
		return fixer.fixed;
	}
}
