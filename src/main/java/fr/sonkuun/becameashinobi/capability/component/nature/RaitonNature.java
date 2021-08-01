package fr.sonkuun.becameashinobi.capability.component.nature;

import fr.sonkuun.becameashinobi.capability.component.AbstractNature;
import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public class RaitonNature extends AbstractNature {

	public RaitonNature() {
		super(ElementalNature.RAITON, 0, 0);
	}
	
	public RaitonNature(RaitonNature raitonNature) {
		super(ElementalNature.RAITON, raitonNature.getLevel(), raitonNature.getXp());
	}
	
	public RaitonNature(ElementalNature nature, int level, int xp) {
		super(nature, level, xp);
	}
}
