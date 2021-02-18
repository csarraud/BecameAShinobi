package fr.sonkuun.becameashinobi.listener;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class BecameAShinobiListener {
	
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
	}
}
