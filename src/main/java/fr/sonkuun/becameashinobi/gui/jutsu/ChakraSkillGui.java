package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.widget.DotonSkillTreeWidget;
import fr.sonkuun.becameashinobi.gui.widget.FutonSkillTreeWidget;
import fr.sonkuun.becameashinobi.gui.widget.KatonSkillTreeWidget;
import fr.sonkuun.becameashinobi.gui.widget.RaitonSkillTreeWidget;
import fr.sonkuun.becameashinobi.gui.widget.SuitonSkillTreeWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChakraSkillGui extends AbstractJutsuGui {

	public ChakraSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.addButton(new KatonSkillTreeWidget(250, 100, "KATON"));
		this.addButton(new SuitonSkillTreeWidget(200, 150, "SUITON"));
		this.addButton(new FutonSkillTreeWidget(300, 150, "FUTON"));
		this.addButton(new RaitonSkillTreeWidget(280, 200, "RAITON"));
		this.addButton(new DotonSkillTreeWidget(230, 200, "DOTON"));
		
		super.init();
		
	}

	@Override
	public String getGuiTitle() {
		return "Chakra Skills";
	}

	@Override
	protected Color getFontColor() {
		return new Color(100, 198, 198, 198);
	}

}
