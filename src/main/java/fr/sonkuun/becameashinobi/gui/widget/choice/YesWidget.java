package fr.sonkuun.becameashinobi.gui.widget.choice;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ElementalNatureData;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;
import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.ElementalNaturePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.PacketDistributor;

public class YesWidget extends Widget {

	private ElementalNature nature;
	
	public YesWidget(ElementalNature nature, int xIn, int yIn, int widthIn, int heightIn, String msg) {
		super(xIn, yIn, widthIn, heightIn, msg);
		
		this.nature = nature;
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            Minecraft mc  = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(new ResourceLocation(BecameAShinobi.MODID, "textures/gui/tabs.png"));
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.blit(this.x, this.y, 56, 0, 28, 32);

            mc.getItemRenderer().renderItemAndEffectIntoGUI(new ItemStack(Items.LIME_DYE), this.x + 6, this.y + 10);
            
            if (this.isHovered) {
                mc.currentScreen.renderTooltip(this.getMessage(), mouseX, mouseY);
            }
            
            RenderSystem.enableRescaleNormal();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int modifiers) {
        if (super.mouseClicked(mouseX, mouseY, modifiers)) {
        	ClientPlayerEntity player = Minecraft.getInstance().player;
        	
        	this.learnElementalNatureToPlayer(player);
            Minecraft.getInstance().displayGuiScreen(null);
            return true;
        }
        return false;
    }
    
    public void learnElementalNatureToPlayer(ClientPlayerEntity player) {
    	if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).isPresent()) {
    		ElementalNatureData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE).orElse(null);
    		data.addLevelToNature(this.nature, 1);
    		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new ElementalNaturePacket(player.getUniqueID(), data));
    	}
    }

}
