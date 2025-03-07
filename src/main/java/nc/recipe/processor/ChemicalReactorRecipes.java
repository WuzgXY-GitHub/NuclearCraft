package nc.recipe.processor;

import java.util.*;

import static nc.config.NCConfig.*;
import static nc.util.FissionHelper.FISSION_FLUID;
import static nc.util.FluidStackHelper.*;

public class ChemicalReactorRecipes extends BasicProcessorRecipeHandler {
	
	public ChemicalReactorRecipes() {
		super("chemical_reactor", 0, 2, 0, 2);
	}
	
	@Override
	public void addRecipes() {
		if (!default_processor_recipes_global || !default_processor_recipes[12]) {
			return;
		}
		
		addRecipe(fluidStack("boron", INGOT_VOLUME), fluidStack("hydrogen", BUCKET_VOLUME * 3 / 2), fluidStack("diborane", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 1D);
		
		addRecipe(fluidStack("diborane", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME * 3 / 2), fluidStack("boric_acid", BUCKET_VOLUME / 2), fluidStack("hydrogen", BUCKET_VOLUME * 3 / 2), 0.5D, 0.5D);
		
		addRecipe(fluidStack("nitrogen", BUCKET_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME * 3 / 4), fluidStack("ammonia", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("boric_acid", BUCKET_VOLUME / 2), fluidStack("ammonia", BUCKET_VOLUME / 2), fluidStack("boron_nitride_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME), 0.5D, 0.5D);
		
		addRecipe(fluidStack("hydrogen", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("deuterium", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("heavy_water", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 4), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 4), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("lithium", INGOT_VOLUME / 2), fluidStack("fluorine", BUCKET_VOLUME / 4), fluidStack("lif", INGOT_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("beryllium", INGOT_VOLUME / 2), fluidStack("fluorine", BUCKET_VOLUME / 2), fluidStack("bef2", INGOT_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("sulfur", GEM_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 2), fluidStack("sulfur_dioxide", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("sulfur_dioxide", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("sulfur_trioxide", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("sulfur_trioxide", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME / 4), fluidStack("sulfuric_acid", BUCKET_VOLUME / 4), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("sulfur", GEM_VOLUME / 2), fluidStack("hydrogen", BUCKET_VOLUME / 2), fluidStack("hydrogen_sulfide", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("fluorite_water", GEM_VOLUME / 2), fluidStack("sulfuric_acid", BUCKET_VOLUME / 2), fluidStack("hydrofluoric_acid", BUCKET_VOLUME), fluidStack("calcium_sulfate_solution", GEM_VOLUME / 2), 1D, 0.5D);
		
		addRecipe(fluidStack("sodium_fluoride_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 2), fluidStack("sodium_hydroxide_solution", GEM_VOLUME / 2), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 2), 0.5D, 1D);
		addRecipe(fluidStack("potassium_fluoride_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 2), fluidStack("potassium_hydroxide_solution", GEM_VOLUME / 2), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 2), 0.5D, 1D);
		
		addRecipe(fluidStack("sodium_fluoride_solution", GEM_VOLUME), fluidStack("boric_acid", BUCKET_VOLUME * 2), fluidStack("borax_solution", GEM_VOLUME / 2), fluidStack("hydrofluoric_acid", BUCKET_VOLUME), 1.5D, 1D);
		
		addRecipe(fluidStack("ammonia", BUCKET_VOLUME), fluidStack("sulfuric_acid", BUCKET_VOLUME / 2), fluidStack("ammonium_sulfate_solution", GEM_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		
		addRecipe(fluidStack("ammonium_persulfate_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME), fluidStack("ammonium_bisulfate_solution", GEM_VOLUME), fluidStack("hydrogen_peroxide", BUCKET_VOLUME), 0.5D, 1D);
		
		addRecipe(fluidStack("sodium", INGOT_VOLUME), fluidStack("sulfur", GEM_VOLUME / 2), fluidStack("sodium_sulfide", INGOT_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		addRecipe(fluidStack("potassium", INGOT_VOLUME), fluidStack("sulfur", GEM_VOLUME / 2), fluidStack("potassium_sulfide", INGOT_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		
		addRecipe(fluidStack("oxygen_difluoride", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME / 4), fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 2), 0.5D, 1D);
		addRecipe(fluidStack("oxygen_difluoride", BUCKET_VOLUME / 4), fluidStack("sulfur_dioxide", BUCKET_VOLUME / 4), fluidStack("sulfur_trioxide", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 4), 0.5D, 1D);
		
		addRecipe(fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 2), fluidStack("oxygen_difluoride", BUCKET_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		
		addRecipe(fluidStack("manganese_dioxide", INGOT_VOLUME / 2), fluidStack("carbon", COAL_DUST_VOLUME), fluidStack("manganese", INGOT_VOLUME / 2), fluidStack("carbon_monoxide", BUCKET_VOLUME), 0.5D, 1D);
		
		addRecipe(fluidStack("sugar", INGOT_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 2), fluidStack("ethanol", BUCKET_VOLUME * 2), fluidStack("carbon_dioxide", BUCKET_VOLUME * 2), 0.5D, 0.5D);
		addRecipe(fluidStack("carbon_dioxide", BUCKET_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("carbon_monoxide", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME / 4), 0.5D, 0.5D);
		addRecipe(fluidStack("carbon_monoxide", BUCKET_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME / 2), fluidStack("methanol", BUCKET_VOLUME / 4), emptyFluidStack(), 0.5D, 0.5D);
		addRecipe(fluidStack("methanol", BUCKET_VOLUME / 4), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 4), fluidStack("fluoromethane", BUCKET_VOLUME / 4), fluidStack("water", BUCKET_VOLUME / 4), 0.5D, 1D);
		
		addRecipe(fluidStack("fluoromethane", BUCKET_VOLUME / 2), fluidStack("naoh", GEM_VOLUME / 2), fluidStack("ethene", BUCKET_VOLUME / 4), fluidStack("sodium_fluoride_solution", GEM_VOLUME / 2), 1D, 1D);
		addRecipe(fluidStack("fluoromethane", BUCKET_VOLUME / 2), fluidStack("koh", GEM_VOLUME / 2), fluidStack("ethene", BUCKET_VOLUME / 4), fluidStack("potassium_fluoride_solution", GEM_VOLUME / 2), 1D, 1D);
		
		addRecipe(fluidStack("ethene", BUCKET_VOLUME / 4), fluidStack("sulfuric_acid", BUCKET_VOLUME / 4), fluidStack("ethanol", BUCKET_VOLUME / 4), fluidStack("sulfur_trioxide", BUCKET_VOLUME / 4), 0.5D, 1D);
		
		addRecipe(fluidStack("ethene", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 2), fluidStack("ethyne", BUCKET_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 2), 1.5D, 2D);
		addRecipe(fluidStack("ethene", BUCKET_VOLUME / 4), fluidStack("ethyne", BUCKET_VOLUME / 2), fluidStack("benzene", BUCKET_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME / 4), 1.5D, 2D);
		
		addRecipe(fluidStack("benzene", BUCKET_VOLUME / 2), fluidStack("oxygen_difluoride", BUCKET_VOLUME / 4), fluidStack("fluorobenzene", BUCKET_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 4), 0.5D, 0.5D);
		addRecipe(fluidStack("fluorobenzene", BUCKET_VOLUME), fluidStack("sulfur_trioxide", BUCKET_VOLUME / 2), fluidStack("dfdps", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME / 2), 1D, 1D);
		
		addRecipe(fluidStack("benzene", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 2), fluidStack("difluorobenzene", BUCKET_VOLUME / 4), fluidStack("hydrofluoric_acid", BUCKET_VOLUME / 2), 0.5D, 0.5D);
		addRecipe(fluidStack("difluorobenzene", BUCKET_VOLUME / 4), fluidStack("sodium_sulfide", INGOT_VOLUME / 4), fluidStack("polyphenylene_sulfide", INGOT_VOLUME / 4), fluidStack("sodium_fluoride_solution", GEM_VOLUME / 2), 2D, 1D);
		addRecipe(fluidStack("difluorobenzene", BUCKET_VOLUME / 4), fluidStack("potassium_sulfide", INGOT_VOLUME / 4), fluidStack("polyphenylene_sulfide", INGOT_VOLUME / 4), fluidStack("potassium_fluoride_solution", GEM_VOLUME / 2), 2D, 1D);
		
		addRecipe(fluidStack("fluoromethane", BUCKET_VOLUME / 2), fluidStack("silicon", INGOT_VOLUME / 4), fluidStack("dimethyldifluorosilane", BUCKET_VOLUME / 4), emptyFluidStack(), 0.5D, 1D);
		addRecipe(fluidStack("dimethyldifluorosilane", BUCKET_VOLUME / 4), fluidStack("sodium", INGOT_VOLUME / 2), fluidStack("polydimethylsilylene", INGOT_VOLUME / 4), fluidStack("sodium_fluoride_solution", GEM_VOLUME / 2), 2D, 1D);
		addRecipe(fluidStack("dimethyldifluorosilane", BUCKET_VOLUME / 4), fluidStack("potassium", INGOT_VOLUME / 2), fluidStack("polydimethylsilylene", INGOT_VOLUME / 4), fluidStack("potassium_fluoride_solution", GEM_VOLUME / 2), 2D, 1D);
		
		addRecipe(fluidStack("benzene", BUCKET_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME / 4), fluidStack("phenol", BUCKET_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		addRecipe(fluidStack("phenol", BUCKET_VOLUME / 2), fluidStack("hydrogen_peroxide", BUCKET_VOLUME / 2), fluidStack("hydroquinone_solution", GEM_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		addRecipe(fluidStack("hydroquinone_solution", GEM_VOLUME / 2), fluidStack("sodium_hydroxide_solution", BUCKET_VOLUME), fluidStack("sodium_hydroquinone_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME), 1D, 1D);
		addRecipe(fluidStack("hydroquinone_solution", GEM_VOLUME / 2), fluidStack("potassium_hydroxide_solution", BUCKET_VOLUME), fluidStack("potassium_hydroquinone_solution", GEM_VOLUME / 2), fluidStack("water", BUCKET_VOLUME), 1D, 1D);
		
		addRecipe(fluidStack("dfdps", GEM_VOLUME / 2), fluidStack("sodium_hydroquinone_solution", GEM_VOLUME / 2), fluidStack("polyethersulfone", INGOT_VOLUME / 2), fluidStack("sodium_fluoride_solution", GEM_VOLUME), 1D, 1D);
		addRecipe(fluidStack("dfdps", GEM_VOLUME / 2), fluidStack("potassium_hydroquinone_solution", GEM_VOLUME / 2), fluidStack("polyethersulfone", INGOT_VOLUME / 2), fluidStack("potassium_fluoride_solution", GEM_VOLUME), 1D, 1D);
		
		addRecipe(fluidStack("ethene", BUCKET_VOLUME / 4), fluidStack("fluorine", BUCKET_VOLUME / 2), fluidStack("polytetrafluoroethene", INGOT_VOLUME / 4), fluidStack("hydrogen", BUCKET_VOLUME / 2), 2D, 1D);
		
		addRecipe(fluidStack("boron", INGOT_VOLUME / 2), fluidStack("arsenic", GEM_VOLUME / 2), fluidStack("bas", GEM_VOLUME / 2), emptyFluidStack(), 1D, 1D);
		
		addRecipe(fluidStack("alugentum", INGOT_VOLUME / 2), fluidStack("oxygen", BUCKET_VOLUME * 3), fluidStack("alumina", INGOT_VOLUME), fluidStack("silver", INGOT_VOLUME / 2), 1D, 0.5D);
		
		addRecipe(fluidStack("hydrogen", BUCKET_VOLUME / 4), fluidStack("chlorine", BUCKET_VOLUME / 4), fluidStack("hydrogen_chloride", BUCKET_VOLUME / 4), emptyFluidStack(), 0.5D, 0.5D);
		
		// Fission Materials
		addFissionFluorideRecipes();
	}
	
	public void addFissionFluorideRecipes() {
		for (String element : FISSION_FLUID) {
			addRecipe(fluidStack(element, INGOT_VOLUME / 2), fluidStack("fluorine", BUCKET_VOLUME / 2), fluidStack(element + "_fluoride", INGOT_VOLUME / 2), emptyFluidStack(), 0.5D, 0.5D);
		}
	}
	
	@Override
	public List<Object> getFactoredExtras(List<Object> extras, int factor) {
		List<Object> factored = new ArrayList<>(extras);
		factored.set(0, (double) extras.get(0) / factor);
		return factored;
	}
}
