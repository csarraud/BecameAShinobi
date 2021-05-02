package fr.sonkuun.becameashinobi.hud;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ChakraData;
import fr.sonkuun.becameashinobi.capability.HealthData;
import fr.sonkuun.becameashinobi.geom.Direction;
import fr.sonkuun.becameashinobi.geom.Point;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
import fr.sonkuun.becameashinobi.util.RayTraceUtil;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class BecameAShinobiHUD {

	public static final ResourceLocation CHAKRA_CIRCULATION_TEXTURE = new ResourceLocation(BecameAShinobi.MODID, "textures/hud/chakra/chakra_circulation.png");
	public static final ResourceLocation CHAKRA_TEXTURE = new ResourceLocation(BecameAShinobi.MODID, "textures/hud/chakra/chakra.png");
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Pre event) {
		if(event.getType() != ElementType.HEALTH) {
			return;
		}
		
		event.setCanceled(true);

		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;

		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).isPresent()) {
			return;
		}
		
		renderHealthBar(player);

		RenderSystem.pushMatrix();
		RenderSystem.popMatrix();
	}
	
	public void renderHealthBar(ClientPlayerEntity player) {
		HealthData healthData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).orElse(null);
		
		AbstractGui gui = Minecraft.getInstance().ingameGUI;
		MainWindow window = Minecraft.getInstance().getMainWindow();
		
		int left = window.getScaledWidth() / 2 - 91;
		int top = window.getScaledHeight() - ForgeIngameGui.left_height;
		
		double healthPercentage = 100 * healthData.getHealth() / healthData.getMaxHealth();

		drawHealthBar(left, top, 90, 9, healthPercentage);
	}
	
	public void drawHealthBar(int x, int y, int width, int height, double percentage) {
		/*
		 * Allow transparency for the custom texture
		 */
		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		
		GlUtil.drawRect(new Rect(x, y, width, height), new Color(100, 0, 0, 0));
		GlUtil.drawRect(new Rect(x, y, (int) (width * percentage / 100), height), new Color(255, 0, 0));
		GlUtil.drawBorderRect(new Rect(x, y, width, height), new Color(0, 0, 0));
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Post event) {
		if(event.getType() != ElementType.ALL) {
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;

		if(!player.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).isPresent()) {
			return;
		}
		
		renderChakraBar(player);

		RenderSystem.pushMatrix();
		RenderSystem.popMatrix();
		
		renderEntityDataPlayerIsLookingAt(player);
	}
	
	public void renderChakraBar(ClientPlayerEntity player) {
		ChakraData chakraData = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_CHAKRA).orElse(null);
		
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
	
	public void renderEntityDataPlayerIsLookingAt(ClientPlayerEntity player) {
		Entity entity = RayTraceUtil.getMouseOver(player, 10, 0.0f);
		if(entity != null && entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			
			if(livingEntity.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).isPresent()) {
				HealthData data = livingEntity.getCapability(CapabilityBecameAShinobi.CAPABILITY_HEALTH).orElse(null);
				
				double healthPercentage = 100 * data.getHealth() / data.getMaxHealth();
				
				GlUtil.drawTooltipBox(new Rect(1, 1, 70, 18));
				
				GlUtil.scale(0.5f);
				GlUtil.drawString(livingEntity.getName().getString(), new Point(12, 14), Direction.WEST, new Color(255, 255, 255));
				GlUtil.scale(2f);
				
				drawHealthBar(5, 9, 60, 6, healthPercentage);
			}
		}
		
	}

	public void bind(ResourceLocation texture) {
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
	}
}
