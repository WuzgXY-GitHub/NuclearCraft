package nc.render.tile;

import nc.multiblock.machine.*;
import nc.render.IWorldRender;
import nc.tile.internal.fluid.Tank;
import nc.tile.machine.TileInfiltratorController;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.GlStateManager.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderMultiblockInfiltrator extends TileEntitySpecialRenderer<TileInfiltratorController> implements IWorldRender {
	
	@Override
	public boolean isGlobalRenderer(TileInfiltratorController controller) {
		return controller.isRenderer() && controller.isMultiblockAssembled();
	}
	
	@Override
	public void render(TileInfiltratorController controller, double posX, double posY, double posZ, float partialTicks, int destroyStage, float alpha) {
		if (!controller.isRenderer() || !controller.isMultiblockAssembled()) {
			return;
		}
		
		Machine machine = controller.getMultiblock();
		if (machine == null) {
			return;
		}
		
		MachineLogic logic = machine.getLogic();
		if (!(logic instanceof InfiltratorLogic)) {
			return;
		}
		
		GlStateManager.pushMatrix();
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0F, 240F);
		
		GlStateManager.enableCull();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		
		BlockPos posOffset = machine.getExtremeInteriorCoord(false, false, false).subtract(controller.getPos());
		GlStateManager.translate(posX + posOffset.getX(), posY + posOffset.getY(), posZ + posOffset.getZ());
		
		int xSize = machine.getInteriorLengthX(), ySize = machine.getInteriorLengthY(), zSize = machine.getInteriorLengthZ();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(-PIXEL, -PIXEL, -PIXEL);
		for (Tank tank : logic.reservoirTanks) {
			IWorldRender.renderFluid(tank, xSize + 2D * PIXEL, ySize + 2D * PIXEL, zSize + 2D * PIXEL, EnumFacing.UP);
		}
		GlStateManager.popMatrix();
		
		/*@SuppressWarnings("SimplifyStreamApiCallChains")
		List<Tank> filledTanks = logic.tanks.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
		int filledTankCount = filledTanks.size();
		
		if (filledTankCount > 0) {
			double processFraction = MathHelper.clamp(logic.time / logic.baseProcessTime, 0D, 1D);
			double capacityDivisor = processFraction < 0.5D ? 2D * processFraction : Math.sqrt(Math.max(0D, 2D - 2D * processFraction));
			double[] yTranslation = new double[] {-PIXEL, 0.5D * ySize};
			
			for (int i = 0; i < filledTankCount; ++i) {
				GlStateManager.pushMatrix();
				GlStateManager.translate(-PIXEL, yTranslation[i], -PIXEL);
				FluidStack stack = filledTanks.get(i).getFluid();
				IWorldRender.renderFluid(stack, stack.amount / capacityDivisor, xSize + 2D * PIXEL, filledTankCount == 1 ? ySize + 2D * PIXEL : 0.5D * (ySize + 2D * PIXEL), zSize + 2D * PIXEL, i == 0 ? EnumFacing.UP : EnumFacing.DOWN, x -> true);
				GlStateManager.popMatrix();
			}
		}*/
		
		GlStateManager.disableBlend();
		GlStateManager.disableCull();
		
		GlStateManager.popMatrix();
	}
}
