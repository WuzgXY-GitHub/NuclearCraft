#loader nc_preinit

import mods.nuclearcraft.EnergyProcessorBuilder;
import mods.nuclearcraft.UpgradableEnergyProcessorBuilder;
import mods.nuclearcraft.ProcessorBuilderHelper.bigSlot;
import mods.nuclearcraft.ProcessorBuilderHelper.standardSlot;

UpgradableEnergyProcessorBuilder("atmosphere_collector")
    .setFluidOutputSlots([bigSlot(112, 31)])
    .setOutputTankCapacity(32000)
    .setDefaultProcessTime(20.0)
    .setDefaultProcessPower(100.0)
	.setProgressBarGuiXYWHUV(52, 30, 34, 25, 176, 3)
	.setJeiClickAreaXYWH(52, 33, 34, 22)
	.setJeiTooltipXYWH(52, 33, 34, 22)
	.setJeiBackgroundXYWH(50, 28, 89, 30)
    .buildAndRegister();

UpgradableEnergyProcessorBuilder("fluid_pressurizer")
    .setFluidInputSlots([standardSlot(56, 35)])
    .setFluidOutputSlots([bigSlot(112, 31)])
    .setInputTankCapacity(256000)
    .setOutputTankCapacity(16000)
    .setDefaultProcessTime(400.0)
    .setDefaultProcessPower(50.0)
	.setJeiClickAreaXYWH(73, 34, 38, 18)
    .buildAndRegister();

UpgradableEnergyProcessorBuilder("heat_exchanger_machine")
	.setRecipeHandlerName("heat_exchanger_machine")
	.setFluidInputSlots([standardSlot(44, 20), standardSlot(44, 42)])
	.setFluidOutputSlots([standardSlot(114, 20), standardSlot(114, 42)])
	.setDefaultProcessPower(0.0)
	.setDefaultProcessTime(1.0)
	.setInputTankCapacity(256000)
	.setOutputTankCapacity(256000)
	.setProgressBarGuiXYWHUV(65, 23, 45, 32, 176, 0)
	.setJeiClickAreaXYWH(65, 23, 45, 32)
	.setSpeedUpgradeSlot(133, 64, 16, 16)
	.buildAndRegister();

UpgradableEnergyProcessorBuilder("furnace_generator")
	.setDefaultProcessPower(20.0)
	.setDefaultProcessTime(1.0)
	.setItemInputSlots([standardSlot(56, 35)])
	.setItemOutputSlots([standardSlot(143, 35)])
	.setIsGenerator(true)
	.setJeiClickAreaXYWH(73, 34, 38, 18)
	.setJeiBackgroundXYWH(50, 29, 115, 28)
	.buildAndRegister();
