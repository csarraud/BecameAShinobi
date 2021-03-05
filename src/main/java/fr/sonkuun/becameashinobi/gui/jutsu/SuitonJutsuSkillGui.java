package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.suiton.WaterProjectionSkillObject;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class SuitonJutsuSkillGui extends AbstractJutsuGui {
	
	public SuitonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.skillObjects.add(new WaterProjectionSkillObject(20, 20, 20, 20));
		
		super.init();
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80, 80);
	}

	@Override
	public String getGuiTitle() {
		return "Suiton Skill Tree";
	}
}
