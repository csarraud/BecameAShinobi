package fr.sonkuun.becameashinobi.gui.jutsu.skillobject.futon;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.AbstractSkillObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WindSkillObject extends AbstractSkillObject {

	public WindSkillObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	protected ItemStack createItemStack() {
		return new ItemStack(Items.FEATHER);
	}

	@Override
	protected List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		
		int jutsuLevel = 1;
		
		lore.add(ChatFormatting.GREEN + "Wind Jutsu");
		lore.add("");
		lore.add(ChatFormatting.DARK_PURPLE + "Jutsu level : " + ChatFormatting.GOLD + jutsuLevel + ChatFormatting.DARK_PURPLE + " / 50");
		lore.add("");
		lore.add("Use this jutsu to repulse");
		lore.add("all entities in front of you.");
		lore.add("");
		
		return lore;
	}

}
