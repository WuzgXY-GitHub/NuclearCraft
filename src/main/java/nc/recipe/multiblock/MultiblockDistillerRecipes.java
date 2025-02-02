package nc.recipe.multiblock;

import nc.recipe.BasicRecipeHandler;

import java.util.*;

import static nc.util.FluidStackHelper.BUCKET_VOLUME;

public class MultiblockDistillerRecipes extends BasicRecipeHandler {
	
	public MultiblockDistillerRecipes() {
		super("multiblock_distiller", 0, 2, 0, 2);
	}
	
	@Override
	public void addRecipes() {
		addRecipe(fluidStack("water", BUCKET_VOLUME / 2), fluidStack("hydrogen", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), "hydroxide_solution", 0.5D, 1D);
		addRecipe(fluidStack("heavy_water", BUCKET_VOLUME / 2), fluidStack("deuterium", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), "hydroxide_solution", 0.5D, 1D);
		addRecipe(fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 4), emptyFluidStack(), emptyFluidStack(), "fluoride_solution", 0.5D, 1D);
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 0D);
		return fixer.fixed;
	}
	
	@Override
	public List<Object> getFactoredExtras(List<Object> extras, int factor) {
		List<Object> factored = new ArrayList<>(extras);
		factored.set(0, (double) extras.get(0) / factor);
		return factored;
	}
}
