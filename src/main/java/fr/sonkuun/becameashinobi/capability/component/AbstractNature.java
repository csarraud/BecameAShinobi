package fr.sonkuun.becameashinobi.capability.component;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public abstract class AbstractNature implements INature {
	
	private ElementalNature nature;
	private int level;
	private int xp;
	
	public AbstractNature(ElementalNature nature, int level, int xp) {
		this.nature = nature;
		this.level = level;
		this.xp = xp;
	}
	
	public AbstractNature(AbstractNature natureToCopy) {
		this.nature = natureToCopy.getNature();
		this.level = natureToCopy.getLevel();
		this.xp = natureToCopy.getXp();
	}

	@Override
	public ElementalNature getNature() {
		return nature;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getXp() {
		return xp;
	}
	
}
