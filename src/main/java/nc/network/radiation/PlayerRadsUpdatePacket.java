package nc.network.radiation;

import io.netty.buffer.ByteBuf;
import nc.capability.radiation.entity.IEntityRads;
import nc.network.NCPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;

public class PlayerRadsUpdatePacket extends NCPacket {
	
	protected double totalRads;
	protected double radiationLevel;
	protected double internalRadiationResistance, externalRadiationResistance;
	protected boolean radXUsed;
	protected boolean radXWoreOff;
	protected double radawayBuffer, radawayBufferSlow;
	protected double poisonBuffer;
	protected boolean consumed;
	protected double radawayCooldown;
	protected double recentRadawayAddition;
	protected double radXCooldown;
	protected double recentRadXAddition;
	protected int messageCooldownTime;
	protected double recentPoisonAddition;
	protected double radiationImmunityTime;
	protected boolean radiationImmunityStage;
	protected boolean shouldWarn;
	protected boolean giveGuidebook;
	
	public PlayerRadsUpdatePacket() {
		super();
	}
	
	public PlayerRadsUpdatePacket(IEntityRads playerRads) {
		super();
		totalRads = playerRads.getTotalRads();
		radiationLevel = playerRads.getRadiationLevel();
		internalRadiationResistance = playerRads.getInternalRadiationResistance();
		externalRadiationResistance = playerRads.getExternalRadiationResistance();
		radXUsed = playerRads.getRadXUsed();
		radXWoreOff = playerRads.getRadXWoreOff();
		radawayBuffer = playerRads.getRadawayBuffer(false);
		radawayBufferSlow = playerRads.getRadawayBuffer(true);
		poisonBuffer = playerRads.getPoisonBuffer();
		consumed = playerRads.getConsumedMedicine();
		radawayCooldown = playerRads.getRadawayCooldown();
		recentRadawayAddition = playerRads.getRecentRadawayAddition();
		radXCooldown = playerRads.getRadXCooldown();
		recentRadXAddition = playerRads.getRecentRadXAddition();
		messageCooldownTime = playerRads.getMessageCooldownTime();
		recentPoisonAddition = playerRads.getRecentPoisonAddition();
		radiationImmunityTime = playerRads.getRadiationImmunityTime();
		radiationImmunityStage = playerRads.getRadiationImmunityStage();
		shouldWarn = playerRads.getShouldWarn();
		giveGuidebook = playerRads.getGiveGuidebook();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		totalRads = buf.readDouble();
		radiationLevel = buf.readDouble();
		internalRadiationResistance = buf.readDouble();
		externalRadiationResistance = buf.readDouble();
		radXUsed = buf.readBoolean();
		radXWoreOff = buf.readBoolean();
		radawayBuffer = buf.readDouble();
		radawayBufferSlow = buf.readDouble();
		poisonBuffer = buf.readDouble();
		consumed = buf.readBoolean();
		radawayCooldown = buf.readDouble();
		recentRadawayAddition = buf.readDouble();
		radXCooldown = buf.readDouble();
		recentRadXAddition = buf.readDouble();
		messageCooldownTime = buf.readInt();
		recentPoisonAddition = buf.readDouble();
		radiationImmunityTime = buf.readDouble();
		radiationImmunityStage = buf.readBoolean();
		shouldWarn = buf.readBoolean();
		giveGuidebook = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeDouble(totalRads);
		buf.writeDouble(radiationLevel);
		buf.writeDouble(internalRadiationResistance);
		buf.writeDouble(externalRadiationResistance);
		buf.writeBoolean(radXUsed);
		buf.writeBoolean(radXWoreOff);
		buf.writeDouble(radawayBuffer);
		buf.writeDouble(radawayBufferSlow);
		buf.writeDouble(poisonBuffer);
		buf.writeBoolean(consumed);
		buf.writeDouble(radawayCooldown);
		buf.writeDouble(recentRadawayAddition);
		buf.writeDouble(radXCooldown);
		buf.writeDouble(recentRadXAddition);
		buf.writeInt(messageCooldownTime);
		buf.writeDouble(recentPoisonAddition);
		buf.writeDouble(radiationImmunityTime);
		buf.writeBoolean(radiationImmunityStage);
		buf.writeBoolean(shouldWarn);
		buf.writeBoolean(giveGuidebook);
	}
	
	public static class Handler implements IMessageHandler<PlayerRadsUpdatePacket, IMessage> {
		
		@Override
		public IMessage onMessage(PlayerRadsUpdatePacket message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft.getMinecraft().addScheduledTask(() -> {
					EntityPlayerSP player = Minecraft.getMinecraft().player;
					if (player == null || !player.hasCapability(IEntityRads.CAPABILITY_ENTITY_RADS, null)) {
						return;
					}
					
					IEntityRads playerRads = player.getCapability(IEntityRads.CAPABILITY_ENTITY_RADS, null);
					if (playerRads == null) {
						return;
					}
					
					playerRads.setTotalRads(message.totalRads, false);
					playerRads.setRadiationLevel(message.radiationLevel);
					playerRads.setInternalRadiationResistance(message.internalRadiationResistance);
					playerRads.setExternalRadiationResistance(message.externalRadiationResistance);
					playerRads.setRadXUsed(message.radXUsed);
					playerRads.setRadXWoreOff(message.radXWoreOff);
					playerRads.setRadawayBuffer(false, message.radawayBuffer);
					playerRads.setRadawayBuffer(true, message.radawayBufferSlow);
					playerRads.setPoisonBuffer(message.poisonBuffer);
					playerRads.setConsumedMedicine(message.consumed);
					playerRads.setRadawayCooldown(message.radawayCooldown);
					playerRads.setRecentRadawayAddition(message.recentRadawayAddition);
					playerRads.setRadXCooldown(message.radXCooldown);
					playerRads.setRecentRadXAddition(message.recentRadXAddition);
					playerRads.setMessageCooldownTime(message.messageCooldownTime);
					playerRads.setRecentPoisonAddition(message.recentPoisonAddition);
					playerRads.setRadiationImmunityTime(message.radiationImmunityTime);
					playerRads.setRadiationImmunityStage(message.radiationImmunityStage);
					playerRads.setShouldWarn(message.shouldWarn);
					playerRads.setGiveGuidebook(message.giveGuidebook);
				});
			}
			return null;
		}
	}
}
