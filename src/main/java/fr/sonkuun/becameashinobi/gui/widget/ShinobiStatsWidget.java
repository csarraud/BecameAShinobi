package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ShinobiStatsWidget extends AbstractSkillWidget {

	public ShinobiStatsWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public ItemStack createItemStack() {
		return new ItemStack(Items.PLAYER_HEAD);
	}

	@Override
	public List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatFormatting.BLUE + "Shinobi characteristic");
		lore.add("");
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			
			int maxHealth = data.getMaxHealth();
			double health = data.getRoundedHealth();
			double healthRegeneration = data.getTotalRegenerationHealth();
			
			double maxChakra = data.getChakraMaxValue();
			double chakra = data.getChakraValue();
			double chakraRegeneration = data.getChakraRegenerationPerSecond();
			
			lore.add(ChatFormatting.RED + "Health : " + health + " / " + maxHealth);
			lore.add(ChatFormatting.RED + "Health regeneration : +" + healthRegeneration + "/s");
			
			lore.add(ChatFormatting.BLUE + "Chakra : " + chakra + " / " + maxChakra);
			lore.add(ChatFormatting.BLUE + "Chakra regeneration : +" + chakraRegeneration + "/s");
			
			return lore;
		}
		
		lore.add(ChatFormatting.RED + "[ERROR]" + ChatFormatting.WHITE + "Shinobi capability not initialized.");
		
		return lore;
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
