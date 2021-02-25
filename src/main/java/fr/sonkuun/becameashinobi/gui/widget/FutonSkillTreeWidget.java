package fr.sonkuun.becameashinobi.gui.widget;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class FutonSkillTreeWidget extends AbstractSkillTreeWidget {

	public FutonSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(BecameAShinobi.MODID, "textures/gui/element.png");
	}

	@Override
	public int getOffsetX() {
		return 32;
	}

	@Override
	public int getOffsetY() {
		return 0;
	}

	@Override
	public String getScreenName() {
		return "Futon Skill Tree";
	}

	@Override
	public Screen getGuiToDisplay() {
		return null;
	}

}
