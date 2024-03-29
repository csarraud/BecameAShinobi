package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.jutsu.common.AbstractJutsuGui;
import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.katon.FireballSkillObject;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class KatonJutsuSkillGui extends AbstractJutsuGui {

	public KatonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.skillObjects.add(new FireballSkillObject(20, 20, 20, 20));
		
		super.init();
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
