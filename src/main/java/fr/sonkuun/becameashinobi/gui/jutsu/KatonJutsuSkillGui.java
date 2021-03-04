package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.geom.Rect;
import fr.sonkuun.becameashinobi.util.Color;
import fr.sonkuun.becameashinobi.util.GlUtil;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class KatonJutsuSkillGui extends AbstractJutsuGui {

	public KatonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {
        GlUtil.drawRect(new Rect(0, 0, insideWidth, insideHeight), new Color(50, 100, 100, 100));
		
	}

	@Override
	public String getGuiTitle() {
		return "Katon Skill Tree";
	}
	
}
