package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class FutonNature extends AbstractNature {

	public FutonNature() {
		this(0, 0);
	}
	
	public FutonNature(FutonNature futonNature) {
		this(futonNature.getLevel(), futonNature.getXp());
	}
	
	public FutonNature(int level, int xp) {
		super(ElementalNature.FUTON, level, xp);
	}
}
