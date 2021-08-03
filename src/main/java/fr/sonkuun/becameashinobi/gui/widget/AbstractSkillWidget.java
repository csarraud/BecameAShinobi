package fr.sonkuun.becameashinobi.gui.widget;

import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractSkillWidget extends Widget {

	protected int x, y, width, height;
	protected boolean isMouseOver;
	
	public AbstractSkillWidget(int x, int y, int width, int height) {
		super(x, y, width, height, "");
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.isMouseOver = false;
	}

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            Minecraft mc  = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/tabs.png"));
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.blit(this.x, this.y, 28, 0, 28, 32);

            if (this.isHovered) {
                mc.currentScreen.renderTooltip(this.createDescription(), mouseX, mouseY);
            }
            
            RenderSystem.enableRescaleNormal();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderHelper.enableStandardItemLighting();
            mc.getItemRenderer().renderItemAndEffectIntoGUI(new ItemStack(Items.BOOK), this.x + 6, this.y + 10);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int modifiers) {
        if (super.mouseClicked(mouseX, mouseY, modifiers) && this.canOpenGui()) {
            Minecraft.getInstance().displayGuiScreen(this.createGui());
            return true;
        }
        return false;
    }
	
	public boolean isMouseOver(double x, double y, int deltaX, int deltaY) {
		isMouseOver = (x >= this.x + deltaX && x <= this.x + deltaX + this.width) && (y >= this.y + deltaY && y <= this.y + deltaY + this.height);
		return isMouseOver;
	}
	
	public boolean isMouseOver() {
		return isMouseOver;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public abstract ItemStack createItemStack();
	public abstract List<String> createDescription();
	protected abstract Screen createGui();
	protected abstract boolean canOpenGui();

}
