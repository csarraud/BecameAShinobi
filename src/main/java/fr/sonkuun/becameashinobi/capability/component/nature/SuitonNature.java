package fr.sonkuun.becameashinobi.capability.component.nature;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class SuitonNature extends AbstractNature {

	public SuitonNature() {
		this(0, 0);
	}
	
	public SuitonNature(SuitonNature suitonNature) {
		this(suitonNature.getLevel(), suitonNature.getXp());
	}
	
	public SuitonNature(int level, int xp) {
		super(ElementalNature.SUITON, level, xp);
	}

	@Override
	public ChatFormatting getChatFormattingColor() {
		return ChatFormatting.AQUA;
	}
}
