package nc.gui.multiblock.controller;

import nc.Global;
import nc.gui.element.MultiblockButton;
import nc.multiblock.hx.HeatExchanger;
import nc.network.multiblock.*;
import nc.tile.hx.*;
import nc.tile.TileContainerInfo;
import nc.util.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.*;

public class GuiHeatExchangerController extends GuiMultiblockController<HeatExchanger, IHeatExchangerPart, HeatExchangerUpdatePacket, TileHeatExchangerController, TileContainerInfo<TileHeatExchangerController>> {
	
	protected final ResourceLocation gui_texture;
	
	public GuiHeatExchangerController(Container inventory, EntityPlayer player, TileHeatExchangerController controller, String textureLocation) {
		super(inventory, player, controller, textureLocation);
		gui_texture = new ResourceLocation(Global.MOD_ID + ":textures/gui/container/" + "heat_exchanger_controller" + ".png");
		xSize = 176;
		ySize = 68;
	}
	
	@Override
	protected ResourceLocation getGuiTexture() {
		return gui_texture;
	}
	
	@Override
	public void renderTooltips(int mouseX, int mouseY) {
		if (NCUtil.isModifierKeyDown()) {
			drawTooltip(clearAllInfo(), mouseX, mouseY, 153, 35, 18, 18);
		}
		
		drawEfficiencyTooltip(mouseX, mouseY, 6, 57, 164, 6);
	}
	
	public List<String> efficiencyInfo() {
		List<String> info = new ArrayList<>();
		info.add(TextFormatting.LIGHT_PURPLE + Lang.localize("gui.nc.container.heat_exchanger_controller.active_percent") + " " + TextFormatting.WHITE + NCMath.pcDecimalPlaces(multiblock.fractionOfTubesActive, 1));
		info.add(TextFormatting.AQUA + Lang.localize("gui.nc.container.heat_exchanger_controller.efficiency" + (NCUtil.isModifierKeyDown() ? "_max" : "")) + " " + TextFormatting.WHITE + NCMath.pcDecimalPlaces(NCUtil.isModifierKeyDown() ? multiblock.maxEfficiency : multiblock.efficiency, 1));
		return info;
	}
	
	public void drawEfficiencyTooltip(int mouseX, int mouseY, int x, int y, int drawWidth, int drawHeight) {
		drawTooltip(efficiencyInfo(), mouseX, mouseY, x, y, drawWidth, drawHeight);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int fontColor = multiblock.isHeatExchangerOn ? 4210752 : 15619328;
		String title = multiblock.getInteriorLengthX() + "*" + multiblock.getInteriorLengthY() + "*" + multiblock.getInteriorLengthZ() + " " + Lang.localize("gui.nc.container.heat_exchanger_controller.heat_exchanger");
		fontRenderer.drawString(title, xSize / 2 - fontRenderer.getStringWidth(title) / 2, 6, fontColor);
		
		String underline = StringHelper.charLine('-', MathHelper.ceil((double) fontRenderer.getStringWidth(title) / fontRenderer.getStringWidth("-")));
		fontRenderer.drawString(underline, xSize / 2 - fontRenderer.getStringWidth(underline) / 2, 12, fontColor);
		
		String tubes = Lang.localize("gui.nc.container.heat_exchanger_controller.tubes") + " " + (multiblock.getPartCount(TileHeatExchangerTube.class) + multiblock.getPartCount(TileCondenserTube.class));
		fontRenderer.drawString(tubes, xSize / 2 - fontRenderer.getStringWidth(tubes) / 2, 22, fontColor);
		
		String efficiency = Lang.localize("gui.nc.container.heat_exchanger_controller.efficiency") + " " + NCMath.pcDecimalPlaces(multiblock.efficiency, 1);
		fontRenderer.drawString(efficiency, xSize / 2 - fontRenderer.getStringWidth(efficiency) / 2, 40, fontColor);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		int f = (int) Math.round(multiblock.fractionOfTubesActive * 164);
		drawTexturedModalRect(guiLeft + 6, guiTop + 56, 3, 68, f, 6);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		buttonList.add(new MultiblockButton.ClearAllMaterial(0, guiLeft + 153, guiTop + 35));
	}
	
	@Override
	protected void actionPerformed(GuiButton guiButton) {
		if (multiblock.WORLD.isRemote) {
			if (guiButton.id == 0 && NCUtil.isModifierKeyDown()) {
				new ClearAllMaterialPacket(tile.getTilePos()).sendToServer();
			}
		}
	}
}
