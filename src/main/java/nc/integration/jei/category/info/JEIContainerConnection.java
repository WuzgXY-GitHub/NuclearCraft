package nc.integration.jei.category.info;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class JEIContainerConnection {
	
	public final Class<? extends Container> containerClass;
	public final Class<? extends GuiContainer> guiClass;
	
	public final int itemInputStart;
	public final int itemInputSize;
	public final int playerInventoryStart;
	
	public final int jeiClickAreaX;
	public final int jeiClickAreaY;
	public final int jeiClickAreaW;
	public final int jeiClickAreaH;
	
	public JEIContainerConnection(Class<? extends Container> containerClass, Class<? extends GuiContainer> guiClass, int itemInputStart, int itemInputSize, int playerInventoryStart, int jeiClickAreaX, int jeiClickAreaY, int jeiClickAreaW, int jeiClickAreaH) {
		this.containerClass = containerClass;
		this.guiClass = guiClass;
		
		this.itemInputStart = itemInputStart;
		this.itemInputSize = itemInputSize;
		this.playerInventoryStart = playerInventoryStart;
		
		this.jeiClickAreaX = jeiClickAreaX;
		this.jeiClickAreaY = jeiClickAreaY;
		this.jeiClickAreaW = jeiClickAreaW;
		this.jeiClickAreaH = jeiClickAreaH;
	}
}
