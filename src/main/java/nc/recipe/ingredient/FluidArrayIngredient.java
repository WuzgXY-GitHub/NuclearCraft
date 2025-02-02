package nc.recipe.ingredient;

import com.google.common.collect.Lists;
import crafttweaker.api.item.IngredientOr;
import it.unimi.dsi.fastutil.ints.*;
import nc.recipe.*;
import nc.util.StreamHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;
import java.util.*;

public class FluidArrayIngredient implements IFluidIngredient {
	
	public final List<IFluidIngredient> ingredientList;
	public final @Nullable FluidStack cachedStack;
	
	public FluidArrayIngredient(IFluidIngredient... ingredients) {
		this(Lists.newArrayList(ingredients));
	}
	
	public FluidArrayIngredient(List<IFluidIngredient> ingredientList) {
		this.ingredientList = ingredientList;
		cachedStack = ingredientList.stream().map(IFluidIngredient::getStack).filter(Objects::nonNull).findFirst().orElse(null);
	}
	
	@Override
	public void init() {
		ingredientList.forEach(IIngredient::init);
	}
	
	@Override
	public FluidStack getStack() {
		return isValid() ? cachedStack.copy() : null;
	}
	
	@Override
	public List<FluidStack> getInputStackList() {
		return StreamHelper.flatMap(ingredientList, IFluidIngredient::getInputStackList);
	}
	
	@Override
	public List<FluidStack> getOutputStackList() {
		return isValid() ? Lists.newArrayList(getStack()) : new ArrayList<>();
	}
	
	@Override
	public int getMaxStackSize(int ingredientNumber) {
		return ingredientList.get(ingredientNumber).getMaxStackSize(0);
	}
	
	@Override
	public void setMaxStackSize(int stackSize) {
		for (IFluidIngredient ingredient : ingredientList) {
			ingredient.setMaxStackSize(stackSize);
		}
		cachedStack.amount = stackSize;
	}
	
	@Override
	public String getIngredientName() {
		return getIngredientNamesConcat();
	}
	
	@Override
	public String getIngredientNamesConcat() {
		StringBuilder names = new StringBuilder();
		for (IFluidIngredient ingredient : ingredientList) {
			names.append(", ").append(ingredient.getIngredientName());
		}
		return "{ " + names.substring(2) + " }";
	}
	
	public String getIngredientRecipeString() {
		StringBuilder names = new StringBuilder();
		for (IFluidIngredient ingredient : ingredientList) {
			names.append(", ").append(ingredient.getMaxStackSize(0)).append(" x ").append(ingredient.getIngredientName());
		}
		return "{ " + names.substring(2) + " }";
	}
	
	@Override
	public IntList getFactors() {
		IntList list = new IntArrayList();
		for (IFluidIngredient ingredient : ingredientList) {
			list.addAll(ingredient.getFactors());
		}
		return new IntArrayList(list);
	}
	
	@Override
	public IFluidIngredient getFactoredIngredient(int factor) {
		List<IFluidIngredient> list = new ArrayList<>();
		for (IFluidIngredient ingredient : ingredientList) {
			list.add(ingredient.getFactoredIngredient(factor));
		}
		return new FluidArrayIngredient(list);
	}
	
	@Override
	public IngredientMatchResult match(Object object, IngredientSorption sorption) {
		if (object instanceof FluidArrayIngredient arrayIngredient) {
			loop:
			for (IFluidIngredient ingredient : ingredientList) {
				for (IFluidIngredient ingr : arrayIngredient.ingredientList) {
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
