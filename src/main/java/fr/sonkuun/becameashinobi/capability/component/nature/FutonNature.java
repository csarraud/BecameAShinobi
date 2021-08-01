package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class FutonNature extends AbstractNature {

	public FutonNature() {
		super(ElementalNature.FUTON, 0, 0);
	}
	
	public FutonNature(FutonNature futonNature) {
		super(ElementalNature.FUTON, futonNature.getLevel(), futonNature.getXp());
	}
	
	public FutonNature(ElementalNature nature, int level, int xp) {
		super(nature, level, xp);
	}
}
