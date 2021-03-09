package fr.sonkuun.becameashinobi.gui.widget.choice;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.widget.KatonSkillTreeWidget;
import net.minecraft.client.gui.screen.Screen;

public class KatonChoiceWidget extends KatonSkillTreeWidget {

	public KatonChoiceWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public List<String> getScreenName() {
		List<String> list = new ArrayList<String>();
		
		list.add(ChatFormatting.RED + "Katon");
		
		return list;
	}

	@Override
	public Screen getGuiToDisplay() {
		return null;
	}

}
