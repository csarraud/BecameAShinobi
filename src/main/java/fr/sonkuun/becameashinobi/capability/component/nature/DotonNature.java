package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class DotonNature extends AbstractNature {

	public DotonNature() {
		super(ElementalNature.DOTON, 0, 0);
	}
	
	public DotonNature(DotonNature dotonNature) {
		super(ElementalNature.DOTON, dotonNature.getLevel(), dotonNature.getXp());
	}
	
	public DotonNature(ElementalNature nature, int level, int xp) {
		super(nature, level, xp);
	}
}
