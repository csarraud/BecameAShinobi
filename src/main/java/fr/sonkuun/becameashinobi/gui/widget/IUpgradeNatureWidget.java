package fr.sonkuun.becameashinobi.gui.widget;

public interface IUpgradeNatureWidget {

	public int mouseClickedWithNaturePoint(int naturePoint, double mouseX, double mouseY, int modifiers);
	public void validateChanges();
}
