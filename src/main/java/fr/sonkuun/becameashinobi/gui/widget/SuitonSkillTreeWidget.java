package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.gui.jutsu.SuitonJutsuSkillGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class SuitonSkillTreeWidget extends AbstractSkillTreeWidget {

	public SuitonSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(BecameAShinobi.MODID, "textures/gui/element.png");
	}

	@Override
	public int getOffsetX() {
		return 16;
	}

	@Override
	public int getOffsetY() {
		return 0;
	}

	@Override
	public List<String> getScreenName() {
		List<String> list = new ArrayList<String>();
		
		list.add(ChatFormatting.BLUE + "Suiton skill tree");
		
		return list;
	}

	@Override
	public Screen getGuiToDisplay() {
		return new SuitonJutsuSkillGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

}
