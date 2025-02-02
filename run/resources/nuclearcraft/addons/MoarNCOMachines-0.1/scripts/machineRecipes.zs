import mods.nuclearcraft.BasicRecipeHandler;
import mods.nuclearcraft.Centrifuge;

var atmosphereCollectorRecipes = BasicRecipeHandler.get("atmosphere_collector");
var fluidPressurizerRecipes = BasicRecipeHandler.get("fluid_pressurizer");
var heatExchangerRecipes = BasicRecipeHandler.get("heat_exchanger_machine");
var furnaceGeneratorRecipes = BasicRecipeHandler.get("furnace_generator");

atmosphereCollectorRecipes.addRecipe([<fluid:compressed_air>*1000, 1.0, 1.0, 0.0]);

fluidPressurizerRecipes.addRecipe([<fluid:steam>*4, <fluid:high_pressure_steam>, 1.0/400.0, 1.0/5.0, 0.0]);
fluidPressurizerRecipes.addRecipe([<fluid:low_pressure_steam>*4, <fluid:high_pressure_steam>, 1.0/400.0, 1.0/5.0, 0.0]);

heatExchangerRecipes.addRecipe([<fluid:water>, <fluid:high_pressure_steam>*4, <fluid:low_pressure_steam>*4, <fluid:low_pressure_steam>*16, 1.0, 1.0, 0.0]);

furnaceGeneratorRecipes.addRecipe([<ore:coal>, <contenttweaker:ash>, 1200.0, 1.0, 0.0]);

Centrifuge.addRecipe(<fluid:compressed_air>*10000, <fluid:nitrogen>*7000, <fluid:oxygen>*2500, <fluid:carbon_dioxide>*450, <fluid:helium>*50, null, null, 2.0, 2.0, 0.0);
