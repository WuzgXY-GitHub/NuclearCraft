package nc.recipe.multiblock;

import nc.recipe.BasicRecipeHandler;

import java.util.List;

import static nc.config.NCConfig.*;

public class PebbleFissionRecipes extends BasicRecipeHandler {
	
	public PebbleFissionRecipes() {
		super("pebble_fission", 1, 0, 1, 0);
	}
	
	@Override
	public void addRecipes() {
		addFuelDepleteRecipes(fission_thorium_fuel_time, fission_thorium_heat_generation, fission_thorium_efficiency, fission_thorium_criticality, fission_thorium_decay_factor, fission_thorium_self_priming, fission_thorium_radiation, "TBU");
		addFuelDepleteRecipes(fission_uranium_fuel_time, fission_uranium_heat_generation, fission_uranium_efficiency, fission_uranium_criticality, fission_uranium_decay_factor, fission_uranium_self_priming, fission_uranium_radiation, "LEU233", "HEU233", "LEU235", "HEU235");
		addFuelDepleteRecipes(fission_neptunium_fuel_time, fission_neptunium_heat_generation, fission_neptunium_efficiency, fission_neptunium_criticality, fission_neptunium_decay_factor, fission_neptunium_self_priming, fission_neptunium_radiation, "LEN236", "HEN236");
		addFuelDepleteRecipes(fission_plutonium_fuel_time, fission_plutonium_heat_generation, fission_plutonium_efficiency, fission_plutonium_criticality, fission_plutonium_decay_factor, fission_plutonium_self_priming, fission_plutonium_radiation, "LEP239", "HEP239", "LEP241", "HEP241");
		addFuelDepleteRecipes(fission_mixed_fuel_time, fission_mixed_heat_generation, fission_mixed_efficiency, fission_mixed_criticality, fission_mixed_decay_factor, fission_mixed_self_priming, fission_mixed_radiation, "MIX239", "MIX241");
		addFuelDepleteRecipes(fission_americium_fuel_time, fission_americium_heat_generation, fission_americium_efficiency, fission_americium_criticality, fission_americium_decay_factor, fission_americium_self_priming, fission_americium_radiation, "LEA242", "HEA242");
		addFuelDepleteRecipes(fission_curium_fuel_time, fission_curium_heat_generation, fission_curium_efficiency, fission_curium_criticality, fission_curium_decay_factor, fission_curium_self_priming, fission_curium_radiation, "LECm243", "HECm243", "LECm245", "HECm245", "LECm247", "HECm247");
		addFuelDepleteRecipes(fission_berkelium_fuel_time, fission_berkelium_heat_generation, fission_berkelium_efficiency, fission_berkelium_criticality, fission_berkelium_decay_factor, fission_berkelium_self_priming, fission_berkelium_radiation, "LEB248", "HEB248");
		addFuelDepleteRecipes(fission_californium_fuel_time, fission_californium_heat_generation, fission_californium_efficiency, fission_californium_criticality, fission_californium_decay_factor, fission_californium_self_priming, fission_californium_radiation, "LECf249", "HECf249", "LECf251", "HECf251");
	}
	
	public void addFuelDepleteRecipes(int[] time, int[] heat, double[] efficiency, int[] criticality, double[] decayFactor, boolean[] selfPriming, double[] radiation, String... fuelTypes) {
		int id = 0;
		for (String fuelType : fuelTypes) {
			addRecipe("ingot" + fuelType + "TRISO", "ingotDepleted" + fuelType + "TRISO", time[id], heat[id], efficiency[id], criticality[id], decayFactor[id], selfPriming[id], radiation[id]);
			id += 5;
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Integer.class, 1);
		fixer.add(Integer.class, 0);
		fixer.add(Double.class, 0D);
		fixer.add(Integer.class, 1);
		fixer.add(Double.class, 0D);
		fixer.add(Boolean.class, false);
		fixer.add(Double.class, 0D);
		return fixer.fixed;
	}
}
