package fr.sonkuun.becameashinobi.gui.jutsu;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.AbstractSkillObject;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
import fr.sonkuun.becameashinobi.util.RenderUtil;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractJutsuGui extends Screen {

	private static final int WIDTH = 252, HEIGHT = 140, CORNER_SIZE = 30;
    protected static final int SIDE = 20, TOP = 20, BOTTOM = 30, PADDING = 9;
    private static final int MIN_DELTA_X = -300, MIN_DELTA_Y = -300, MAX_DELTA_X = 100, MAX_DELTA_Y = 100;
    private static final float MIN_ZOOM = 1, MAX_ZOOM = 2, ZOOM_STEP = 0.2F;
	protected int internalHeight;
	protected int internalWidth;
    protected float zoom = MIN_ZOOM;
    protected int deltaX = 0, deltaY = 0;
    
    protected List<AbstractSkillObject> skillObjects = new ArrayList<AbstractSkillObject>();

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

		GlUtil.beginScissor(new Rect(boxLeft, boxTop, insideWidth, insideHeight));
        this.drawInside(boxLeft, boxTop, boxRight, boxBottom, insideWidth, insideHeight);
        GlUtil.endScissor();
        
        RenderSystem.popMatrix();
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
	}

    private void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {

		for(AbstractSkillObject skillItem : this.skillObjects) {
			this.itemRenderer.renderItemIntoGUI(skillItem.getItemstack(), skillItem.getX() + this.deltaX, skillItem.getY() + this.deltaY);
		}
		
		for(AbstractSkillObject skillItem : this.skillObjects) {
			if(skillItem.isMouseOver()) {
				this.renderTooltip(skillItem.getDescription(), skillItem.getX() + 5 + this.deltaX, skillItem.getY() + this.deltaY);
			}
		}
    }
    
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
		
		int newDeltaX = this.deltaX + (int)deltaX;
		if(newDeltaX >= MIN_DELTA_X && newDeltaX <= MAX_DELTA_X) {
			this.deltaX = newDeltaX;
		}
		
		int newDeltaY = this.deltaY + (int)deltaY;
		if(newDeltaY >= MIN_DELTA_Y && newDeltaY <= MAX_DELTA_Y) {
			this.deltaY = newDeltaY;
		}
		
		//System.out.println(String.format("[x = %s, y = %s, button = %s, deltaX = %s, deltaY = %s]",
				//x, y, button, deltaX, deltaY));
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
	public void mouseMoved(double x, double y) {
		int left = SIDE + (width - internalWidth) / 2;
        int top = TOP + (height - internalHeight) / 2;
		int boxLeft = left + PADDING;
        int boxTop = top + 2*PADDING;
		
		for(AbstractSkillObject skillItem : skillObjects) {
			skillItem.isMouseOver(x, y, this.deltaX + boxLeft, this.deltaY + boxTop);
		}
		
		super.mouseMoved(x, y);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public abstract String getGuiTitle();
}
