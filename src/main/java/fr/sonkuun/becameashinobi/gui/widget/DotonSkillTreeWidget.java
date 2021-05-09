package fr.sonkuun.becameashinobi.gui.widget;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.gui.jutsu.DotonJutsuSkillGui;
import fr.sonkuun.becameashinobi.gui.widget.common.AbstractSkillTreeWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class DotonSkillTreeWidget extends AbstractSkillTreeWidget {

	public DotonSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(BecameAShinobi.MODID, "textures/gui/element.png");
	}

	@Override
	public int getOffsetX() {
		return 64;
	}

	@Override
	public int getOffsetY() {
		return 0;
	}

	@Override
	public List<String> getScreenName() {
		List<String> list = new ArrayList<String>();
		
		list.add(ChatFormatting.GRAY + "Doton skill tree");
		
		return list;
	}

	@Override
	public Screen getGuiToDisplay() {
		return new DotonJutsuSkillGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

}
