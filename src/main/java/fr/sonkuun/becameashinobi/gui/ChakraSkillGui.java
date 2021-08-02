package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.gui.common.AbstractCustomGui;
import fr.sonkuun.becameashinobi.gui.widget.EyeJutsuWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChakraSkillGui extends AbstractCustomGui {

	protected ChakraSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {

		this.skillWidgets.add(new EyeJutsuWidget(200, 20, 20, 20));
		
		super.init();
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80, 80);
	}

	@Override
	public String getGuiTitle() {
		return "Chakra Skill";
	}

	@Override
	protected boolean canZoom() {
		return false;
	}

	@Override
	protected boolean canMove() {
		return false;
	}

}
