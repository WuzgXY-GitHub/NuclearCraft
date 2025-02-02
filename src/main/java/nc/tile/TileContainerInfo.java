package nc.tile;

import nc.container.ContainerFunction;
import nc.gui.*;
import nc.handler.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TileContainerInfo<TILE extends TileEntity> {
	
	public final String modId;
	public final String name;
	
	public final Class<TILE> tileClass;
	
	protected final ContainerFunction<TILE> containerFunction;
	protected final GuiFunction<TILE> guiFunction;
	
	public final int guiId;
	
	public TileContainerInfo(String modId, String name, Class<TILE> tileClass, ContainerFunction<TILE> containerFunction, GuiFunction<TILE> guiFunction) {
		this.modId = modId;
		this.name = name;
		
		this.tileClass = tileClass;
		
		this.containerFunction = containerFunction;
		this.guiFunction = guiFunction;
		
		guiId = GuiHandler.getGuiId(name);
	}
	
	public TileContainerInfo(String modId, String name, Class<TILE> tileClass, ContainerFunction<TILE> containerFunction, GuiInfoTileFunction<TILE> guiFunction) {
		this(modId, name, tileClass, containerFunction, GuiFunction.of(modId, name, containerFunction, guiFunction));
	}
	
	public Object getNewContainer(int id, EntityPlayer player, TILE tile) {
		return containerFunction.apply(player, tile);
	}
	
	public Object getNewGui(int id, EntityPlayer player, TILE tile) {
		return guiFunction.apply(player, tile);
	}
	
	public int getGuiId() {
		return guiId;
	}
}
