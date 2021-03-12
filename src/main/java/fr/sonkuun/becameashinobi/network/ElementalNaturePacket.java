package fr.sonkuun.becameashinobi.network;

import java.util.UUID;
import java.util.function.Supplier;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ElementalNatureData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ElementalNaturePacket {

	private UUID uuid;
	private ElementalNatureData elementalNatureData;
	
	public ElementalNaturePacket(UUID uuid, ElementalNatureData data) {
		this.uuid = uuid;
		this.elementalNatureData = new ElementalNatureData(data);
	}
	
	public ElementalNaturePacket() {
		this.uuid = null;
	}

	public UUID getUuid() {
		return uuid;
	}

	public ElementalNatureData getElementalNatureData() {
		return elementalNatureData;
	}

	public void encode(ElementalNaturePacket message, PacketBuffer buffer) {
		buffer.writeUniqueId(message.getUuid());
		buffer.writeInt(message.getElementalNatureData().getKatonLevel());
		buffer.writeInt(message.getElementalNatureData().getSuitonLevel());
		buffer.writeInt(message.getElementalNatureData().getFutonLevel());
		buffer.writeInt(message.getElementalNatureData().getRaitonLevel());
		buffer.writeInt(message.getElementalNatureData().getDotonLevel());
	}

	public ElementalNaturePacket decode(PacketBuffer buffer) {
		return new ElementalNaturePacket(buffer.readUniqueId(), ElementalNatureData.fromPacketBuffer(buffer));
	}

	public void handle(ElementalNaturePacket message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			/*
			 * If serverPlayer isn't null, then the packet is from CLIENT to SERVER.
			 * 
			 * Else the packet is from SERVER to CLIENT.
			 */
			ServerPlayerEntity serverPlayer = supplier.get().getSender();
			PlayerEntity clientPlayer = Minecraft.getInstance().world.getPlayerByUuid(message.getUuid());
			
			if(serverPlayer != null) {
				ElementalNatureData data = serverPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).orElse(null);
				data.synchronize(message);
			}
			else if(clientPlayer != null && clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).isPresent()) {
				ElementalNatureData data = clientPlayer.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).orElse(null);
				data.synchronize(message);
			}
		});

		supplier.get().setPacketHandled(true);

	}
}
