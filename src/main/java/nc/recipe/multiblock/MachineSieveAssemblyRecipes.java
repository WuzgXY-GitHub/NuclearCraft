package nc.recipe.multiblock;

import nc.enumm.MetaEnums.MachineSieveAssemblyType;
import nc.init.NCBlocks;
import nc.recipe.BasicRecipeHandler;
import net.minecraft.item.ItemStack;

import java.util.List;

import static nc.config.NCConfig.machine_sieve_assembly_efficiency;

public class MachineSieveAssemblyRecipes extends BasicRecipeHandler {
	
	public MachineSieveAssemblyRecipes() {
		super("machine_sieve_assembly", 1, 0, 0, 0);
	}
	
	@Override
	public void addRecipes() {
		for (int i = 0; i < MachineSieveAssemblyType.values().length; ++i) {
			addRecipe(new ItemStack(NCBlocks.machine_sieve_assembly, 1, i), machine_sieve_assembly_efficiency[i]);
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 0D);
		return fixer.fixed;
	}
}
