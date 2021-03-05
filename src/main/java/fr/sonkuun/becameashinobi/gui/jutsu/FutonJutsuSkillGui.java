package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.futon.WindSkillObject;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class FutonJutsuSkillGui extends AbstractJutsuGui {

	public FutonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.skillObjects.add(new WindSkillObject(20, 20, 20, 20));
		
		super.init();
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
