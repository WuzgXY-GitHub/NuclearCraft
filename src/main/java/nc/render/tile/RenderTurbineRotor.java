package nc.render.tile;

import nc.multiblock.turbine.*;
import nc.multiblock.turbine.Turbine.PlaneDir;
import nc.multiblock.turbine.TurbineRotorBladeUtil.TurbinePartDir;
import nc.render.IWorldRender;
import nc.tile.turbine.TileTurbineController;
import nc.util.NCMath;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.*;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.*;

import javax.vecmath.Vector3f;

import static nc.config.NCConfig.*;

@SideOnly(Side.CLIENT)
public class RenderTurbineRotor extends TileEntitySpecialRenderer<TileTurbineController> implements IWorldRender {
	
	private final float[] brightnessArray = new float[] {1F, 1F, 1F, 1F, 1F, 1F, 1F, 1F};
	private int brightnessIndex = 0;
	
	private long prevRenderTime = 0L;
	
	@Override
	public boolean isGlobalRenderer(TileTurbineController controller) {
		return controller.isRenderer() && controller.isMultiblockAssembled();
	}
	
	@Override
	public void render(TileTurbineController controller, double posX, double posY, double posZ, float partialTicks, int destroyStage, float alpha) {
		if (!controller.isRenderer() || !controller.isMultiblockAssembled()) {
			return;
		}
		
		Turbine turbine = controller.getMultiblock();
		if (turbine == null || turbine.nbtUpdateRenderDataFlag) {
			return;
		}
		
		int flowLength = turbine.getFlowLength(), bladeLength = turbine.bladeLength, shaftWidth = turbine.shaftWidth;
		if (flowLength < 1 || turbine.renderPosArray == null || turbine.bladeAngleArray == null || turbine.rotorStateArray == null || turbine.rotorStateArray.length < 1 + 4 * flowLength) {
			return;
		}
		
		IBlockState shaftState = turbine.rotorStateArray[4 * flowLength];
		EnumFacing dir = turbine.flowDir;
		if (shaftState == null || dir == null) {
			return;
		}
		
		BlockRendererDispatcher renderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		brightnessArray[brightnessIndex] = controller.getWorld().getLightBrightness(turbine.getExtremeInteriorCoord(NCMath.getBit(brightnessIndex, 0) == 1, NCMath.getBit(brightnessIndex, 1) == 1, NCMath.getBit(brightnessIndex, 2) == 1));
		brightnessIndex = (brightnessIndex + 1) % 8;
		float brightness = (brightnessArray[0] + brightnessArray[1] + brightnessArray[2] + brightnessArray[3] + brightnessArray[4] + brightnessArray[5] + brightnessArray[6] + brightnessArray[7]) / 8F;
		
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0F, 240F);
		
		BlockPos pos = controller.getPos();
		double r = turbine.getRotorRadius(), scale = r / Math.sqrt(r * r + NCMath.sq(shaftWidth) / 4D);
		double rX = -turbine.getMaxX() + pos.getX() + (dir.getAxis() == Axis.X ? 0D : r);
		double rY = -turbine.getMaxY() + pos.getY() + (dir.getAxis() == Axis.Y ? 0D : r);
		double rZ = -turbine.getMaxZ() + pos.getZ() + (dir.getAxis() == Axis.Z ? 0D : r);
		
		// Enter rotated frame
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(posX - rX, posY - rY, posZ - rZ);
		GlStateManager.scale(dir.getAxis() == Axis.X ? 1D : scale, dir.getAxis() == Axis.Y ? 1D : scale, dir.getAxis() == Axis.Z ? 1D : scale);
		{
			long systemTime = Minecraft.getSystemTime();
			if (!Minecraft.getMinecraft().isGamePaused()) {
				turbine.rotorAngle = (turbine.rotorAngle + (systemTime - prevRenderTime) * turbine.angVel) % 360F;
			}
			prevRenderTime = systemTime;
			GlStateManager.rotate(turbine.rotorAngle, dir.getAxis() == Axis.X ? 1F : 0F, dir.getAxis() == Axis.Y ? 1F : 0F, dir.getAxis() == Axis.Z ? 1F : 0F);
		}
		GlStateManager.translate(-pos.getX() + rX, -pos.getY() + rY, -pos.getZ() + rZ);
		
		for (int depth : turbine.bladeDepths) {
			renderRotor(turbine, renderer, brightness, shaftState, dir, flowLength, bladeLength, shaftWidth, turbine_render_blade_width, depth);
		}
		
		// Leave rotated frame
		GlStateManager.popMatrix();
		
		// Enter stationary frame
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(posX - rX, posY - rY, posZ - rZ);
		GlStateManager.scale(dir.getAxis() == Axis.X ? 1D : scale, dir.getAxis() == Axis.Y ? 1D : scale, dir.getAxis() == Axis.Z ? 1D : scale);
		GlStateManager.translate(-pos.getX() + rX, -pos.getY() + rY, -pos.getZ() + rZ);
		
		for (int depth : turbine.statorDepths) {
			renderRotor(turbine, renderer, brightness, shaftState, dir, flowLength, bladeLength, shaftWidth, turbine_render_blade_width, depth);
		}
		
		// Leave stationary frame
		GlStateManager.popMatrix();
	}
	
	public void renderRotor(Turbine turbine, BlockRendererDispatcher renderer, float brightness, IBlockState shaftState, EnumFacing flowDir, int flowLength, int bladeLength, int shaftWidth, double bladeWidth, int depth) {
		double depthScale = Math.pow(turbine_render_rotor_expansion, (double) (1 + depth - flowLength) / (double) flowLength);
		
		GlStateManager.pushMatrix();
		
		if (turbine.renderPosArray.length < 1 + 4 * flowLength * shaftWidth + depth) {
			return;
		}
		
		Vector3f renderPos = turbine.renderPosArray[4 * flowLength * shaftWidth + depth];
		GlStateManager.translate(renderPos.x + 0.5D, renderPos.y + 0.5D, renderPos.z + 0.5D);
		GlStateManager.scale(flowDir.getAxis() == Axis.X ? 1D : depthScale, flowDir.getAxis() == Axis.Y ? 1D : depthScale, flowDir.getAxis() == Axis.Z ? 1D : depthScale);
		GlStateManager.translate(-renderPos.x - 0.5D, -renderPos.y - 0.5D, -renderPos.z - 0.5D);
		
		renderShaft(turbine, renderer, brightness, shaftState, flowDir, flowLength, shaftWidth, depth);
		for (int j : new int[] {0, flowLength, 2 * flowLength, 3 * flowLength}) {
			renderBlades(turbine, renderer, brightness, flowDir, flowLength, bladeLength, shaftWidth, bladeWidth, j, depth);
		}
		
		GlStateManager.popMatrix();
	}
	
	public void renderShaft(Turbine turbine, BlockRendererDispatcher renderer, float brightness, IBlockState shaftState, EnumFacing flowDir, int flowLength, int shaftWidth, int depth) {
		GlStateManager.pushMatrix();
		
		if (turbine.renderPosArray.length < 1 + 4 * flowLength * shaftWidth + depth) {
			return;
		}
		
		Vector3f renderPos = turbine.renderPosArray[4 * flowLength * shaftWidth + depth];
		GlStateManager.translate(renderPos.x + 0.5D, renderPos.y + 0.5D, renderPos.z + 0.5D);
		GlStateManager.scale(flowDir.getAxis() == Axis.X ? 1D : shaftWidth, flowDir.getAxis() == Axis.Y ? 1D : shaftWidth, flowDir.getAxis() == Axis.Z ? 1D : shaftWidth);
		
		GlStateManager.translate(-0.5D, -0.5D, -0.5D);
		GlStateManager.rotate(-90F, 0F, 1F, 0F);
		
		renderer.renderBlockBrightness(shaftState, brightness);
		
		GlStateManager.popMatrix();
	}
	
	public void renderBlades(Turbine turbine, BlockRendererDispatcher renderer, float brightness, EnumFacing flowDir, int flowLength, int bladeLength, int shaftWidth, double bladeWidth, int jMult, int depth) {
		Vector3f renderPos;
		IBlockState rotorState;
		TurbinePartDir bladeDir;
		PlaneDir planeDir;
		
		int i = jMult + depth;
		
		if (turbine.rotorStateArray.length < i + 1) {
			return;
		}
		
		for (int w = 0; w < shaftWidth; ++w) {
			GlStateManager.pushMatrix();
			
			renderPos = turbine.renderPosArray[w + i * shaftWidth];
			rotorState = turbine.rotorStateArray[i];
			bladeDir = rotorState.getValue(TurbineRotorBladeUtil.DIR);
			planeDir = (i < flowLength || i >= 3 * flowLength) ? PlaneDir.V : PlaneDir.U;
			
			GlStateManager.translate(renderPos.x + 0.5D, renderPos.y + 0.5D, renderPos.z + 0.5D);
			GlStateManager.scale(flowDir.getAxis() == Axis.X ? 1D : (turbine.getBladeDir(planeDir) == TurbinePartDir.X ? bladeLength : bladeWidth), flowDir.getAxis() == Axis.Y ? 1D : (turbine.getBladeDir(planeDir) == TurbinePartDir.Y ? bladeLength : bladeWidth), flowDir.getAxis() == Axis.Z ? 1D : (turbine.getBladeDir(planeDir) == TurbinePartDir.Z ? bladeLength : bladeWidth));
			GlStateManager.rotate(turbine.bladeAngleArray[i] * (flowDir.getAxisDirection() == AxisDirection.POSITIVE ^ flowDir.getAxis() == Axis.X ? 1F : -1F), bladeDir == TurbinePartDir.X ? 1F : 0F, bladeDir == TurbinePartDir.Y ? 1F : 0F, bladeDir == TurbinePartDir.Z ? 1F : 0F);
			
			GlStateManager.translate(-0.5D, -0.5D, -0.5D);
			GlStateManager.rotate(-90F, 0F, 1F, 0F);
			
			renderer.renderBlockBrightness(rotorState, brightness);
			
			GlStateManager.popMatrix();
		}
	}
}
