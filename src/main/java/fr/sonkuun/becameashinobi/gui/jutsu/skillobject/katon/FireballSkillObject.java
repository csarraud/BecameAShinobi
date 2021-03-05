package fr.sonkuun.becameashinobi.gui.jutsu.skillobject.katon;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.AbstractSkillObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FireballSkillObject extends AbstractSkillObject {
	
	public FireballSkillObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	protected ItemStack createItemStack() {
		return new ItemStack(Items.FIRE_CHARGE);
	}

	@Override
	protected List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		ClientPlayerEntity player = Minecraft.getInstance().player;
		
		int jutsuLevel = 1;
		
		lore.add(ChatFormatting.RED + "Fireball Jutsu");
		lore.add("");
		lore.add(ChatFormatting.DARK_PURPLE + "Jutsu level : " + ChatFormatting.GOLD + jutsuLevel + ChatFormatting.DARK_PURPLE + " / 50");
		lore.add("");
		lore.add("Use this jutsu to throw a");
		lore.add("fireball in the direction");
		lore.add("you're looking at.");
		lore.add("");
		
		return lore;
	}
}
