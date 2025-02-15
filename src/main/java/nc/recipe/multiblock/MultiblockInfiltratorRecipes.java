package nc.recipe.multiblock;

import nc.recipe.BasicRecipeHandler;

import java.util.*;

import static nc.util.FluidStackHelper.INGOT_VOLUME;

public class MultiblockInfiltratorRecipes extends BasicRecipeHandler {
	
	public MultiblockInfiltratorRecipes() {
		super("multiblock_infiltrator", 2, 2, 1, 0);
	}
	
	@Override
	public void addRecipes() {
		addRecipe(oreStack("fiberSiliconCarbide", 2), emptyItemStack(), fluidStack("polymethylsilylene_methylene", INGOT_VOLUME), emptyFluidStack(), "ingotSiCSiCCMC", 1D, 1D, 0D, 1D);
		addRecipe("sinteredZirconia", "dustZirconia", fluidStack("polyphenylene_sulfide", INGOT_VOLUME), emptyFluidStack(), "ingotZirfon", 1D, 1D, 0D, 0D);
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 1D);
		fixer.add(Double.class, 0D);
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
