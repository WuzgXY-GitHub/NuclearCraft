package nc.integration.jei.category.info;

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import nc.integration.jei.category.*;
import nc.integration.jei.wrapper.*;
import nc.recipe.*;
import nc.util.*;
import net.minecraft.item.ItemStack;

import java.util.List;

public abstract class JEICategoryInfo<WRAPPER extends JEIRecipeWrapper, CATEGORY extends JEIRecipeCategory<WRAPPER, CATEGORY, CATEGORY_INFO>, CATEGORY_INFO extends JEICategoryInfo<WRAPPER, CATEGORY, CATEGORY_INFO>> {
	
	public final String modId;
	public final String name;
	
	public final JEIRecipeCategoryFunction<WRAPPER, CATEGORY, CATEGORY_INFO> jeiCategoryFunction;
	
	public final Class<WRAPPER> jeiRecipeClass;
	public final JEIRecipeWrapperFunction<WRAPPER, CATEGORY, CATEGORY_INFO> jeiRecipeWrapperFunction;
	
	public final List<Object> jeiCrafters;
	
	public final List<JEIContainerConnection> jeiContainerConnections;
	
	protected JEICategoryInfo(String modId, String name, JEIRecipeCategoryFunction<WRAPPER, CATEGORY, CATEGORY_INFO> jeiCategoryFunction, Class<WRAPPER> jeiRecipeClass, JEIRecipeWrapperFunction<WRAPPER, CATEGORY, CATEGORY_INFO> jeiRecipeWrapperFunction, List<Object> jeiCrafters, List<JEIContainerConnection> jeiContainerConnections) {
		this.modId = modId;
		this.name = name;
		
		this.jeiCategoryFunction = jeiCategoryFunction;
		
		this.jeiRecipeClass = jeiRecipeClass;
		this.jeiRecipeWrapperFunction = jeiRecipeWrapperFunction;
		
		this.jeiCrafters = jeiCrafters;
		
		this.jeiContainerConnections = jeiContainerConnections;
	}
	
	public String getModId() {
		return modId;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract boolean isJEICategoryEnabled();
	
	public abstract int getItemInputSize();
	
	public abstract int getFluidInputSize();
	
	public abstract int getItemOutputSize();
	
	public abstract int getFluidOutputSize();
	
	public abstract int[] getItemInputSlots();
	
	public abstract int[] getItemOutputSlots();
	
	public abstract int[] getFluidInputTanks();
	
	public abstract int[] getFluidOutputTanks();
	
	public abstract List<int[]> getItemInputGuiXYWH();
	
	public abstract List<int[]> getFluidInputGuiXYWH();
	
	public abstract List<int[]> getItemOutputGuiXYWH();
	
	public abstract List<int[]> getFluidOutputGuiXYWH();
	
	public abstract List<int[]> getItemInputStackXY();
	
	public abstract List<int[]> getItemOutputStackXY();
	
	public abstract BasicRecipeHandler getRecipeHandler();
	
	public abstract String getJEICategoryUid();
	
	public abstract String getJEITitle();
	
	public abstract String getJEITexture();
	
	public abstract int getJEIBackgroundX();
	
	public abstract int getJEIBackgroundY();
	
	public abstract int getJEIBackgroundW();
	
	public abstract int getJEIBackgroundH();
	
	public abstract int getJEITooltipX();
	
	public abstract int getJEITooltipY();
	
	public abstract int getJEITooltipW();
	
	public abstract int getJEITooltipH();
	
	@SuppressWarnings("unchecked")
	public CATEGORY getJEICategory(IGuiHelper guiHelper) {
		return jeiCategoryFunction.apply(guiHelper, (CATEGORY_INFO) this);
	}
	
	public void registerJEICategory(IModRegistry registry, IJeiHelpers jeiHelpers, IGuiHelper guiHelper, IRecipeTransferRegistry transferRegistry) {
		if (isJEICategoryEnabled()) {
			registry.addRecipes(getJEIRecipes(guiHelper));
			
			CATEGORY category = getJEICategory(guiHelper);
			registry.addRecipeCategories(category);
			registry.addRecipeHandlers(category);
			
			addJEIRecipeCatalysts(registry);
			addRecipeClickAreas(registry);
			addRecipeTransferHandlers(transferRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public WRAPPER getJEIRecipe(IGuiHelper guiHelper, BasicRecipe recipe) {
		return jeiRecipeWrapperFunction.apply(guiHelper, (CATEGORY_INFO) this, recipe);
	}
	
	public List<WRAPPER> getJEIRecipes(IGuiHelper guiHelper) {
		return StreamHelper.map(getRecipeHandler().getRecipeList(), x -> getJEIRecipe(guiHelper, x));
	}
	
	public void addJEIRecipeCatalysts(IModRegistry registry) {
		for (Object crafter : jeiCrafters) {
			ItemStack crafterStack = StackHelper.fixItemStack(crafter);
			if (crafterStack != null) {
				registry.addRecipeCatalyst(crafterStack, getJEICategoryUid());
			}
		}
	}
	
	public void addRecipeClickAreas(IModRegistry registry) {
		for (JEIContainerConnection connection : jeiContainerConnections) {
			registry.addRecipeClickArea(connection.guiClass, connection.jeiClickAreaX, connection.jeiClickAreaY, connection.jeiClickAreaW, connection.jeiClickAreaH, getJEICategoryUid());
		}
	}
	
	public void addRecipeTransferHandlers(IRecipeTransferRegistry transferRegistry) {
		for (JEIContainerConnection connection : jeiContainerConnections) {
			transferRegistry.addRecipeTransferHandler(connection.containerClass, getJEICategoryUid(), connection.itemInputStart, connection.itemInputSize, connection.playerInventoryStart, 36);
		}
	}
}
