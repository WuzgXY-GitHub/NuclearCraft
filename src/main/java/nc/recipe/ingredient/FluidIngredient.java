package nc.recipe.ingredient;

import com.google.common.collect.Lists;
import crafttweaker.api.minecraft.CraftTweakerMC;
import it.unimi.dsi.fastutil.ints.*;
import nc.recipe.*;
import nc.tile.internal.fluid.Tank;
import nc.util.FluidStackHelper;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.Optional;

import java.util.List;

public class FluidIngredient implements IFluidIngredient {
	
	public FluidStack stack;
	public String fluidName;
	
	public FluidIngredient(FluidStack stack) {
		this.stack = stack;
		fluidName = FluidStackHelper.getFluidName(stack);
	}
	
	public FluidIngredient(String fluidName, int amount) {
		stack = FluidRegistry.getFluidStack(fluidName, amount);
		this.fluidName = fluidName;
	}
	
	@Override
	public FluidStack getStack() {
		return stack == null ? null : stack.copy();
	}
	
	@Override
	public List<FluidStack> getInputStackList() {
		return Lists.newArrayList(stack);
	}
	
	@Override
	public List<FluidStack> getOutputStackList() {
		return Lists.newArrayList(stack);
	}
	
	@Override
	public int getMaxStackSize(int ingredientNumber) {
		return stack == null ? 0 : stack.amount;
	}
	
	@Override
	public void setMaxStackSize(int stackSize) {
		stack.amount = stackSize;
	}
	
	@Override
	public String getIngredientName() {
		return fluidName;
	}
	
	@Override
	public String getIngredientNamesConcat() {
		return fluidName;
	}
	
	@Override
	public IntList getFactors() {
		return new IntArrayList(Lists.newArrayList(stack.amount));
	}
	
	@Override
	public IFluidIngredient getFactoredIngredient(int factor) {
		FluidStack newStack = stack.copy();
		newStack.amount = stack.amount / factor;
		return new FluidIngredient(newStack);
	}
	
	@Override
	public IngredientMatchResult match(Object object, IngredientSorption type) {
		if (object instanceof Tank tank) {
			object = tank.getFluid();
		}
		if (object instanceof FluidStack fluidstack) {
			if (!fluidstack.isFluidEqual(stack) || !FluidStack.areFluidStackTagsEqual(fluidstack, stack)) {
				return IngredientMatchResult.FAIL;
			}
			return new IngredientMatchResult(type.checkStackSize(stack.amount, fluidstack.amount), 0);
		}
		else if (object instanceof FluidIngredient ingredient && match(ingredient.stack, type).matches()) {
			return new IngredientMatchResult(type.checkStackSize(getMaxStackSize(0), ingredient.getMaxStackSize(0)), 0);
		}
		return IngredientMatchResult.FAIL;
	}
	
	@Override
	public boolean isValid() {
		return stack != null;
	}
	
	@Override
	public boolean isEmpty() {
		return stack == null || stack.amount <= 0;
	}
	
	// CraftTweaker
	
	@Override
	@Optional.Method(modid = "crafttweaker")
	public crafttweaker.api.item.IIngredient ct() {
		return CraftTweakerMC.getILiquidStack(stack);
	}
}
