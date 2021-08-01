package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class KatonNature extends AbstractNature {

	public KatonNature() {
		super(ElementalNature.KATON, 0, 0);
	}
	
	public KatonNature(ElementalNature nature, int level, int xp) {
		super(nature, level, xp);
	}

}
