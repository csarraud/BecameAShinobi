package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.ChakraNatureGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ValidationNaturePointWidget extends AbstractSkillWidget {

	private ChakraNatureGui gui;
	
	public ValidationNaturePointWidget(ChakraNatureGui gui, int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.gui = gui;
	}

	@Override
	public ItemStack createItemStack() {
		return new ItemStack(Items.SLIME_BLOCK);
	}

	@Override
	public List<String> createDescription() {
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatFormatting.GREEN + "Validate changes");
		
		return lore;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int modifiers) {
		if(this.gui == null) {
			return false;
		}
		
		this.gui.validateChanges();
		
		/*
		 * TODO : Play sound
		 */
		
		return true;
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
