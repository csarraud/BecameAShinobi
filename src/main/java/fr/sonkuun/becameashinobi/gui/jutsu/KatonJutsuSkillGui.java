package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class KatonJutsuSkillGui extends AbstractJutsuGui {

	public KatonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {
		
	}

	@Override
	public String getGuiTitle() {
		return "Katon Skill Tree";
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80, 80);
	}
	
}
