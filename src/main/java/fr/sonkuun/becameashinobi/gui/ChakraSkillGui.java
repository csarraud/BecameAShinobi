package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.gui.widget.JutsuTreeGuiWidget;
import fr.sonkuun.becameashinobi.gui.widget.KatonSkillTreeWidget;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChakraSkillGui extends AbstractJutsuGui {

	public ChakraSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.addButton(new KatonSkillTreeWidget(250, 100, "KATON"));
		this.addButton(new JutsuTreeGuiWidget(200, 150, "SUITON"));
		this.addButton(new JutsuTreeGuiWidget(300, 150, "FUTON"));
		this.addButton(new JutsuTreeGuiWidget(280, 200, "RAITON"));
		this.addButton(new JutsuTreeGuiWidget(230, 200, "DOTON"));
		
		super.init();
		
	}

	@Override
	public String getGuiTitle() {
		return "Chakra Skills";
	}

}
