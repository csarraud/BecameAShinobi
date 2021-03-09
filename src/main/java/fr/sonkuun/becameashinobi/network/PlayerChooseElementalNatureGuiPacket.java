package fr.sonkuun.becameashinobi.network;

import java.util.UUID;
import java.util.function.Supplier;

import fr.sonkuun.becameashinobi.gui.ChooseElementalNatureGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PlayerChooseElementalNatureGuiPacket {

	private UUID uuid;
	private boolean shouldChooseNature;
	
	public PlayerChooseElementalNatureGuiPacket(UUID uuid, boolean shouldChooseNature) {
		this.uuid = uuid;
		this.shouldChooseNature = shouldChooseNature;
	}
	
	public PlayerChooseElementalNatureGuiPacket() {
		this.uuid = null;
		this.shouldChooseNature = true;
	}

	public UUID getUuid() {
		return uuid;
	}

	public boolean isShouldChooseNature() {
		return shouldChooseNature;
	}

	public void encode(PlayerChooseElementalNatureGuiPacket message, PacketBuffer buffer) {
		buffer.writeUniqueId(message.getUuid());
		buffer.writeBoolean(message.isShouldChooseNature());
	}

	public PlayerChooseElementalNatureGuiPacket decode(PacketBuffer buffer) {
		return new PlayerChooseElementalNatureGuiPacket(buffer.readUniqueId(), buffer.readBoolean());
	}

	public void handle(PlayerChooseElementalNatureGuiPacket message, Supplier<Context> supplier) {
		supplier.get().enqueueWork(() -> {
			
			/*
			 * If serverPlayer isn't null, then the packet is from CLIENT to SERVER.
			 * In our case, do nothing
			 * 
			 * Else the packet is from SERVER to CLIENT.
			 * In our case, the server inform the client to open the ChooseNatureGui
			 */
			ServerPlayerEntity serverPlayer = supplier.get().getSender();
			PlayerEntity clientPlayer = Minecraft.getInstance().world.getPlayerByUuid(message.getUuid());
			System.out.println("HANDLE PACKET");
			if(serverPlayer != null) {
				/*
				 * Do nothing
				 */
			}
			else if(clientPlayer != null) {
				Minecraft.getInstance().displayGuiScreen(new ChooseElementalNatureGui(Minecraft.getInstance().player.connection.getAdvancementManager()));
			}
		});

		supplier.get().setPacketHandled(true);

	}
}
