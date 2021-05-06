package fr.sonkuun.becameashinobi.gui.widget.choice;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;
import fr.sonkuun.becameashinobi.gui.ChooseElementalNatureGui;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class NoWidget extends Widget {

	private ElementalNature nature;
	
	public NoWidget(ElementalNature nature, int xIn, int yIn, int widthIn, int heightIn, String msg) {
		super(xIn, yIn, widthIn, heightIn, msg);

		this.nature = nature;
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            Minecraft mc  = Minecraft.getInstance();
    		MainWindow window = mc.getMainWindow();
            
            int x = (window.getScaledWidth() / 2) + this.x;
            int y = (window.getScaledHeight() / 2) + this.y;
            
            mc.getTextureManager().bindTexture(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/tabs.png"));
            this.isHovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
            this.blit(x, y, 56, 0, 28, 32);

            mc.getItemRenderer().renderItemAndEffectIntoGUI(new ItemStack(Items.RED_DYE), x + 6, y + 10);
            
            if (this.isHovered) {
                mc.currentScreen.renderTooltip(this.getMessage(), mouseX, mouseY);
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
            Minecraft.getInstance().displayGuiScreen(new ChooseElementalNatureGui(Minecraft.getInstance().player.connection.getAdvancementManager()));
            return true;
        }
        return false;
    }

}
