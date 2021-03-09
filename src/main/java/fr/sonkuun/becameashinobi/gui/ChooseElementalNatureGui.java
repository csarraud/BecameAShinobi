package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.gui.jutsu.AbstractJutsuGui;
import fr.sonkuun.becameashinobi.gui.widget.choice.DotonChoiceWidget;
import fr.sonkuun.becameashinobi.gui.widget.choice.FutonChoiceWidget;
import fr.sonkuun.becameashinobi.gui.widget.choice.KatonChoiceWidget;
import fr.sonkuun.becameashinobi.gui.widget.choice.RaitonChoiceWidget;
import fr.sonkuun.becameashinobi.gui.widget.choice.SuitonChoiceWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChooseElementalNatureGui extends AbstractJutsuGui {

	public ChooseElementalNatureGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {

		this.addButton(new KatonChoiceWidget(250, 100, "KATON"));
		this.addButton(new SuitonChoiceWidget(200, 150, "SUITON"));
		this.addButton(new FutonChoiceWidget(300, 150, "FUTON"));
		this.addButton(new RaitonChoiceWidget(280, 200, "RAITON"));
		this.addButton(new DotonChoiceWidget(230, 200, "DOTON"));
		
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
