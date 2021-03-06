package fr.sonkuun.becameashinobi.hud;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import fr.sonkuun.becameashinobi.geom.Direction;
import fr.sonkuun.becameashinobi.geom.Point;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
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

	public static final ResourceLocation CHAKRA_CIRCULATION_TEXTURE = new ResourceLocation(BecameAShinobi.MODID, "textures/hud/chakra/chakra_circulation.png");
	public static final ResourceLocation CHAKRA_TEXTURE = new ResourceLocation(BecameAShinobi.MODID, "textures/hud/chakra/chakra.png");
	
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
		
		int width = window.getWidth();
		int height = window.getHeight();
		
		int textureWidth = 256;
		int textureHeight = 256;
		
		int posX = (int) ((width * (4.0 / window.getGuiScaleFactor()) - textureWidth));
		int posY = (int) (((height - textureHeight) / 2) * (4.0 / window.getGuiScaleFactor()));

		double chakraPercent = chakraData.getChakraValue() / chakraData.getChakraMaxValue();
		int chakraProgress = (int) (textureHeight - textureHeight * chakraPercent);
		
		GlUtil.scale(0.5f);
		GlUtil.drawString(Double.toString(chakraData.getChakraValue()), new Point(posX + (int) (textureWidth * 0.73), posY - 10).scale(0.5f), Direction.CENTER, new Color(129, 189, 249));
		GlUtil.scale(2f);
		
		GlUtil.scale(0.25f);
		
		/*
		 * Allow transparency for the custom texture
		 */
		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		
		bind(CHAKRA_TEXTURE);
		gui.blit(posX, posY + chakraProgress, 0, chakraProgress, textureWidth, textureHeight - chakraProgress);
		
		bind(CHAKRA_CIRCULATION_TEXTURE);
		gui.blit(posX, posY, 0, 0, textureWidth, textureHeight);
		GlUtil.scale(4f);
		
		bind(AbstractGui.GUI_ICONS_LOCATION);
	}

	public void bind(ResourceLocation texture) {
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
	}
}
