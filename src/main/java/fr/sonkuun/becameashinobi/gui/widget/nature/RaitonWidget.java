package fr.sonkuun.becameashinobi.gui.widget.nature;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import fr.sonkuun.becameashinobi.gui.widget.AbstractSkillWidget;
import fr.sonkuun.becameashinobi.gui.widget.IUpgradeNatureWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class RaitonWidget extends AbstractSkillWidget implements IUpgradeNatureWidget {

	private int temporaryValue;
	
	public RaitonWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		temporaryValue = 0;
	}

	@Override
	public ItemStack createItemStack() {
		return new ItemStack(Items.YELLOW_DYE);
	}

	@Override
	public List<String> createDescription() {
		List<String> lore = new ArrayList<String>();

		String value = "0";
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			
			if(temporaryValue > 0) {
				value = ChatFormatting.GOLD + "" + (data.getChakraNature().getRaiton().getValue() + temporaryValue) + " (+" + temporaryValue + ")";
			} 
			else {
				value = ChatFormatting.GOLD + "" + data.getChakraNature().getRaiton().getValue();
			}
		}

		lore.add(ChatFormatting.YELLOW + "Raiton " + value);
		lore.add("");

		return lore;
	}

	@Override
	public int mouseClickedWithNaturePoint(int naturePoint, double mouseX, double mouseY, int modifiers) {
		
		// Left click
		if(modifiers == 0 && naturePoint > 0) {
			temporaryValue += 1;
			return naturePoint - 1;
		} 
		// Right click
		else if(modifiers == 1 && temporaryValue > 0) {
			temporaryValue -= 1;
			return naturePoint + 1;
		}
		
		return naturePoint;
	}

	@Override
	protected Screen createGui() {
		return null;
	}

	@Override
	protected boolean canOpenGui() {
		return false;
	}

}
