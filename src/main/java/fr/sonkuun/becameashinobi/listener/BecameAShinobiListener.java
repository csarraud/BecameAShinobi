package fr.sonkuun.becameashinobi.listener;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import fr.sonkuun.becameashinobi.gui.jutsu.AbstractJutsuGui;
import fr.sonkuun.becameashinobi.gui.widget.JutsuTreeGuiWidget;
import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.ChakraPacket;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
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
		
		
	}
	
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
		
		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).isPresent()) {
			return;
		}
		
		ChakraData chakraData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).orElse(null);
		
		chakraData.updateChakra(player);
	}
}
