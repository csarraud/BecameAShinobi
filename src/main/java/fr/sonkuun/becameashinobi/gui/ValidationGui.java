package fr.sonkuun.becameashinobi.gui;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;
import fr.sonkuun.becameashinobi.gui.jutsu.common.AbstractJutsuGui;
import fr.sonkuun.becameashinobi.gui.widget.choice.NoWidget;
import fr.sonkuun.becameashinobi.gui.widget.choice.YesWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ValidationGui extends AbstractJutsuGui {

	private ElementalNature nature;
	private Widget widgetClicked;
	
	public ValidationGui(ElementalNature nature, Widget widgetClicked, ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
		
		this.nature = nature;
		this.widgetClicked = widgetClicked;
	}

	@Override
	protected void init() {
		
		this.widgetClicked.x = -25;
		this.widgetClicked.y = -50;
		
		this.addButton(this.widgetClicked);
		this.addButton(new YesWidget(nature, -75, 15, 25, 25, ChatFormatting.GREEN + "Yes"));
		this.addButton(new NoWidget(nature, 30, 15, 25, 25, ChatFormatting.RED + "No"));
		
		super.init();
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 100, 100, 100);
	}

	@Override
	public String getGuiTitle() {
		return "Choose " + nature.getName() + " ?";
	}

}
