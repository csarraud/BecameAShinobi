package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EyeJutsuWidget extends AbstractSkillWidget {

	public EyeJutsuWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	protected ItemStack createItemStack() {
		return new ItemStack(Items.ENDER_EYE);
	}

	@Override
	protected List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		
		lore.add(ChatFormatting.RED + "Eye jutsu");
		lore.add("");
		lore.add(ChatFormatting.DARK_PURPLE + "Jutsu : " + ChatFormatting.GOLD + "None");
		lore.add("");
		
		return lore;
	}

}
