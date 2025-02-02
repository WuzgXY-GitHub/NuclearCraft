package nc.multiblock.hx;

import it.unimi.dsi.fastutil.objects.*;
import nc.Global;
import nc.multiblock.*;
import nc.multiblock.cuboidal.CuboidalMultiblock;
import nc.network.multiblock.HeatExchangerUpdatePacket;
import nc.tile.hx.*;
import nc.tile.multiblock.TilePartAbstract.SyncReason;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.UnaryOperator;

public class HeatExchanger extends CuboidalMultiblock<HeatExchanger, IHeatExchangerPart> implements ILogicMultiblock<HeatExchanger, HeatExchangerLogic, IHeatExchangerPart>, IPacketMultiblock<HeatExchanger, IHeatExchangerPart, HeatExchangerUpdatePacket> {
	
	public static final ObjectSet<Class<? extends IHeatExchangerPart>> PART_CLASSES = new ObjectOpenHashSet<>();
	public static final Object2ObjectMap<String, UnaryOperator<HeatExchangerLogic>> LOGIC_MAP = new Object2ObjectOpenHashMap<>();
	
	protected @Nonnull HeatExchangerLogic logic = new HeatExchangerLogic(this);
	
	protected final PartSuperMap<HeatExchanger, IHeatExchangerPart> partSuperMap = new PartSuperMap<>();
	
	protected IHeatExchangerController<?> controller;
	
	public boolean isHeatExchangerOn, computerActivated;
	public double fractionOfTubesActive, efficiency, maxEfficiency;
	
	protected final Set<EntityPlayer> updatePacketListeners = new ObjectOpenHashSet<>();
	
	public HeatExchanger(World world) {
		super(world, HeatExchanger.class, IHeatExchangerPart.class);
		for (Class<? extends IHeatExchangerPart> clazz : PART_CLASSES) {
			partSuperMap.equip(clazz);
		}
	}
	
	@Override
	public @Nonnull HeatExchangerLogic getLogic() {
		return logic;
	}
	
	@Override
	public void setLogic(String logicID) {
		if (logicID.equals(logic.getID())) {
			return;
		}
		logic = getNewLogic(LOGIC_MAP.get(logicID));
	}
	
	@Override
	public PartSuperMap<HeatExchanger, IHeatExchangerPart> getPartSuperMap() {
		return partSuperMap;
	}
	
	// Multiblock Size Limits
	
	@Override
	protected int getMinimumInteriorLength() {
		return logic.getMinimumInteriorLength();
	}
	
	@Override
	protected int getMaximumInteriorLength() {
		return logic.getMaximumInteriorLength();
	}
	
	// Multiblock Methods
	
	@Override
	public void onAttachedPartWithMultiblockData(IHeatExchangerPart part, NBTTagCompound data) {
		logic.onAttachedPartWithMultiblockData(part, data);
		syncDataFrom(data, SyncReason.FullSync);
	}
	
	@Override
	protected void onBlockAdded(IHeatExchangerPart newPart) {
		onPartAdded(newPart);
		logic.onBlockAdded(newPart);
	}
	
	@Override
	protected void onBlockRemoved(IHeatExchangerPart oldPart) {
		onPartRemoved(oldPart);
		logic.onBlockRemoved(oldPart);
	}
	
	@Override
	protected void onMachineAssembled() {
		logic.onMachineAssembled();
	}
	
	@Override
	protected void onMachineRestored() {
		logic.onMachineRestored();
	}
	
	@Override
	protected void onMachinePaused() {
		logic.onMachinePaused();
	}
	
	@Override
	protected void onMachineDisassembled() {
		logic.onMachineDisassembled();
	}
	
	@Override
	protected boolean isMachineWhole() {
		return setLogic(this) && super.isMachineWhole() && logic.isMachineWhole();
	}
	
	public boolean setLogic(HeatExchanger multiblock) {
		if (getPartMap(IHeatExchangerController.class).isEmpty()) {
			multiblock.setLastError(Global.MOD_ID + ".multiblock_validation.no_controller", null);
			return false;
		}
		if (getPartCount(IHeatExchangerController.class) > 1) {
			multiblock.setLastError(Global.MOD_ID + ".multiblock_validation.too_many_controllers", null);
			return false;
		}
		
		for (IHeatExchangerController<?> contr : getParts(IHeatExchangerController.class)) {
			controller = contr;
			break;
		}
		
		setLogic(controller.getLogicID());
		
		return true;
	}
	
	@Override
	protected void onAssimilate(HeatExchanger assimilated) {
		logic.onAssimilate(assimilated);
	}
	
	@Override
	protected void onAssimilated(HeatExchanger assimilator) {
		logic.onAssimilated(assimilator);
	}
	
	// Server
	
	@Override
	protected boolean updateServer() {
		boolean flag = false;
		if (logic.onUpdateServer()) {
			flag = true;
		}
		return flag;
	}
	
	// Client
	
	@Override
	protected void updateClient() {
		logic.onUpdateClient();
	}
	
	// NBT
	
	@Override
	public void syncDataTo(NBTTagCompound data, SyncReason syncReason) {
		data.setBoolean("isHeatExchangerOn", isHeatExchangerOn);
		data.setBoolean("computerActivated", computerActivated);
		data.setDouble("fractionOfTubesActive", fractionOfTubesActive);
		data.setDouble("efficiency", efficiency);
		data.setDouble("maxEfficiency", maxEfficiency);
		
		writeLogicNBT(data, syncReason);
	}
	
	@Override
	public void syncDataFrom(NBTTagCompound data, SyncReason syncReason) {
		isHeatExchangerOn = data.getBoolean("isHeatExchangerOn");
		computerActivated = data.getBoolean("computerActivated");
		fractionOfTubesActive = data.getDouble("fractionOfTubesActive");
		efficiency = data.getDouble("efficiency");
		maxEfficiency = data.getDouble("maxEfficiency");
		
		readLogicNBT(data, syncReason);
	}
	
	// Packets
	
	@Override
	public Set<EntityPlayer> getMultiblockUpdatePacketListeners() {
		return updatePacketListeners;
	}
	
	@Override
	public HeatExchangerUpdatePacket getMultiblockUpdatePacket() {
		return logic.getMultiblockUpdatePacket();
	}
	
	@Override
	public void onMultiblockUpdatePacket(HeatExchangerUpdatePacket message) {
		logic.onMultiblockUpdatePacket(message);
	}
	
	// Multiblock Validators
	
	@Override
	protected boolean isBlockGoodForInterior(World world, BlockPos pos) {
		return logic.isBlockGoodForInterior(world, pos);
	}
	
	// Clear Material
	
	@Override
	public void clearAllMaterial() {
		logic.clearAllMaterial();
		super.clearAllMaterial();
	}
}
