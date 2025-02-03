package nc.integration.groovyscript;

import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.api.documentation.annotations.MethodDescription.Type;
import com.cleanroommc.groovyscript.helper.Alias;
import com.cleanroommc.groovyscript.registry.NamedRegistry;
import com.google.common.base.CaseFormat;

@RegistryDescription
public class GSStaticRecipeHandler extends NamedRegistry {
	
	public GSStaticRecipeHandler() {
		super(Alias.generateOf("BasicRecipeHandler", CaseFormat.UPPER_CAMEL));
	}
	
	@MethodDescription(type = Type.QUERY)
	public GSBasicRecipeRegistry get(String name) {
		return GSContainer.instance.getRecipeRegistry(name);
	}
}
