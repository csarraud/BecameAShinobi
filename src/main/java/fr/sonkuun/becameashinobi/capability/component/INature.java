package fr.sonkuun.becameashinobi.capability.component;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public interface INature {
	
	public ElementalNature getNature();
	public int getLevel();
	public void setLevel(int level);
	public void addLevel(int increment);
	public int getXp();
	public void setXp(int xp);
	public void addXp(int increment);
	public ChatFormatting getChatFormattingColor();
}
