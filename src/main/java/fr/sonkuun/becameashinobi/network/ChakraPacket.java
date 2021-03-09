package fr.sonkuun.becameashinobi.network;

import java.util.UUID;
import java.util.function.Supplier;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ChakraPacket {

	private UUID uuid;
	private ChakraData chakraData;
	
	public ChakraPacket(UUID uuid, ChakraData data) {
		this.uuid = uuid;
		this.chakraData = new ChakraData(data);
	}

	public ChakraPacket() {
		this.uuid = null;
	}
	
	public ChakraData getChakraData() {
		return this.chakraData;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public void encode(ChakraPacket message, PacketBuffer buffer) {
		buffer.writeUniqueId(message.getUUID());
		buffer.writeDouble(message.getChakraData().getChakraMaxValue());
		buffer.writeDouble(message.getChakraData().getChakraValue());
		buffer.writeDouble(message.getChakraData().getBaseChakraRegenerationPerSecond());
		buffer.writeDouble(message.getChakraData().getChakraRegenerationPerSecond());
		buffer.writeInt(message.getChakraData().getChakraRegenerationFactor());
		buffer.writeInt(message.getChakraData().getChakraRegenerationTick());
	}

	public ChakraPacket decode(PacketBuffer buffer) {
		return new ChakraPacket(buffer.readUniqueId(), ChakraData.fromPacketBuffer(buffer));
	}

	public void handle(ChakraPacket message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			/*
			 * If serverPlayer isn't null, then the packet is from CLIENT to SERVER.
			 * In our case, the client used chakra and inform the server
			 * 
			 * Else the packet is from SERVER to CLIENT.
			 * In our case, the server inform the client it chakra regenerate
			 */
			ServerPlayerEntity serverPlayer = supplier.get().getSender();
			PlayerEntity clientPlayer = Minecraft.getInstance().world.getPlayerByUuid(message.getUUID());
			
			if(serverPlayer != null) {
				ChakraData data = serverPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).orElse(null);
				data.synchronize(message);
			}
			else if(clientPlayer != null && clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).isPresent()) {
				ChakraData data = clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).orElse(null);
				data.synchronize(message);
			}
		});

		supplier.get().setPacketHandled(true);

	}



}
