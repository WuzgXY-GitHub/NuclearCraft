package nc.recipe.ingredient;

import com.google.common.collect.Lists;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import it.unimi.dsi.fastutil.ints.*;
import nc.recipe.*;
import nc.util.OreDictHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

import java.util.*;

public class OreIngredient implements IItemIngredient {
	
	public final String oreName;
	public List<ItemStack> cachedStackList = null;
	public int stackSize;
	
	public OreIngredient(String oreName, int stackSize) {
		this.oreName = oreName;
		cachedStackList = OreDictHelper.getPrioritisedStackList(oreName);
		this.stackSize = stackSize;
	}
	
	@Override
	public void init() {
		cachedStackList = OreDictHelper.getPrioritisedStackList(oreName);
	}
	
	@Override
	public ItemStack getStack() {
		if (cachedStackList == null || cachedStackList.isEmpty() || cachedStackList.get(0) == null) {
			return null;
		}
		ItemStack item = cachedStackList.get(0).copy();
		item.setCount(stackSize);
		return item;
	}
	
	@Override
	public List<ItemStack> getInputStackList() {
		List<ItemStack> stackList = new ArrayList<>();
		for (ItemStack item : cachedStackList) {
			ItemStack itemStack = item.copy();
			itemStack.setCount(stackSize);
			stackList.add(itemStack);
		}
		return stackList;
	}
	
	@Override
	public List<ItemStack> getOutputStackList() {
		if (cachedStackList == null || cachedStackList.isEmpty()) {
			return Collections.emptyList();
		}
		return Lists.newArrayList(getStack());
	}
	
	@Override
	public int getMaxStackSize(int ingredientNumber) {
		return stackSize;
	}
	
	@Override
	public void setMaxStackSize(int stackSize) {
		this.stackSize = stackSize;
		for (ItemStack stack : cachedStackList) {
			stack.setCount(stackSize);
		}
	}
	
	@Override
	public String getIngredientName() {
		return "ore:" + oreName;
	}
	
	@Override
	public String getIngredientNamesConcat() {
		return getIngredientName();
	}
	
	@Override
	public IntList getFactors() {
		return new IntArrayList(Lists.newArrayList(stackSize));
	}
	
	@Override
	public IItemIngredient getFactoredIngredient(int factor) {
		return new OreIngredient(oreName, stackSize / factor);
	}
	
	@Override
	public IngredientMatchResult match(Object object, IngredientSorption sorption) {
		if (object instanceof OreIngredient oreStack) {
			if (oreStack.oreName.equals(oreName) && sorption.checkStackSize(stackSize, oreStack.stackSize)) {
				return IngredientMatchResult.PASS_0;
			}
		}
		else if (object instanceof String) {
			return new IngredientMatchResult(oreName.equals(object), 0);
		}
		else if (object instanceof ItemStack itemstack && sorption.checkStackSize(stackSize, itemstack.getCount())) {
			if (itemstack.isEmpty()) {
				return IngredientMatchResult.FAIL;
			}
			if (OreDictHelper.getOreNames(itemstack).contains(oreName)) {
				return IngredientMatchResult.PASS_0;
			}
		}
		else if (object instanceof ItemIngredient ingredient) {
			if (match(ingredient.stack, sorption).matches()) {
				return IngredientMatchResult.PASS_0;
			}
		}
		else if (object instanceof ItemArrayIngredient arrayIngredient) {
			for (IItemIngredient ingredient : arrayIngredient.ingredientList) {
				if (!match(ingredient, sorption).matches()) {
					return IngredientMatchResult.FAIL;
				}
			}
			return IngredientMatchResult.PASS_0;
		}
		return IngredientMatchResult.FAIL;
	}
	
	@Override
	public boolean isValid() {
		return cachedStackList != null && !cachedStackList.isEmpty() && cachedStackList.get(0) != null;
	}
	
	@Override
	public boolean isEmpty() {
		return stackSize <= 0;
	}
	
	// CraftTweaker
	
	@Override
	@Optional.Method(modid = "crafttweaker")
	public crafttweaker.api.item.IIngredient ct() {
		return new IngredientStack(CraftTweakerMC.getOreDict(oreName), stackSize);
	}
}
