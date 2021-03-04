package fr.sonkuun.becameashinobi.gui.jutsu;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
import fr.sonkuun.becameashinobi.util.RenderUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractJutsuGui extends Screen {

	private static final int WIDTH = 252, HEIGHT = 140, CORNER_SIZE = 30;
    private static final int SIDE = 20, TOP = 20, BOTTOM = 30, PADDING = 9;
    private static final float MIN_ZOOM = 1, MAX_ZOOM = 2, ZOOM_STEP = 0.2F;
	private int internalHeight;
	private int internalWidth;
    private float zoom = MIN_ZOOM;

	protected AbstractJutsuGui(ClientAdvancementManager clientAdvancementManager) {
		super(NarratorChatListener.EMPTY);
	}
	
	@Override
    protected void init() {
        int uiScaling = 100;
		this.internalHeight = this.height * uiScaling  / 100;
        this.internalWidth = this.width * uiScaling / 100;
        
        super.init();
    }

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		int left = SIDE + (width - internalWidth) / 2;
        int top = TOP + (height - internalHeight) / 2;

        int right = internalWidth - SIDE + (width - internalWidth) / 2;
        int bottom = internalHeight - SIDE + (height - internalHeight) / 2;

        this.renderBackground();
        this.renderInside(mouseX, mouseY, left, top, right, bottom);
        this.renderWindow(left, top, right, bottom);
        
        super.render(mouseX, mouseY, partialTicks);
	}
	
	private void renderInside(int mouseX, int mouseY, int left, int top, int right, int bottom) {
		int boxLeft = left + PADDING;
        int boxTop = top + 2*PADDING;
        int boxRight = right - PADDING;
        int boxBottom = bottom - PADDING;

        int insideWidth = boxRight - boxLeft;
        int insideHeight = boxBottom - boxTop;
        
        RenderSystem.pushMatrix();
        RenderSystem.translated((float) (boxLeft), (float) (boxTop), 0.0F);
        RenderSystem.enableDepthTest();
        GlUtil.drawRect(new Rect(0, 0, insideWidth, insideHeight), this.getFontColor());
        this.drawInside(boxLeft, boxTop, boxRight, boxBottom, insideWidth, insideHeight);
        RenderSystem.popMatrix();
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
	}

    protected abstract void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight);
    protected abstract Color getFontColor();
    
	public void renderWindow(int left, int top, int right, int bottom) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderHelper.disableStandardItemLighting();
        this.minecraft.getTextureManager().bindTexture(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/window.png"));
        // Top left corner
        this.blit(left, top, 0, 0, CORNER_SIZE, CORNER_SIZE);
        // Top side
        RenderUtil.renderRepeating(this, left + CORNER_SIZE, top, internalWidth - CORNER_SIZE - 2*SIDE - CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, 0, WIDTH - CORNER_SIZE - CORNER_SIZE, CORNER_SIZE);
        // Top right corner
        this.blit(right - CORNER_SIZE, top, WIDTH - CORNER_SIZE, 0, CORNER_SIZE, CORNER_SIZE);
        // Left side
        RenderUtil.renderRepeating(this, left, top + CORNER_SIZE, CORNER_SIZE, bottom - top - 2 * CORNER_SIZE, 0, CORNER_SIZE, CORNER_SIZE, HEIGHT - CORNER_SIZE - CORNER_SIZE);
        // Right side
        RenderUtil.renderRepeating(this, right - CORNER_SIZE, top + CORNER_SIZE, CORNER_SIZE, bottom - top - 2 * CORNER_SIZE, WIDTH - CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, HEIGHT - CORNER_SIZE - CORNER_SIZE);
        // Bottom left corner
        this.blit(left, bottom - CORNER_SIZE, 0, HEIGHT - CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        // Bottom side
        RenderUtil.renderRepeating(this, left + CORNER_SIZE, bottom - CORNER_SIZE, internalWidth - CORNER_SIZE - 2*SIDE - CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, HEIGHT - CORNER_SIZE, WIDTH - CORNER_SIZE - CORNER_SIZE, CORNER_SIZE);
        // Bottom right corner
        this.blit(right - CORNER_SIZE, bottom - CORNER_SIZE, WIDTH - CORNER_SIZE, HEIGHT - CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);

        this.font.drawString(getGuiTitle(), left + 8, top + 6, 4210752);
    }

	@Override
	public boolean mouseClicked(double x, double y, int modifiers) {
		// TODO Auto-generated method stub
		return super.mouseClicked(x, y, modifiers);
	}

	@Override
	public boolean mouseDragged(double x, double y, int button,
			double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		return super.mouseDragged(x, y, button, deltaX, deltaY);
	}

	@Override
	public boolean mouseScrolled(double x, double y, double scroll) {
		int wheel = (int) scroll;
        if (wheel < 0 && zoom > MIN_ZOOM) {
            zoom -= ZOOM_STEP;
        } else if (wheel > 0 && zoom < MAX_ZOOM) {
            zoom += ZOOM_STEP;
        }
		return super.mouseScrolled(x, y, scroll);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public abstract String getGuiTitle();
}
