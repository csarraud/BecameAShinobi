package fr.sonkuun.becameashinobi.capability.component.nature;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class SuitonNature extends AbstractNature {

	public SuitonNature() {
		this(0);
	}
	
	public SuitonNature(SuitonNature suitonNature) {
		this(suitonNature.getValue());
	}
	
	public SuitonNature(int value) {
		super(ElementalNature.SUITON, value);
	}

	@Override
	public ChatFormatting getChatFormattingColor() {
		return ChatFormatting.AQUA;
	}
}
