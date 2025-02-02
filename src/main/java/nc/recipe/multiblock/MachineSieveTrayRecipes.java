package nc.recipe.multiblock;

import nc.enumm.MetaEnums;
import nc.init.NCBlocks;
import nc.recipe.BasicRecipeHandler;
import net.minecraft.item.ItemStack;

import java.util.List;

import static nc.config.NCConfig.*;

public class MachineSieveTrayRecipes extends BasicRecipeHandler {
	
	public MachineSieveTrayRecipes() {
		super("machine_sieve_tray", 1, 0, 0, 0);
	}
	
	@Override
	public void addRecipes() {
		for (int i = 0; i < MetaEnums.MachineSieveTrayType.values().length; ++i) {
			addRecipe(new ItemStack(NCBlocks.machine_sieve_tray, 1, i), machine_sieve_tray_efficiency[i]);
		}
	}
	
	@Override
	protected List<Object> fixedExtras(List<Object> extras) {
		ExtrasFixer fixer = new ExtrasFixer(extras);
		fixer.add(Double.class, 0D);
		return fixer.fixed;
	}
}
