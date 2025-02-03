package nc.integration.groovyscript.ingredient;

import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.api.documentation.annotations.MethodDescription.Type;
import com.cleanroommc.groovyscript.helper.Alias;
import com.cleanroommc.groovyscript.registry.NamedRegistry;
import com.google.common.base.CaseFormat;
import nc.integration.groovyscript.GSHelper;
import nc.recipe.ingredient.ChanceItemIngredient;

@RegistryDescription
public class GSChanceItemIngredient extends NamedRegistry {
	
	public GSChanceItemIngredient() {
		super(Alias.generateOf("ChanceItemIngredient", CaseFormat.UPPER_CAMEL));
	}
	
	@MethodDescription(type = Type.QUERY)
	public ChanceItemIngredient create(Object object, int chancePercent) {
		return new ChanceItemIngredient(GSHelper.buildAdditionItemIngredient(object), chancePercent);
	}
	
	@MethodDescription(type = Type.QUERY)
	public ChanceItemIngredient create(Object object, int chancePercent, int minStackSize) {
		return new ChanceItemIngredient(GSHelper.buildAdditionItemIngredient(object), chancePercent, minStackSize);
	}
}
