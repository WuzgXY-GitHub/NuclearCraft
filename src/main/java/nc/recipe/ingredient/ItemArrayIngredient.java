package nc.recipe.ingredient;

import com.google.common.collect.Lists;
import crafttweaker.api.item.IngredientOr;
import it.unimi.dsi.fastutil.ints.*;
import nc.recipe.*;
import nc.util.StreamHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;
import java.util.*;

public class ItemArrayIngredient implements IItemIngredient {
	
	public final List<IItemIngredient> ingredientList;
	public final @Nullable ItemStack cachedStack;
	
	public ItemArrayIngredient(IItemIngredient... ingredients) {
		this(Lists.newArrayList(ingredients));
	}
	
	public ItemArrayIngredient(List<IItemIngredient> ingredientList) {
		this.ingredientList = ingredientList;
		cachedStack = ingredientList.stream().map(IItemIngredient::getStack).filter(Objects::nonNull).findFirst().orElse(null);
	}
	
	@Override
	public void init() {
		ingredientList.forEach(IIngredient::init);
	}
	
	@Override
	public ItemStack getStack() {
		return isValid() ? cachedStack.copy() : null;
	}
	
	@Override
	public List<ItemStack> getInputStackList() {
		return StreamHelper.flatMap(ingredientList, IItemIngredient::getInputStackList);
	}
	
	@Override
	public List<ItemStack> getOutputStackList() {
		return isValid() ? Lists.newArrayList(getStack()) : new ArrayList<>();
	}
	
	@Override
	public int getMaxStackSize(int ingredientNumber) {
		return ingredientList.get(ingredientNumber).getMaxStackSize(0);
	}
	
	@Override
	public void setMaxStackSize(int stackSize) {
		for (IItemIngredient ingredient : ingredientList) {
			ingredient.setMaxStackSize(stackSize);
		}
		cachedStack.setCount(stackSize);
	}
	
	@Override
	public String getIngredientName() {
		return getIngredientNamesConcat();
	}
	
	@Override
	public String getIngredientNamesConcat() {
		StringBuilder names = new StringBuilder();
		for (IItemIngredient ingredient : ingredientList) {
			names.append(", ").append(ingredient.getIngredientName());
		}
		return "{ " + names.substring(2) + " }";
	}
	
	public String getIngredientRecipeString() {
		StringBuilder names = new StringBuilder();
		for (IItemIngredient ingredient : ingredientList) {
			names.append(", ").append(ingredient.getMaxStackSize(0)).append(" x ").append(ingredient.getIngredientName());
		}
		return "{ " + names.substring(2) + " }";
	}
	
	@Override
	public IntList getFactors() {
		IntList list = new IntArrayList();
		for (IItemIngredient ingredient : ingredientList) {
			list.addAll(ingredient.getFactors());
		}
		return new IntArrayList(list);
	}
	
	@Override
	public IItemIngredient getFactoredIngredient(int factor) {
		List<IItemIngredient> list = new ArrayList<>();
		for (IItemIngredient ingredient : ingredientList) {
			list.add(ingredient.getFactoredIngredient(factor));
		}
		return new ItemArrayIngredient(list);
	}
	
	@Override
	public IngredientMatchResult match(Object object, IngredientSorption sorption) {
		if (object instanceof ItemArrayIngredient arrayIngredient) {
			loop:
			for (IItemIngredient ingredient : ingredientList) {
				for (IItemIngredient ingr : arrayIngredient.ingredientList) {
					if (ingredient.match(ingr, sorption).matches()) {
						continue loop;
					}
				}
				return IngredientMatchResult.FAIL;
			}
			return IngredientMatchResult.PASS_0;
		}
		else {
			for (int i = 0; i < ingredientList.size(); ++i) {
				if (ingredientList.get(i).match(object, sorption).matches()) {
					return new IngredientMatchResult(true, i);
				}
			}
		}
		return IngredientMatchResult.FAIL;
	}
	
	@Override
	public boolean isValid() {
		return cachedStack != null;
	}
	
	@Override
	public boolean isEmpty() {
		return ingredientList.stream().allMatch(IIngredient::isEmpty);
	}
	
	// CraftTweaker
	
	@Override
	@Optional.Method(modid = "crafttweaker")
	public crafttweaker.api.item.IIngredient ct() {
		crafttweaker.api.item.IIngredient[] array = new crafttweaker.api.item.IIngredient[ingredientList.size()];
		for (int i = 0; i < ingredientList.size(); ++i) {
			array[i] = ingredientList.get(i).ct();
		}
		return new IngredientOr(array);
	}
}
