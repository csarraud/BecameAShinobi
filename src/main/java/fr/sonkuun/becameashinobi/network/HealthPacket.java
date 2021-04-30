package fr.sonkuun.becameashinobi.network;

import java.util.UUID;
import java.util.function.Supplier;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.HealthData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class HealthPacket {

	private UUID uuid;
	private HealthData healthData;
	
	public HealthPacket(UUID uuid, HealthData data) {
		this.uuid = uuid;
		this.healthData = new HealthData(data);
	}

	public HealthPacket() {
		this.uuid = null;
	}
	
	public HealthData getHealthData() {
		return this.healthData;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public void encode(HealthPacket message, PacketBuffer buffer) {
		buffer.writeUniqueId(message.getUUID());
		buffer.writeInt(message.getHealthData().getMaxHealth());
		buffer.writeDouble(message.getHealthData().getExactHealth());
		buffer.writeDouble(message.getHealthData().getBaseHealthRegenerationPerSecond());
		buffer.writeInt(message.getHealthData().getHealthRegenerationTick());
	}

	public HealthPacket decode(PacketBuffer buffer) {
		return new HealthPacket(buffer.readUniqueId(), HealthData.fromPacketBuffer(buffer));
	}

	public void handle(HealthPacket message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			/*
			 * If serverPlayer isn't null, then the packet is from CLIENT to SERVER.
			 * In our case, the client health has been updated and inform the server
			 * 
			 * Else the packet is from SERVER to CLIENT.
			 * In our case, the server inform the client it health regenerate
			 */
			ServerPlayerEntity serverPlayer = supplier.get().getSender();
			PlayerEntity clientPlayer = Minecraft.getInstance().world.getPlayerByUuid(message.getUUID());
			
			if(serverPlayer != null) {
				HealthData data = serverPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).orElse(null);
				data.synchronize(message);
			}
			else if(clientPlayer != null && clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).isPresent()) {
				HealthData data = clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).orElse(null);
				data.synchronize(message);
			}
		});

		supplier.get().setPacketHandled(true);

	}
}
