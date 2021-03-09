package fr.sonkuun.becameashinobi.gui.widget.choice;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.gui.widget.SuitonSkillTreeWidget;
import net.minecraft.client.gui.screen.Screen;

public class SuitonChoiceWidget extends SuitonSkillTreeWidget {

	public SuitonChoiceWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public List<String> getScreenName() {
		List<String> list = new ArrayList<String>();
		
		list.add(ChatFormatting.BLUE + "Suiton");
		
		return list;
	}

	@Override
	public Screen getGuiToDisplay() {
		return null;
	}

}
