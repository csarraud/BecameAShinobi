package fr.sonkuun.becameashinobi.capability.component;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public interface INature {
	
	public ElementalNature getNature();
	public int getValue();
	public void setValue(int value);
	public void addValue(int increment);
	public ChatFormatting getChatFormattingColor();
}
