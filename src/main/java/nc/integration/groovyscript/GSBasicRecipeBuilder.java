package nc.integration.groovyscript;

import com.cleanroommc.groovyscript.api.*;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import nc.recipe.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class GSBasicRecipeBuilder<BUILDER extends GSBasicRecipeBuilder<BUILDER>> extends AbstractRecipeBuilder<BasicRecipe> {
	
	public final GSBasicRecipeRegistry registry;
	
	public final ObjectArrayList<Object> extras = new ObjectArrayList<>();
	
	public GSBasicRecipeBuilder(GSBasicRecipeRegistry registry) {
		this.registry = registry;
	}
	
	@SuppressWarnings("unchecked")
	protected BUILDER getThis() {
		return (BUILDER) this;
	}
	
	@GroovyBlacklist
	protected BasicRecipeHandler getRecipeHandler() {
		return registry.getRecipeHandler();
	}
	
	public BUILDER setExtra(int index, Object extra) {
		extras.ensureCapacity(index + 1);
		if (extras.size() <= index) {
			extras.size(index + 1);
		}
		extras.set(index, extra);
		return getThis();
	}
	
	@Override
	public String getErrorMsg() {
		return "Error building NuclearCraft " + getRecipeHandler().getName() + " recipe";
	}
	
	@Override
	public void validate(GroovyLog.Msg msg) {
		BasicRecipeHandler recipeHandler = getRecipeHandler();
		validateItems(msg, 0, recipeHandler.itemInputSize, 0, recipeHandler.itemOutputSize);
		validateFluids(msg, 0, recipeHandler.fluidInputSize, 0, recipeHandler.fluidOutputSize);
	}
	
	@Override
	public @Nullable BasicRecipe register() {
		if (!validate()) {
			return null;
		}
		else {
			return registry.addRecipeInternal(Stream.of(input, fluidInput, output, fluidOutput, extras).flatMap(List::stream).toArray());
		}
	}
}
