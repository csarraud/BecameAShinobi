package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.gui.ChakraNatureGui;
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
		
		lore.add(ChatFormatting.GRAY + "" + ChatFormatting.UNDERLINE + "Shinobi characteristic");
		lore.add("");
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			
			/*
			 * Health
			 */
			int maxHealth = data.getMaxHealth();
			double health = data.getRoundedHealth();
			double healthRegeneration = data.getTotalRegenerationHealth();

			lore.add(ChatFormatting.RED + "Health");
			lore.add(ChatFormatting.GOLD  + "" + health + ChatFormatting.RED + " / " + ChatFormatting.GOLD + maxHealth);
			lore.add(ChatFormatting.RED + "+" + ChatFormatting.GOLD + healthRegeneration + ChatFormatting.RED + "/s");
			lore.add("");
			
			/*
			 * Chakra
			 */
			double maxChakra = data.getChakraMaxValue();
			double chakra = data.getChakraValue();
			double chakraRegeneration = data.getChakraRegenerationPerSecond();
			int sneakFactor = data.getChakraRegenerationFactor();

			lore.add(ChatFormatting.BLUE + "Chakra");
			lore.add(ChatFormatting.GOLD + "" + chakra + ChatFormatting.BLUE + " / " + ChatFormatting.GOLD + maxChakra);
			lore.add(ChatFormatting.BLUE + "+" + ChatFormatting.GOLD + chakraRegeneration + ChatFormatting.BLUE + "/s     (+" + ChatFormatting.GOLD + sneakFactor + ChatFormatting.BLUE + "%)");
			lore.add("");
			
			/*
			 * Chakra nature
			 */
			for(AbstractNature nature : data.getLearnedChakraNature()) {

				lore.add(nature.getChatFormattingColor() + nature.getNature().getName() + " " + ChatFormatting.GOLD + nature.getValue());
			}
			
			return lore;
		}
		
		lore.add(ChatFormatting.RED + "[ERROR]" + ChatFormatting.WHITE + "Shinobi capability not initialized.");
		
		return lore;
	}

	@Override
	protected Screen createGui() {
		return new ChakraNatureGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

	@Override
	protected boolean canOpenGui() {
		return true;
	}

}
