package fr.sonkuun.becameashinobi.capability.component;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public interface INature {
	
	public ElementalNature getNature();
	public int getLevel();
	public int getXp();
	public ChatFormatting getChatFormattingColor();
}
