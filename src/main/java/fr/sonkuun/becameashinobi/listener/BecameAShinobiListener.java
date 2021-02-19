package fr.sonkuun.becameashinobi.listener;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.ChakraPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.PacketDistributor;

public class BecameAShinobiListener {
	
	/*
	 * Used for debug purpose only
	 */
	@SubscribeEvent
	public void interact(PlayerInteractEvent event) {
		if(event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).isPresent()) {
			ChakraData data = event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).orElse(null);
			data.useChakra(10);
			PlayerEntity player = event.getPlayer();
			BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new ChakraPacket(player.getUniqueID(), data));
		}
	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if(event.side.equals(LogicalSide.CLIENT)) {
			return;
		}
		
		PlayerEntity player = event.player;
		
		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).isPresent()) {
			return;
		}
		
		ChakraData chakraData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).orElse(null);
		
		chakraData.updateChakra();
		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ChakraPacket(player.getUniqueID(), chakraData));
	}
}
