package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.gui.jutsu.AbstractJutsuGui;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChooseElementalNatureGui extends AbstractJutsuGui {

	public ChooseElementalNatureGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		
		
		super.init();
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 100, 100, 100);
	}

	@Override
	public String getGuiTitle() {
		return "Choose your nature";
	}
	
}
