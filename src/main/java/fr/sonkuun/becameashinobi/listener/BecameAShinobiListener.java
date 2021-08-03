package fr.sonkuun.becameashinobi.listener;

import java.util.List;
import java.util.stream.Stream;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import fr.sonkuun.becameashinobi.gui.widget.ChakraSkillGuiWidget;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class BecameAShinobiListener {
	
	/*
	 * When a player join server, check if he had already choose
	 * his elemental nature and other abilities.
	 */
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			
			/*
			 * No elemental nature already learned
			 */
			if(data.getLearnedChakraNature().size() == 0) {
				player.sendMessage(new StringTextComponent("You can choose one chakra nature to learn."));
			}
		}
	}
	
	/*
	 * Used for debug purpose only
	 */
	@SubscribeEvent
	public void interact(PlayerInteractEvent event) {
		
		if(event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = event.getPlayer().getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			data.useChakra(10);
			data.removeHealth(1);
			data.getChakraNature().getKaton().addValue(1);
			PlayerEntity player = event.getPlayer();
			data.sendDataToServer(player);
		}
	}
	
	/*
	 * Add a button to open Jutsu Tree Gui
	 */
	@SubscribeEvent
	public void playerInventoryOpened(GuiScreenEvent.InitGuiEvent.Post event) {
		if(event.getGui() instanceof InventoryScreen) {
			event.addWidget(new ChakraSkillGuiWidget(280, 15, 28, 28));
		}
	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if(event.side.equals(LogicalSide.CLIENT) || event.phase.equals(TickEvent.Phase.END)) {
			return;
		}
		
		PlayerEntity player = event.player;
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			data.updateChakra(player);
			data.updateHealth(player);
		}
	}
	
	/*
	 * Override damage event
	 * 
	 * This event is server side
	 */
	@SubscribeEvent
	public void onEntityIsHurted(LivingDamageEvent event) {
		DamageSource source = event.getSource();
		LivingEntity victim = event.getEntityLiving();
		
		/* Player damage living entity */
		if(source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getTrueSource();
			
			if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()
					&& victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
				
				float damage = event.getAmount();
				
				ShinobiData livingEntityData = victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
				
				livingEntityData.removeHealth((double) damage);
				livingEntityData.sendDataToAllClient(victim);
				
				if(livingEntityData.getExactHealth() == 0.0) {
					victim.setHealth(0.0f);
				}
				else {
					event.setCanceled(true);
					victim.setHealth(victim.getMaxHealth());
				}
			}
		}
		/* Living entity damage player*/
		else if(source.getTrueSource() instanceof LivingEntity && victim instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) victim;
			
			if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()
					&& victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
				
				float damage = event.getAmount();
				
				ShinobiData playerData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
				
				playerData.removeHealth((double) damage);
				playerData.sendDataToClient(player);
				
				if(playerData.getExactHealth() == 0.0) {
					player.setHealth(0.0f);
				}
				else {
					event.setCanceled(true);
					victim.setHealth(victim.getMaxHealth());
				}
			}
		}
		/* Player is hurted by environment */
		else if(victim instanceof PlayerEntity) {
			
			if(!victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
				return;
			}
			
			ShinobiData data = victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			double damage = getDamageFromSource(source, victim);
			
			/* If damage isn't override, return */
			if(damage == Double.NaN) {
				return;
			}
			
			data.removeHealth(damage);
			data.sendDataToAllClient(victim);
			
			if(data.getExactHealth() == 0.0) {
				victim.setHealth(0.0f);
			}
			else {
				event.setCanceled(true);
				victim.setHealth(victim.getMaxHealth());
			}
		}
		/* Living entity is hurted by environment */
		else {
			
			if(!victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
				return;
			}
			
			ShinobiData data = victim.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			double damage = getDamageFromSource(source, victim);
			
			/* If damage isn't override, return */
			if(damage == Double.NaN) {
				return;
			}
			
			data.removeHealth(damage);
			data.sendDataToAllClient(victim);
			
			if(data.getExactHealth() == 0.0) {
				victim.setHealth(0.0f);
			}
			else {
				event.setCanceled(true);
				victim.setHealth(victim.getMaxHealth());
			}
		}
	}
	
	/*
	 * Return a custom value for every natural damage
	 * 
	 * If the damage isn't override, return NaN
	 * 
	 * TODO : override all damage
	 */
	public double getDamageFromSource(DamageSource source, LivingEntity entity) {		
		ShinobiData data = entity.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
		int maxHealth = data.getMaxHealth();
		
		if(source.equals(DamageSource.LAVA)) {
			return maxHealth * 5 / 100.0;
		}
		else if(source.equals(DamageSource.DROWN)) {
			return maxHealth * 5 / 100.0;
		}
		
		return Double.NaN;
	}
}
