package fr.sonkuun.becameashinobi.capability.component.nature;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class FutonNature extends AbstractNature {

	public FutonNature() {
		this(0);
	}
	
	public FutonNature(FutonNature futonNature) {
		this(futonNature.getValue());
	}
	
	public FutonNature(int value) {
		super(ElementalNature.FUTON, value);
	}

	@Override
	public ChatFormatting getChatFormattingColor() {
		return ChatFormatting.GREEN;
	}
}
