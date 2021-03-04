package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class FutonJutsuSkillGui extends AbstractJutsuGui {

	public FutonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80,80);
	}

	@Override
	public String getGuiTitle() {
		return "Futon Skill Tree";
	}
}
