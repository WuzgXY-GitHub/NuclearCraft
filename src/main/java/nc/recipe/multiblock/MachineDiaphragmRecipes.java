package nc.recipe.multiblock;

import nc.enumm.MetaEnums;
import nc.init.NCBlocks;
import nc.recipe.BasicRecipeHandler;
import net.minecraft.item.ItemStack;

import java.util.List;

import static nc.config.NCConfig.*;

public class MachineDiaphragmRecipes extends BasicRecipeHandler {
	
	public MachineDiaphragmRecipes() {
		super("machine_diaphragm", 1, 0, 0, 0);
	}
	
	@Override
	public void addRecipes() {
		for (int i = 0; i < MetaEnums.MachineDiaphragmType.values().length; ++i) {
			addRecipe(new ItemStack(NCBlocks.machine_diaphragm, 1, i), machine_diaphragm_efficiency[i], machine_diaphragm_contact_factor[i]);
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 0D);
		fixer.add(Double.class, 1D);
		return fixer.fixed;
	}
}
