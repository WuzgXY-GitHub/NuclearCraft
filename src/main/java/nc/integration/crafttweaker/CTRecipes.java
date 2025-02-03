package nc.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.liquid.ILiquidStack;
import nc.recipe.*;
import stanhebben.zenscript.annotations.*;

public class CTRecipes {
	
	@ZenClass("mods.nuclearcraft.Manufactory")
	@ZenRegister
	public static class ManufactoryMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.manufactory;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Separator")
	@ZenRegister
	public static class SeparatorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.separator;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.DecayHastener")
	@ZenRegister
	public static class DecayHastenerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.decay_hastener;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FuelReprocessor")
	@ZenRegister
	public static class FuelReprocessorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fuel_reprocessor;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, IIngredient output5, IIngredient output6, IIngredient output7, IIngredient output8, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, output3, output4, output5, output6, output7, output8, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, IIngredient output5, IIngredient output6, IIngredient output7, IIngredient output8) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2, output3, output4, output5, output6, output7, output8);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.AlloyFurnace")
	@ZenRegister
	public static class AlloyFurnaceMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.alloy_furnace;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Infuser")
	@ZenRegister
	public static class InfuserMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.infuser;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Melter")
	@ZenRegister
	public static class MelterMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.melter;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Supercooler")
	@ZenRegister
	public static class SupercoolerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.supercooler;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Electrolyzer")
	@ZenRegister
	public static class ElectrolyzerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.electrolyzer;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, output3, output4, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2, output3, output4);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Assembler")
	@ZenRegister
	public static class AssemblerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.assembler;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient input3, IIngredient input4, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, input3, input4, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2, IIngredient input3, IIngredient input4) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2, input3, input4);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.IngotFormer")
	@ZenRegister
	public static class IngotFormerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.ingot_former;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Pressurizer")
	@ZenRegister
	public static class PressurizerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.pressurizer;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.ChemicalReactor")
	@ZenRegister
	public static class ChemicalReactorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.chemical_reactor;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output1, IIngredient output2, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output1, output2, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.SaltMixer")
	@ZenRegister
	public static class SaltMixerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.salt_mixer;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Crystallizer")
	@ZenRegister
	public static class CrystallizerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.crystallizer;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Enricher")
	@ZenRegister
	public static class EnricherMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.enricher;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Extractor")
	@ZenRegister
	public static class ExtractorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.extractor;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Centrifuge")
	@ZenRegister
	public static class CentrifugeMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.centrifuge;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, IIngredient output5, IIngredient output6, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, output3, output4, output5, output6, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, IIngredient output5, IIngredient output6) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2, output3, output4, output5, output6);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.RockCrusher")
	@ZenRegister
	public static class RockCrusherMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.rock_crusher;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, IIngredient output3, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, output3, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2, IIngredient output3) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2, output3);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.ElectricFurnace")
	@ZenRegister
	public static class ElectricFurnaceMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.electric_furnace;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input, output, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.DecayGenerator")
	@ZenRegister
	public static class DecayGeneratorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.decay_generator;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, double meanLifetime, double power, double radiation) {
			getRecipeHandler().ctAddRecipe(input, output, meanLifetime, power, radiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Diaphragm")
	@ZenRegister
	public static class DiaphragmMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.machine_diaphragm;
		}
		
		@ZenMethod
		public static void add(IIngredient input, double efficiency, double contactFactor) {
			getRecipeHandler().ctAddRecipe(input, efficiency, contactFactor);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.SieveTray")
	@ZenRegister
	public static class SieveTrayMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.machine_sieve_tray;
		}
		
		@ZenMethod
		public static void add(IIngredient input, double efficiency) {
			getRecipeHandler().ctAddRecipe(input, efficiency);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.MultiblockElectrolyzer")
	@ZenRegister
	public static class MultiblockElectrolyzerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.multiblock_electrolyzer;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4, double timeMultiplier, double powerMultiplier, double processRadiation, String electrolyteGroup) {
			getRecipeHandler().ctAddRecipe(input, output1, output2, output3, output4, timeMultiplier, powerMultiplier, processRadiation, electrolyteGroup);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2, IIngredient output3, IIngredient output4) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2, output3, output4);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
		
		@ZenMethod
		public static void addElectrolyte(String electrolyteGroup, ILiquidStack fluidInput, double efficiency) {
			NCRecipes.multiblock_electrolyzer.addElectrolyte(electrolyteGroup, CTHelper.buildAdditionFluidIngredient(fluidInput), efficiency);
		}
		
		@ZenMethod
		public static void removeElectrolyte(String electrolyteGroup, ILiquidStack fluidInput) {
			NCRecipes.multiblock_electrolyzer.removeElectrolyte(electrolyteGroup, CTHelper.buildAdditionFluidIngredient(fluidInput));
		}
		
		@ZenMethod
		public static void removeElectrolyteGroup(String electrolyteGroup) {
			NCRecipes.multiblock_electrolyzer.removeElectrolyteGroup(electrolyteGroup);
		}
		
		@ZenMethod
		public static void removeAllElectrolyteGroups() {
			NCRecipes.multiblock_electrolyzer.removeAllElectrolyteGroups();
		}
	}
	
	@ZenClass("mods.nuclearcraft.ElectrolyzerCathode")
	@ZenRegister
	public static class ElectrolyzerCathodeMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.electrolyzer_cathode;
		}
		
		@ZenMethod
		public static void add(IIngredient input, double efficiency) {
			getRecipeHandler().ctAddRecipe(input, efficiency);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.ElectrolyzerAnode")
	@ZenRegister
	public static class ElectrolyzerAnodeMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.electrolyzer_anode;
		}
		
		@ZenMethod
		public static void add(IIngredient input, double efficiency) {
			getRecipeHandler().ctAddRecipe(input, efficiency);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.MultiblockDistiller")
	@ZenRegister
	public static class MultiblockDistillerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.multiblock_distiller;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output1, IIngredient output2, @Optional(valueDouble = 1D) double timeMultiplier, @Optional(valueDouble = 1D) double powerMultiplier, @Optional double processRadiation) {
			getRecipeHandler().ctAddRecipe(input1, input2, output1, output2, timeMultiplier, powerMultiplier, processRadiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FissionModerator")
	@ZenRegister
	public static class FissionModeratorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fission_moderator;
		}
		
		@ZenMethod
		public static void add(IIngredient input, int fluxFactor, double efficiency) {
			getRecipeHandler().ctAddRecipe(input, fluxFactor, efficiency);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FissionReflector")
	@ZenRegister
	public static class FissionReflectorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fission_reflector;
		}
		
		@ZenMethod
		public static void add(IIngredient input, double efficiency, double reflectivity) {
			getRecipeHandler().ctAddRecipe(input, efficiency, reflectivity);
		}
		
		@ZenMethod
		public static void remove(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeAll() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FissionIrradiator")
	@ZenRegister
	public static class FissionIrradiatorMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fission_irradiator;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, long fluxRequired, double heatPerFlux, double efficiency, @Optional(valueLong = 0) long minFluxPerTick, @Optional(valueLong = -1) long maxFluxPerTick, @Optional double radiation) {
			getRecipeHandler().ctAddRecipe(input, output, fluxRequired, heatPerFlux, efficiency, minFluxPerTick, maxFluxPerTick, radiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.PebbleFission")
	@ZenRegister
	public static class PebbleFissionMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.pebble_fission;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int time, int heat, double efficiency, int criticality, double decayFactor, boolean selfPriming, double radiation) {
			getRecipeHandler().ctAddRecipe(input, output, time, heat, efficiency, criticality, decayFactor, selfPriming, radiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.SolidFission")
	@ZenRegister
	public static class SolidFissionMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.solid_fission;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int time, int heat, double efficiency, int criticality, double decayFactor, boolean selfPriming, double radiation) {
			getRecipeHandler().ctAddRecipe(input, output, time, heat, efficiency, criticality, decayFactor, selfPriming, radiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FissionHeating")
	@ZenRegister
	public static class FissionHeatingMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fission_heating;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int heatPerInputMB) {
			getRecipeHandler().ctAddRecipe(input, output, heatPerInputMB);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.SaltFission")
	@ZenRegister
	public static class SaltFissionMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.salt_fission;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, double time, int heat, double efficiency, int criticality, double decayFactor, boolean selfPriming, double radiation) {
			getRecipeHandler().ctAddRecipe(input, output, time, heat, efficiency, criticality, decayFactor, selfPriming, radiation);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.FissionEmergencyCooling")
	@ZenRegister
	public static class FissionEmergencyCoolingMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.fission_emergency_cooling;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int coolingPerInputMB) {
			getRecipeHandler().ctAddRecipe(input, output, coolingPerInputMB);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	// TODO
	@ZenClass("mods.nuclearcraft.HeatExchanger")
	@ZenRegister
	public static class HeatExchangerMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.heat_exchanger;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int heatRequired, int temperatureIn, int temperatureOut) {
			getRecipeHandler().ctAddRecipe(input, output, heatRequired, temperatureIn, temperatureOut);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	// TODO
	@ZenClass("mods.nuclearcraft.Condenser")
	@ZenRegister
	public static class CondenserMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.condenser;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, int coolingRequired, int condensingTemperature) {
			getRecipeHandler().ctAddRecipe(input, output, coolingRequired, condensingTemperature);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.Turbine")
	@ZenRegister
	public static class TurbineMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.turbine;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, double powerPerMB, double expansionLevel, double spinUpMultiplier, @Optional(value = "cloud") String particleEffect, @Optional(valueDouble = 1D / 23.2D) double particleSpeedMultiplier) {
			getRecipeHandler().ctAddRecipe(input, output, powerPerMB, expansionLevel, spinUpMultiplier, particleEffect, particleSpeedMultiplier);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.RadiationScrubber")
	@ZenRegister
	public static class RadiationScrubberMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.radiation_scrubber;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input1, IIngredient input2, IIngredient output1, IIngredient output2, long processTime, long processPower, double processEfficiency) {
			getRecipeHandler().ctAddRecipe(input1, input2, output1, output2, processTime, processPower, processEfficiency);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1, IIngredient input2) {
			getRecipeHandler().ctRemoveRecipeWithInput(input1, input2);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1, IIngredient output2) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output1, output2);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.RadiationBlockMutation")
	@ZenRegister
	public static class RadiationBlockMutationMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.radiation_block_mutation;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, double radiationThreshold) {
			getRecipeHandler().ctAddRecipe(input, output, radiationThreshold);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
	
	@ZenClass("mods.nuclearcraft.RadiationBlockPurification")
	@ZenRegister
	public static class RadiationBlockPurificationMethods {
		
		@ZenMethod
		public static BasicRecipeHandler getRecipeHandler() {
			return NCRecipes.radiation_block_purification;
		}
		
		@ZenMethod
		public static void addRecipe(IIngredient input, IIngredient output, double radiationThreshold) {
			getRecipeHandler().ctAddRecipe(input, output, radiationThreshold);
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input) {
			getRecipeHandler().ctRemoveRecipeWithInput(input);
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output) {
			getRecipeHandler().ctRemoveRecipeWithOutput(output);
		}
		
		@ZenMethod
		public static void removeAllRecipes() {
			getRecipeHandler().ctRemoveAllRecipes();
		}
	}
}
