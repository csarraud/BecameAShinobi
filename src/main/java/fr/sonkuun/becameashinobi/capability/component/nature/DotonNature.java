package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class DotonNature extends AbstractNature {

	public DotonNature() {
		this(0, 0);
	}
	
	public DotonNature(DotonNature dotonNature) {
		this(dotonNature.getLevel(), dotonNature.getXp());
	}
	
	public DotonNature(int level, int xp) {
		super(ElementalNature.DOTON, level, xp);
	}
}
