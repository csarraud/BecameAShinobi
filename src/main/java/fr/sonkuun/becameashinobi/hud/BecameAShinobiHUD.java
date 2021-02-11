package fr.sonkuun.becameashinobi.hud;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class BecameAShinobiHUD {

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Post event) {
		if(event.getType() != ElementType.ALL) {
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;

		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).isPresent()) {
			return;
		}
		
		renderChakraBar(player);

		RenderSystem.pushMatrix();
		RenderSystem.popMatrix();
	}
	
	public void renderChakraBar(ClientPlayerEntity player) {
		ChakraData chakraData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI).orElse(null);
		
		AbstractGui gui = Minecraft.getInstance().ingameGUI;
		MainWindow window = Minecraft.getInstance().getMainWindow();
		
		/*
		 * TO DO : draw chakra bar
		 */
		
		bind(AbstractGui.GUI_ICONS_LOCATION);
	}

	public void bind(ResourceLocation texture) {
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
	}
}
