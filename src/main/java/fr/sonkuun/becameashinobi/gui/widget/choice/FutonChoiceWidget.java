package fr.sonkuun.becameashinobi.gui.widget.choice;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.elemental.ElementalNature;
import fr.sonkuun.becameashinobi.gui.ValidationGui;
import fr.sonkuun.becameashinobi.gui.widget.FutonSkillTreeWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public class FutonChoiceWidget extends FutonSkillTreeWidget {

	public FutonChoiceWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public List<String> getScreenName() {
		List<String> list = new ArrayList<String>();
		
		list.add(ChatFormatting.GREEN + "Futon");
		
		return list;
	}

	@Override
	public Screen getGuiToDisplay() {
		return new ValidationGui(ElementalNature.FUTON, this, Minecraft.getInstance().player.connection.getAdvancementManager());
	}

}
