package nc.gui.processor;

import nc.container.processor.ContainerIrradiator;
import nc.gui.GuiFluidRenderer;
import nc.gui.GuiItemRenderer;
import nc.init.NCItems;
import nc.tile.processor.TileEnergyFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;

public class GuiIrradiator extends GuiEnergyFluidProcessor {

	public GuiIrradiator(EntityPlayer player, TileEnergyFluidProcessor tile) {
		super("irradiator", player, new ContainerIrradiator(player, tile));
		this.tile = tile;
		xSize = 176;
		ySize = 166;
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRendererObj.drawString(tile.storage.getEnergyStored() + " RF", 28, ySize - 94, 4210752);
		
		GuiItemRenderer itemRenderer = new GuiItemRenderer(132, ySize - 102, 0.5F, NCItems.upgrade, 0);
		itemRenderer.draw();
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		double e = Math.round(((double) tile.storage.getEnergyStored()) / ((double) tile.storage.getMaxEnergyStored()) * 74);
		drawTexturedModalRect(guiLeft + 8, guiTop + 6 + 74 - (int) e, 176, 90 + 74 - (int) e, 16, (int) e);
		
		int k = getCookProgressScaled(37);
		drawTexturedModalRect(guiLeft + 70, guiTop + 34, 176, 3, k, 18);
		
		GuiFluidRenderer.renderGuiTank(tile.tanks[0], guiLeft + 32, guiTop + 35, zLevel, 16, 16);
		GuiFluidRenderer.renderGuiTank(tile.tanks[1], guiLeft + 52, guiTop + 35, zLevel, 16, 16);
		GuiFluidRenderer.renderGuiTank(tile.tanks[2], guiLeft + 108, guiTop + 31, zLevel, 24, 24);
		GuiFluidRenderer.renderGuiTank(tile.tanks[3], guiLeft + 136, guiTop + 31, zLevel, 24, 24);
	}
}
