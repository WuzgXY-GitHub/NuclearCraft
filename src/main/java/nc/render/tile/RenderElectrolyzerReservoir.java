package nc.render.tile;

import nc.multiblock.machine.*;
import nc.render.IWorldRender;
import nc.tile.internal.fluid.Tank;
import nc.tile.machine.TileElectrolyzerController;
import nc.util.ColorHelper;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.GlStateManager.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderElectrolyzerReservoir extends TileEntitySpecialRenderer<TileElectrolyzerController> implements IWorldRender {
	
	@Override
	public boolean isGlobalRenderer(TileElectrolyzerController controller) {
		return controller.isRenderer() && controller.isMultiblockAssembled();
	}
	
	@Override
	public void render(TileElectrolyzerController controller, double posX, double posY, double posZ, float partialTicks, int destroyStage, float alpha) {
		if (!controller.isRenderer() || !controller.isMultiblockAssembled()) {
			return;
		}
		
		Machine machine = controller.getMultiblock();
		if (machine == null) {
			return;
		}
		
		MachineLogic logic = machine.getLogic();
		if (!(logic instanceof ElectrolyzerLogic)) {
			return;
		}
		
		GlStateManager.pushMatrix();
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0F, 240F);
		
		GlStateManager.enableCull();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		
		BlockPos offset = machine.getMinimumCoord().subtract(controller.getPos());
		GlStateManager.translate(posX + offset.getX() + IWorldRender.PIXEL, posY + offset.getY() + IWorldRender.PIXEL, posZ + offset.getZ() + IWorldRender.PIXEL);
		
		for (Tank tank : logic.reservoirTanks) {
			FluidStack stack = tank.getFluid();
			if (stack != null) {
				Fluid fluid = stack.getFluid();
				if (fluid != null) {
					int luminosity = 16 * fluid.getLuminosity();
					float[] lastBrightness = null;
					
					if (!FMLClientHandler.instance().hasOptifine() && luminosity > 0) {
						lastBrightness = new float[] {OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY};
						OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Math.min(luminosity + lastBrightness[0], 240F), Math.min(luminosity + lastBrightness[1], 240F));
					}
					
					boolean gaseous = fluid.isGaseous(stack);
					int color = fluid.getColor(stack);
					double fraction = (double) stack.amount / (double) tank.getCapacity();
					
					GlStateManager.color(ColorHelper.getRed(color) / 255F, ColorHelper.getGreen(color) / 255F, ColorHelper.getBlue(color) / 255F, (gaseous ? (float) fraction : 1F) * ColorHelper.getAlpha(color) / 255F);
					
					if (fluid.getStill(stack) != null) {
						BlockModel model = new BlockModel();
						model.setTexture(IWorldRender.getStillTexture(fluid));
						model.setSize(machine.getExteriorLengthX() - 2D * IWorldRender.PIXEL, (gaseous ? 1D : fraction) * machine.getExteriorLengthY() - 2D * IWorldRender.PIXEL, machine.getExteriorLengthZ() - 2D * IWorldRender.PIXEL);
						IWorldRender.RenderResizableCuboid.renderCube(model);
					}
					
					if (lastBrightness != null) {
						OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightness[0], lastBrightness[1]);
					}
					
					GlStateManager.color(1F, 1F, 1F, 1F);
				}
			}
		}
		
		GlStateManager.disableBlend();
		GlStateManager.disableCull();
		
		GlStateManager.popMatrix();
	}
}
