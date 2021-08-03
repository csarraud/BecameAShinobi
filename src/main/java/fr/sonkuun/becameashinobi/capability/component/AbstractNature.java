package fr.sonkuun.becameashinobi.capability.component;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;

public abstract class AbstractNature implements INature {
	
	private ElementalNature nature;
	private int value;
	
	public AbstractNature(ElementalNature nature, int value) {
		this.nature = nature;
		this.value = value;
	}
	
	public AbstractNature(AbstractNature natureToCopy) {
		this.nature = natureToCopy.getNature();
		this.value = natureToCopy.getValue();
	}

	@Override
	public ElementalNature getNature() {
		return nature;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int level) {
		this.value = level;
	}

	@Override
	public void addValue(int increment) {
		this.value += increment;
	}	
}
