package fr.sonkuun.becameashinobi.elemental;

public enum ElementalNature {
	KATON("Katon"),
	FUTON("Futon"),
	RAITON("Raiton"),
	DOTON("Doton"),
	SUITON("Suiton");

	private String name;
	
	ElementalNature(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
