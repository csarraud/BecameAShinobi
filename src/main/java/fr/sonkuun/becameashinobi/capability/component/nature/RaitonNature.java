package fr.sonkuun.becameashinobi.capability.component.nature;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class RaitonNature extends AbstractNature {

	public RaitonNature() {
		this(0);
	}
	
	public RaitonNature(RaitonNature raitonNature) {
		this(raitonNature.getValue());
	}
	
	public RaitonNature(int value) {
		super(ElementalNature.RAITON, value);
	}

	@Override
	public ChatFormatting getChatFormattingColor() {
		return ChatFormatting.YELLOW;
	}
}
