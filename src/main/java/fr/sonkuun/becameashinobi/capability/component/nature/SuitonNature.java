package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class SuitonNature extends AbstractNature {

	public SuitonNature() {
		super(ElementalNature.SUITON, 0, 0);
	}
	
	public SuitonNature(SuitonNature suitonNature) {
		super(ElementalNature.SUITON, suitonNature.getLevel(), suitonNature.getXp());
	}
	
	public SuitonNature(ElementalNature nature, int level, int xp) {
		super(nature, level, xp);
	}
}
