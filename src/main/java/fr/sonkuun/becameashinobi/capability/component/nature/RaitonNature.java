package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class RaitonNature extends AbstractNature {

	public RaitonNature() {
		this(0, 0);
	}
	
	public RaitonNature(RaitonNature raitonNature) {
		this(raitonNature.getLevel(), raitonNature.getXp());
	}
	
	public RaitonNature(int level, int xp) {
		super(ElementalNature.RAITON, level, xp);
	}
}
