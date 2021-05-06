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

		this.addButton(new KatonChoiceWidget(5, -35, "KATON"));
		this.addButton(new SuitonChoiceWidget(-40, 0, "SUITON"));
		this.addButton(new FutonChoiceWidget(50, 0, "FUTON"));
		this.addButton(new RaitonChoiceWidget(35, 45, "RAITON"));
		this.addButton(new DotonChoiceWidget(-25, 45, "DOTON"));
		
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
