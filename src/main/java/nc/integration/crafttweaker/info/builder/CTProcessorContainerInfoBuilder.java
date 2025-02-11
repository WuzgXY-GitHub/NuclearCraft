package nc.integration.crafttweaker.info.builder;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.creativetabs.ICreativeTab;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.creativetabs.MCCreativeTab;
import nc.Global;
import nc.container.processor.ContainerProcessorImpl.*;
import nc.gui.GuiInfoTileFunction;
import nc.gui.processor.GuiProcessorImpl.*;
import nc.integration.crafttweaker.CTRegistration;
import nc.integration.jei.wrapper.JEIRecipeWrapperImpl.*;
import nc.network.tile.processor.EnergyProcessorUpdatePacket;
import nc.tab.NCTabs;
import nc.tile.processor.TileProcessorImpl.*;
import nc.tile.processor.info.builder.ProcessorContainerInfoBuilderImpl.*;
import nc.util.*;
import nc.util.ReflectionHelper.ConstructorWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import stanhebben.zenscript.annotations.*;

import static nc.NuclearCraft.proxy;

public class CTProcessorContainerInfoBuilder {
	
	@ZenClass("mods.nuclearcraft.ProcessorBuilderHelper")
	@ZenRegister
	public static class CTProcessorContainerInfoBuilderHelper {
		
		@ZenMethod
		public static int[] standardSlot(int x, int y) {
			return ContainerInfoHelper.standardSlot(x, y);
		}
		
		@ZenMethod
		public static int[] bigSlot(int x, int y) {
			return ContainerInfoHelper.bigSlot(x, y);
		}
		
		@ZenMethod("getCreativeTabNC")
		public static ICreativeTab ctCreativeTabNC(String name) {
			return MCCreativeTab.getICreativeTab(NCTabs.getCreativeTab(name));
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static Class ctCreateGuiBasicEnergyProcessor(String name) {
		return proxy.clientGet(() -> ReflectionHelper.cloneClass(GuiBasicEnergyProcessorDyn.class, "Gui" + name + "Dyn"));
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static GuiInfoTileFunction ctGetGuiBasicEnergyProcessorFunction(Class guiClass) {
		return LambdaHelper.let(new ConstructorWrapper<>((Class<? extends GuiBasicEnergyProcessor<TileBasicEnergyProcessorDyn>>) guiClass, Container.class, EntityPlayer.class, TileBasicEnergyProcessorDyn.class, String.class), x -> proxy.clientGet(() -> x::newInstance));
	}
	
	@ZenClass("mods.nuclearcraft.EnergyProcessorBuilder")
	@ZenRegister
	public static class CTEnergyProcessorContainerInfoBuilder extends BasicProcessorContainerInfoBuilder<TileBasicEnergyProcessorDyn, EnergyProcessorUpdatePacket> {
		
		@ZenConstructor
		public CTEnergyProcessorContainerInfoBuilder(String name) {
			this(name, ReflectionHelper.cloneClass(ContainerBasicEnergyProcessorDyn.class, "Container" + name + "Dyn"), ctCreateGuiBasicEnergyProcessor(name));
		}
		
		@SuppressWarnings({"rawtypes", "unchecked"})
		protected CTEnergyProcessorContainerInfoBuilder(String name, Class<? extends ContainerBasicEnergyProcessor<TileBasicEnergyProcessorDyn>> containerClass, Class guiClass) {
			super(Global.MOD_ID, name, TileBasicEnergyProcessorDyn.class, TileBasicEnergyProcessorDyn::new, containerClass, new ConstructorWrapper<>(containerClass, EntityPlayer.class, TileBasicEnergyProcessorDyn.class)::newInstance, proxy.clientGet(() -> guiClass), ctGetGuiBasicEnergyProcessorFunction(guiClass));
		}
		
		@ZenMethod("buildAndRegister")
		public void ctBuildAndRegister() {
			CTRegistration.registerProcessor(this, TileBasicEnergyProcessorDyn::new, JEIBasicEnergyProcessorRecipeWrapperDyn.class);
		}
		
		@ZenMethod("setGuiWH")
		public CTEnergyProcessorContainerInfoBuilder ctSetGuiWH(int w, int h) {
			setGuiWH(w, h);
			return this;
		}
		
		@ZenMethod("setItemInputSlots")
		public CTEnergyProcessorContainerInfoBuilder ctSetItemInputSlots(int[]... slots) {
			setItemInputSlots(slots);
			return this;
		}
		
		@ZenMethod("setFluidInputSlots")
		public CTEnergyProcessorContainerInfoBuilder ctSetFluidInputSlots(int[]... slots) {
			setFluidInputSlots(slots);
			return this;
		}
		
		@ZenMethod("setItemOutputSlots")
		public CTEnergyProcessorContainerInfoBuilder ctSetItemOutputSlots(int[]... slots) {
			setItemOutputSlots(slots);
			return this;
		}
		
		@ZenMethod("setFluidOutputSlots")
		public CTEnergyProcessorContainerInfoBuilder ctSetFluidOutputSlots(int[]... slots) {
			setFluidOutputSlots(slots);
			return this;
		}
		
		@ZenMethod("setPlayerGuiXY")
		public CTEnergyProcessorContainerInfoBuilder ctSetPlayerGuiXY(int x, int y) {
			setPlayerGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setProgressBarGuiXYWHUV")
		public CTEnergyProcessorContainerInfoBuilder ctSetProgressBarGuiXYWHUV(int x, int y, int w, int h, int u, int v) {
			setProgressBarGuiXYWHUV(x, y, w, h, u, v);
			return this;
		}
		
		@ZenMethod("setEnergyBarGuiXYWHUV")
		public CTEnergyProcessorContainerInfoBuilder ctSetEnergyBarGuiXYWHUV(int x, int y, int w, int h, int u, int v) {
			setEnergyBarGuiXYWHUV(x, y, w, h, u, v);
			return this;
		}
		
		@ZenMethod("setMachineConfigGuiXY")
		public CTEnergyProcessorContainerInfoBuilder ctSetMachineConfigGuiXY(int x, int y) {
			setMachineConfigGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setRedstoneControlGuiXY")
		public CTEnergyProcessorContainerInfoBuilder ctSetRedstoneControlGuiXY(int x, int y) {
			setRedstoneControlGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setRecipeHandlerName")
		public CTEnergyProcessorContainerInfoBuilder ctSetRecipeHandlerName(String recipeHandlerName) {
			setRecipeHandlerName(recipeHandlerName);
			return this;
		}
		
		@ZenMethod("setJeiCategoryEnabled")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiCategoryEnabled(boolean jeiCategoryEnabled) {
			setJeiCategoryEnabled(jeiCategoryEnabled);
			return this;
		}
		
		@ZenMethod("setJeiCategoryUid")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiCategoryUid(String jeiCategoryUid) {
			setJeiCategoryUid(jeiCategoryUid);
			return this;
		}
		
		@ZenMethod("setJeiTitle")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiTitle(String jeiTitle) {
			setJeiTitle(jeiTitle);
			return this;
		}
		
		@ZenMethod("setJeiTexture")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiTexture(String jeiTexture) {
			setJeiTexture(jeiTexture);
			return this;
		}
		
		@ZenMethod("setJeiBackgroundXYWH")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiBackgroundXYWH(int x, int y, int w, int h) {
			setJeiBackgroundXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setJeiTooltipXYWH")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiTooltipXYWH(int x, int y, int w, int h) {
			setJeiTooltipXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setJeiClickAreaXYWH")
		public CTEnergyProcessorContainerInfoBuilder ctSetJeiClickAreaXYWH(int x, int y, int w, int h) {
			setJeiClickAreaXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setStandardJeiAlternateTitle")
		public CTEnergyProcessorContainerInfoBuilder ctSetStandardJeiAlternateTitle() {
			setStandardJeiAlternateTitle();
			return this;
		}
		
		@ZenMethod("setStandardJeiAlternateTexture")
		public CTEnergyProcessorContainerInfoBuilder ctSetStandardJeiAlternateTexture() {
			setStandardJeiAlternateTexture();
			return this;
		}
		
		@ZenMethod("standardExtend")
		public CTEnergyProcessorContainerInfoBuilder ctStandardExtend(int x, int y) {
			standardExtend(x, y);
			return this;
		}
		
		@ZenMethod("disableProgressBar")
		public CTEnergyProcessorContainerInfoBuilder ctDisableProgressBar() {
			disableProgressBar();
			return this;
		}
		
		@ZenMethod("setCreativeTab")
		public CTEnergyProcessorContainerInfoBuilder ctSetCreativeTab(ICreativeTab tab) {
			setCreativeTab(CraftTweakerMC.getCreativeTabs(tab));
			return this;
		}
		
		@ZenMethod("setParticles")
		public CTEnergyProcessorContainerInfoBuilder ctSetParticles(String... particles) {
			setParticles(particles);
			return this;
		}
		
		@ZenMethod("setInputTankCapacity")
		public CTEnergyProcessorContainerInfoBuilder ctSetInputTankCapacity(int capacity) {
			setInputTankCapacity(capacity);
			return this;
		}
		
		@ZenMethod("setOutputTankCapacity")
		public CTEnergyProcessorContainerInfoBuilder ctSetOutputTankCapacity(int capacity) {
			setOutputTankCapacity(capacity);
			return this;
		}
		
		@ZenMethod("setDefaultProcessTime")
		public CTEnergyProcessorContainerInfoBuilder ctSetDefaultProcessTime(double processTime) {
			setDefaultProcessTime(processTime);
			return this;
		}
		
		@ZenMethod("setDefaultProcessPower")
		public CTEnergyProcessorContainerInfoBuilder ctSetDefaultProcessPower(double processPower) {
			setDefaultProcessPower(processPower);
			return this;
		}
		
		@ZenMethod("setIsGenerator")
		public CTEnergyProcessorContainerInfoBuilder ctSetIsGenerator(boolean isGenerator) {
			setIsGenerator(isGenerator);
			return this;
		}
		
		@ZenMethod("setConsumesInputs")
		public CTEnergyProcessorContainerInfoBuilder ctSetConsumesInputs(boolean consumesInputs) {
			setConsumesInputs(consumesInputs);
			return this;
		}
		
		@ZenMethod("setLosesProgress")
		public CTEnergyProcessorContainerInfoBuilder ctSetLosesProgress(boolean losesProgress) {
			setLosesProgress(losesProgress);
			return this;
		}
		
		@ZenMethod("setOCComponentName")
		public CTEnergyProcessorContainerInfoBuilder ctSetOCComponentName(String ocComponentName) {
			setOCComponentName(ocComponentName);
			return this;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static Class ctCreateGuiBasicUpgradableEnergyProcessor(String name) {
		return proxy.clientGet(() -> ReflectionHelper.cloneClass(GuiBasicUpgradableEnergyProcessorDyn.class, "Gui" + name + "Dyn"));
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static GuiInfoTileFunction ctGetGuiBasicUpgradableEnergyProcessorFunction(Class guiClass) {
		return LambdaHelper.let(new ConstructorWrapper<>((Class<? extends GuiBasicUpgradableEnergyProcessor<TileBasicUpgradableEnergyProcessorDyn>>) guiClass, Container.class, EntityPlayer.class, TileBasicUpgradableEnergyProcessorDyn.class, String.class), x -> proxy.clientGet(() -> x::newInstance));
	}
	
	@ZenClass("mods.nuclearcraft.UpgradableEnergyProcessorBuilder")
	@ZenRegister
	public static class CTUpgradableEnergyProcessorContainerInfoBuilder extends BasicUpgradableProcessorContainerInfoBuilder<TileBasicUpgradableEnergyProcessorDyn, EnergyProcessorUpdatePacket> {
		
		@ZenConstructor
		public CTUpgradableEnergyProcessorContainerInfoBuilder(String name) {
			this(name, ReflectionHelper.cloneClass(ContainerBasicUpgradableEnergyProcessorDyn.class, "Container" + name + "Dyn"), ctCreateGuiBasicUpgradableEnergyProcessor(name));
		}
		
		@SuppressWarnings({"rawtypes", "unchecked"})
		protected CTUpgradableEnergyProcessorContainerInfoBuilder(String name, Class<? extends ContainerBasicUpgradableEnergyProcessor<TileBasicUpgradableEnergyProcessorDyn>> containerClass, Class guiClass) {
			super(Global.MOD_ID, name, TileBasicUpgradableEnergyProcessorDyn.class, TileBasicUpgradableEnergyProcessorDyn::new, containerClass, new ConstructorWrapper<>(containerClass, EntityPlayer.class, TileBasicUpgradableEnergyProcessorDyn.class)::newInstance, proxy.clientGet(() -> guiClass), ctGetGuiBasicUpgradableEnergyProcessorFunction(guiClass));
		}
		
		@ZenMethod("buildAndRegister")
		public void ctBuildAndRegister() {
			CTRegistration.registerProcessor(this, TileBasicUpgradableEnergyProcessorDyn::new, JEIBasicUpgradableEnergyProcessorRecipeWrapperDyn.class);
		}
		
		@ZenMethod("setGuiWH")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetGuiWH(int w, int h) {
			setGuiWH(w, h);
			return this;
		}
		
		@ZenMethod("setItemInputSlots")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetItemInputSlots(int[]... slots) {
			setItemInputSlots(slots);
			return this;
		}
		
		@ZenMethod("setFluidInputSlots")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetFluidInputSlots(int[]... slots) {
			setFluidInputSlots(slots);
			return this;
		}
		
		@ZenMethod("setItemOutputSlots")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetItemOutputSlots(int[]... slots) {
			setItemOutputSlots(slots);
			return this;
		}
		
		@ZenMethod("setFluidOutputSlots")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetFluidOutputSlots(int[]... slots) {
			setFluidOutputSlots(slots);
			return this;
		}
		
		@ZenMethod("setPlayerGuiXY")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetPlayerGuiXY(int x, int y) {
			setPlayerGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setProgressBarGuiXYWHUV")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetProgressBarGuiXYWHUV(int x, int y, int w, int h, int u, int v) {
			setProgressBarGuiXYWHUV(x, y, w, h, u, v);
			return this;
		}
		
		@ZenMethod("setEnergyBarGuiXYWHUV")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetEnergyBarGuiXYWHUV(int x, int y, int w, int h, int u, int v) {
			setEnergyBarGuiXYWHUV(x, y, w, h, u, v);
			return this;
		}
		
		@ZenMethod("setMachineConfigGuiXY")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetMachineConfigGuiXY(int x, int y) {
			setMachineConfigGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setRedstoneControlGuiXY")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetRedstoneControlGuiXY(int x, int y) {
			setRedstoneControlGuiXY(x, y);
			return this;
		}
		
		@ZenMethod("setRecipeHandlerName")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetRecipeHandlerName(String recipeHandlerName) {
			setRecipeHandlerName(recipeHandlerName);
			return this;
		}
		
		@ZenMethod("setJeiCategoryEnabled")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiCategoryEnabled(boolean jeiCategoryEnabled) {
			setJeiCategoryEnabled(jeiCategoryEnabled);
			return this;
		}
		
		@ZenMethod("setJeiCategoryUid")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiCategoryUid(String jeiCategoryUid) {
			setJeiCategoryUid(jeiCategoryUid);
			return this;
		}
		
		@ZenMethod("setJeiTitle")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiTitle(String jeiTitle) {
			setJeiTitle(jeiTitle);
			return this;
		}
		
		@ZenMethod("setJeiTexture")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiTexture(String jeiTexture) {
			setJeiTexture(jeiTexture);
			return this;
		}
		
		@ZenMethod("setJeiBackgroundXYWH")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiBackgroundXYWH(int x, int y, int w, int h) {
			setJeiBackgroundXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setJeiTooltipXYWH")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiTooltipXYWH(int x, int y, int w, int h) {
			setJeiTooltipXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setJeiClickAreaXYWH")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetJeiClickAreaXYWH(int x, int y, int w, int h) {
			setJeiClickAreaXYWH(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setStandardJeiAlternateTitle")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetStandardJeiAlternateTitle() {
			setStandardJeiAlternateTitle();
			return this;
		}
		
		@ZenMethod("setStandardJeiAlternateTexture")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetStandardJeiAlternateTexture() {
			setStandardJeiAlternateTexture();
			return this;
		}
		
		@ZenMethod("standardExtend")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctStandardExtend(int x, int y) {
			standardExtend(x, y);
			return this;
		}
		
		@ZenMethod("disableProgressBar")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctDisableProgressBar() {
			disableProgressBar();
			return this;
		}
		
		@ZenMethod("setCreativeTab")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetCreativeTab(ICreativeTab tab) {
			setCreativeTab(CraftTweakerMC.getCreativeTabs(tab));
			return this;
		}
		
		@ZenMethod("setParticles")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetParticles(String... particles) {
			setParticles(particles);
			return this;
		}
		
		@ZenMethod("setInputTankCapacity")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetInputTankCapacity(int capacity) {
			setInputTankCapacity(capacity);
			return this;
		}
		
		@ZenMethod("setOutputTankCapacity")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetOutputTankCapacity(int capacity) {
			setOutputTankCapacity(capacity);
			return this;
		}
		
		@ZenMethod("setDefaultProcessTime")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetDefaultProcessTime(double processTime) {
			setDefaultProcessTime(processTime);
			return this;
		}
		
		@ZenMethod("setDefaultProcessPower")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetDefaultProcessPower(double processPower) {
			setDefaultProcessPower(processPower);
			return this;
		}
		
		@ZenMethod("setIsGenerator")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetIsGenerator(boolean isGenerator) {
			setIsGenerator(isGenerator);
			return this;
		}
		
		@ZenMethod("setConsumesInputs")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetConsumesInputs(boolean consumesInputs) {
			setConsumesInputs(consumesInputs);
			return this;
		}
		
		@ZenMethod("setLosesProgress")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetLosesProgress(boolean losesProgress) {
			setLosesProgress(losesProgress);
			return this;
		}
		
		@ZenMethod("setOCComponentName")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetOCComponentName(String ocComponentName) {
			setOCComponentName(ocComponentName);
			return this;
		}
		
		@ZenMethod("setSpeedUpgradeSlot")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetSpeedUpgradeSlot(int x, int y, int w, int h) {
			setSpeedUpgradeSlot(x, y, w, h);
			return this;
		}
		
		@ZenMethod("setEnergyUpgradeSlot")
		public CTUpgradableEnergyProcessorContainerInfoBuilder ctSetEnergyUpgradeSlot(int x, int y, int w, int h) {
			setEnergyUpgradeSlot(x, y, w, h);
			return this;
		}
	}
}
