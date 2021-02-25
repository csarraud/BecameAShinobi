package fr.sonkuun.becameashinobi.gui.widget;

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

public abstract class AbstractSkillTreeWidget extends Widget implements IToSkillTreeWidget{

    public AbstractSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn - 28, yIn - 28, 28, 28, msg);
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            Minecraft mc  = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/tabs.png"));
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.blit(this.x, this.y, 56, 0, 28, 32);

            if (this.isHovered) {
                mc.currentScreen.renderTooltip(getScreenName(), mouseX, mouseY);
            }
            
            RenderSystem.enableRescaleNormal();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderHelper.enableStandardItemLighting();
            mc.getItemRenderer().renderItemAndEffectIntoGUI(new ItemStack(Items.BOOK), this.x + 6, this.y + 10);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int modifiers) {
        if (super.mouseClicked(mouseX, mouseY, modifiers)) {
            Minecraft.getInstance().displayGuiScreen(getGuiToDisplay());
            return true;
        }
        return false;
    }
	
	public abstract String getScreenName();
	public abstract Screen getGuiToDisplay();
}
