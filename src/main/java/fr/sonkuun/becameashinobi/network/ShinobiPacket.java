package fr.sonkuun.becameashinobi.network;

import java.util.UUID;
import java.util.function.Supplier;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ShinobiPacket {

	private UUID uuid;
	private ShinobiData shinobiData;
	
	public ShinobiPacket(UUID uuid, ShinobiData data) {
		this.uuid = uuid;
		this.shinobiData = new ShinobiData(data);
	}
	
	public ShinobiPacket() {
		this.uuid = null;
	}
	
	public ShinobiData getShinobiData() {
		return this.shinobiData;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}

	public void encode(ShinobiPacket message, PacketBuffer buffer) {
		buffer.writeUniqueId(message.getUUID());
		
		/*
		 * Health
		 */
		buffer.writeInt(message.getShinobiData().getMaxHealth());
		buffer.writeDouble(message.getShinobiData().getExactHealth());
		buffer.writeDouble(message.getShinobiData().getBaseHealthRegenerationPerSecond());
		buffer.writeInt(message.getShinobiData().getHealthRegenerationTick());
		
		/*
		 * Chakra
		 */
		buffer.writeDouble(message.getShinobiData().getChakraMaxValue());
		buffer.writeDouble(message.getShinobiData().getChakraValue());
		buffer.writeDouble(message.getShinobiData().getBaseChakraRegenerationPerSecond());
		buffer.writeDouble(message.getShinobiData().getChakraRegenerationPerSecond());
		buffer.writeInt(message.getShinobiData().getChakraRegenerationFactor());
		buffer.writeInt(message.getShinobiData().getChakraRegenerationTick());
	}

	public ShinobiPacket decode(PacketBuffer buffer) {
		return new ShinobiPacket(buffer.readUniqueId(), ShinobiData.fromPacketBuffer(buffer));
	}

	public void handle(ShinobiPacket message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			/*
			 * If serverPlayer isn't null, then the packet is from CLIENT to SERVER.
			 * In our case, the client health / chakra has been updated and inform the server
			 * 
			 * Else the packet is from SERVER to CLIENT.
			 * In our case, the server inform the client it health / chakra value has been modified
			 */
			ServerPlayerEntity serverPlayer = supplier.get().getSender();
			PlayerEntity clientPlayer = Minecraft.getInstance().world.getPlayerByUuid(message.getUUID());
			LivingEntity livingEntity = null;
			
			if(serverPlayer != null) {
				ShinobiData data = serverPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
				data.synchronize(message);
			}
			else if(clientPlayer != null && clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
				ShinobiData data = clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
				data.synchronize(message);
			}
			else if((livingEntity = entityFromUUID(message.getUUID())) != null) {
				
				if(livingEntity.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
					ShinobiData data = livingEntity.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
					data.synchronize(message);
				}
			}
		});

		supplier.get().setPacketHandled(true);
	}
	
	public LivingEntity entityFromUUID(UUID uuid) {
		for(Entity entity : Minecraft.getInstance().world.getAllEntities()) {
			if(entity instanceof LivingEntity && entity.getUniqueID().equals(uuid)) {
				return (LivingEntity) entity;
			}
		}
		
		return null;
	}
}
