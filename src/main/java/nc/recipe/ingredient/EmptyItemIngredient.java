package nc.recipe.ingredient;

import com.google.common.collect.Lists;
import crafttweaker.mc1120.item.MCItemStack;
import it.unimi.dsi.fastutil.ints.*;
import nc.recipe.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

import java.util.*;

public class EmptyItemIngredient implements IItemIngredient {
	
	public EmptyItemIngredient() {}
	
	@Override
	public ItemStack getStack() {
		return null;
	}
	
	@Override
	public List<ItemStack> getInputStackList() {
		return Collections.emptyList();
	}
	
	@Override
	public List<ItemStack> getInputStackHashingList() {
		return Lists.newArrayList(ItemStack.EMPTY);
	}
	
	@Override
	public List<ItemStack> getOutputStackList() {
		return Collections.emptyList();
	}
	
	@Override
	public int getMaxStackSize(int ingredientNumber) {
		return 0;
	}
	
	@Override
	public void setMaxStackSize(int stackSize) {
	
	}
	
	@Override
	public String getIngredientName() {
		return "null";
	}
	
	@Override
	public String getIngredientNamesConcat() {
		return "null";
	}
	
	@Override
	public IntList getFactors() {
		return new IntArrayList();
	}
	
	@Override
	public IItemIngredient getFactoredIngredient(int factor) {
		return new EmptyItemIngredient();
	}
	
	@Override
	public IngredientMatchResult match(Object object, IngredientSorption sorption) {
		if (object == null) {
			return IngredientMatchResult.PASS_0;
		}
		if (object instanceof ItemStack stack) {
			return new IngredientMatchResult(stack.isEmpty(), 0);
		}
		return new IngredientMatchResult(object instanceof EmptyItemIngredient, 0);
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}
	
	// CraftTweaker
	
	@Override
	@Optional.Method(modid = "crafttweaker")
	public crafttweaker.api.item.IIngredient ct() {
		return MCItemStack.EMPTY;
	}
}
