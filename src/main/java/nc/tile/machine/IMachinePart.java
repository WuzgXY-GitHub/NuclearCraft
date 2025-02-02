package nc.tile.machine;

import nc.multiblock.cuboidal.ITileCuboidalLogicMultiblockPart;
import nc.multiblock.machine.*;

public interface IMachinePart extends ITileCuboidalLogicMultiblockPart<Machine, MachineLogic, IMachinePart> {
	
	default void refreshMachineRecipe() {
		MachineLogic logic = getLogic();
		if (logic != null) {
			logic.refreshRecipe();
		}
	}
	
	default void refreshMachineActivity() {
		MachineLogic logic = getLogic();
		if (logic != null) {
			logic.refreshActivity();
		}
	}
}
