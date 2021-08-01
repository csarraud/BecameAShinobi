package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class KatonNature extends AbstractNature {

	public KatonNature() {
		this(0, 0);
	}
	
	public KatonNature(KatonNature katonNature) {
		this(katonNature.getLevel(), katonNature.getXp());
	}
	
	public KatonNature(int level, int xp) {
		super(ElementalNature.KATON, level, xp);
	}
}
