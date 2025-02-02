package nc.tile.dummy;

import nc.tile.energy.ITileEnergy;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.energy.EnergyConnection;
import nc.tile.internal.fluid.TankSorption;
import nc.tile.internal.inventory.ItemSorption;
import nc.tile.inventory.ITileInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import static nc.config.NCConfig.machine_update_rate;

public class TileMachineInterface extends TileDummy<IInterfaceable> {
	
	public TileMachineInterface() {
		super(IInterfaceable.class, "machine_interface", ITileInventory.inventoryConnectionAll(ItemSorption.BOTH), ITileEnergy.energyConnectionAll(EnergyConnection.BOTH), machine_update_rate, null, ITileFluid.fluidConnectionAll(TankSorption.BOTH));
	}
	
	@Override
	public void update() {
		super.update();
		if (!world.isRemote) {
			pushEnergy();
		}
	}
	
	// Find Master
	
	@Override
	public void findMaster() {
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity tile = world.getTileEntity(pos.offset(side));
			if (tile != null) {
				if (isMaster(pos.offset(side))) {
					masterPosition = pos.offset(side);
					return;
				}
			}
		}
		masterPosition = null;
	}
}
