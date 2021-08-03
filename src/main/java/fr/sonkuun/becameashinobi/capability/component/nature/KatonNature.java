package fr.sonkuun.becameashinobi.capability.component.nature;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class KatonNature extends AbstractNature {

	public KatonNature() {
		this(0);
	}
	
	public KatonNature(KatonNature katonNature) {
		this(katonNature.getValue());
	}
	
	public KatonNature(int value) {
		super(ElementalNature.KATON, value);
	}

	@Override
	public ChatFormatting getChatFormattingColor() {
		return ChatFormatting.RED;
	}
}
