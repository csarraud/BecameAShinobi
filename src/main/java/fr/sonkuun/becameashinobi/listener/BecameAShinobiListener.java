package fr.sonkuun.becameashinobi.listener;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import fr.sonkuun.becameashinobi.capability.ElementalNatureData;
import fr.sonkuun.becameashinobi.capability.HealthData;
import fr.sonkuun.becameashinobi.gui.widget.JutsuTreeGuiWidget;
import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.ChakraPacket;
import fr.sonkuun.becameashinobi.network.PlayerChooseElementalNatureGuiPacket;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.PacketDistributor;

public class BecameAShinobiListener {
	
	/*
	 * When a player join server, check if he had already choose
	 * his elemental nature and other abilities.
	 */
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).isPresent()) {
			ElementalNatureData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).orElse(null);
			
			if(data.countLearnedElementalNature() == 0) {
				BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player),
						new PlayerChooseElementalNatureGuiPacket(player.getUniqueID(), true));
			}
		}
	}
	
	/*
	 * Used for debug purpose only
	 */
	@SubscribeEvent
	public void interact(PlayerInteractEvent event) {
		if(event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).isPresent()) {
			ChakraData data = event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).orElse(null);
			data.useChakra(10);
			PlayerEntity player = event.getPlayer();
			BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new ChakraPacket(player.getUniqueID(), data));
		}
		
		if(event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).isPresent()) {
			HealthData data = event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).orElse(null);
			data.removeHealth(1);
		}
	}
	/*
	 * Add a button to open Jutsu Tree Gui
	 */
	@SubscribeEvent
	public void playerInventoryOpened(GuiScreenEvent.InitGuiEvent.Post event) {
		if(event.getGui() instanceof InventoryScreen) {
			event.addWidget(new JutsuTreeGuiWidget(50, 50, "Jutsu Tree"));
		}
	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if(event.side.equals(LogicalSide.CLIENT) || event.phase.equals(TickEvent.Phase.END)) {
			return;
		}
		
		PlayerEntity player = event.player;
		
		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).isPresent()) {
			return;
		}
		
		ChakraData chakraData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).orElse(null);
		
		chakraData.updateChakra(player);
	}
}
