package fr.sonkuun.becameashinobi.gui.widget;

import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;

public abstract class AbstractSkillTreeWidget extends Widget implements IToSkillTreeWidget {

    public AbstractSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn - 28, yIn - 28, 28, 28, msg);
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            Minecraft mc  = Minecraft.getInstance();
    		MainWindow window = mc.getMainWindow();
            
            int x = (window.getScaledWidth() / 2) + this.x;
            int y = (window.getScaledHeight() / 2) + this.y;
            
            bind(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/tabs.png"));
            this.isHovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
            this.blit(x, y, 56, 0, 28, 32);
            
            bind(getTexture());
            this.blit(x + 6, y + 8, getOffsetX(), getOffsetY(), 16, 16);
            
            if (this.isHovered) {
                mc.currentScreen.renderTooltip(getScreenName(), mouseX, mouseY);
            }
            
            RenderSystem.enableRescaleNormal();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int modifiers) {

        Minecraft mc  = Minecraft.getInstance();
		MainWindow window = mc.getMainWindow();
        
        int x = (window.getScaledWidth() / 2) + this.x;
        int y = (window.getScaledHeight() / 2) + this.y;
        
    	boolean clicked = mouseX >= x && mouseX <= (x + this.width) && mouseY >= y && mouseY <=(y + this.height);
    			
        if (clicked) {
            Minecraft.getInstance().displayGuiScreen(getGuiToDisplay());
            return true;
        }
        return false;
    }
	
	public abstract List<String> getScreenName();
	public abstract Screen getGuiToDisplay();
	
	public void bind(ResourceLocation resource) {
		Minecraft.getInstance().getTextureManager().bindTexture(resource);
	}
}
