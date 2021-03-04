package fr.sonkuun.becameashinobi.gui.widget;

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
	public String getScreenName() {
		return "Suiton Skill Tree";
	}

	@Override
	public Screen getGuiToDisplay() {
		return new SuitonJutsuSkillGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

}
