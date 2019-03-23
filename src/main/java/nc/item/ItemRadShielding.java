package nc.item;

import nc.capability.radiation.resistance.IRadiationResistance;
import nc.config.NCConfig;
import nc.enumm.MetaEnums;
import nc.util.Lang;
import nc.util.NCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemRadShielding extends NCItemMeta<MetaEnums.RadShieldingType> {
	
	public ItemRadShielding(String nameIn, String[]... tooltips) {
		super(nameIn, MetaEnums.RadShieldingType.class, tooltips);
	}
	
	private static final String NOT_HARDCORE = Lang.localise("item.nuclearcraft.rad_shielding.not_hardcore");
	private static final String INSTALL_FAIL = Lang.localise("item.nuclearcraft.rad_shielding.install_fail");
	private static final String INSTALL_SUCCESS = Lang.localise("item.nuclearcraft.rad_shielding.install_success");
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!NCConfig.radiation_tile_shielding || !player.isSneaking()) return actionResult(false, stack);
		if (NCConfig.radiation_hardcore_containers <= 0D) {
			if (!world.isRemote) {
				player.sendMessage(new TextComponentString(NOT_HARDCORE));
			}
			return actionResult(false, stack);
		}
		
		RayTraceResult raytraceresult = rayTrace(world, player, false);
		if (raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) return actionResult(false, stack);
		
		BlockPos pos = raytraceresult.getBlockPos();
		TileEntity te = world.getTileEntity(pos);
		if (!world.isBlockModifiable(player, pos) || te == null || !te.hasCapability(IRadiationResistance.CAPABILITY_RADIATION_RESISTANCE, null) || !te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) return actionResult(false, stack);
		IRadiationResistance tile = te.getCapability(IRadiationResistance.CAPABILITY_RADIATION_RESISTANCE, null);
		if (tile == null) return actionResult(false, stack);
		
		double newResistance = NCConfig.radiation_shielding_level[stack.getMetadata()];
		NCUtil.getLogger().info(tile.getRadiationResistance());
		if (newResistance <= tile.getRadiationResistance()) {
			if (!world.isRemote) {
				player.sendMessage(new TextComponentString(INSTALL_FAIL + " " + tile.getRadiationResistance()));
			}
			return actionResult(false, stack);
		}
		
		tile.setRadiationResistance(newResistance);
		stack.shrink(1);
		te.markDirty();
		if (!world.isRemote) {
			player.sendMessage(new TextComponentString(INSTALL_SUCCESS + " " + tile.getRadiationResistance()));
		}
		else {
			player.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.5F, 1F);
		}
		return actionResult(true, stack);
	}
}
