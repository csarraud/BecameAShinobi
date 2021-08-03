package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.ChakraSkillGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ChakraSkillGuiWidget extends AbstractSkillWidget {

	public ChakraSkillGuiWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
    }

	@Override
	public ItemStack createItemStack() {
		return new ItemStack(Items.BOOK);
	}

	@Override
	public List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatFormatting.WHITE + "Chakra Skill");
		
		return lore;
	}

	@Override
	protected Screen createGui() {
		return new ChakraSkillGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

	@Override
	protected boolean canOpenGui() {
		return true;
	}
}
