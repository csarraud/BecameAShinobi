package fr.sonkuun.becameashinobi.gui.widget;

import fr.sonkuun.becameashinobi.BecameAShinobi;
import fr.sonkuun.becameashinobi.gui.jutsu.KatonJutsuSkillGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class KatonSkillTreeWidget extends AbstractSkillTreeWidget {


	public KatonSkillTreeWidget(int xIn, int yIn, String msg) {
		super(xIn, yIn, msg);
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(BecameAShinobi.MODID, "textures/gui/element.png");
	}

	@Override
	public String getScreenName() {
		return "Katon Skill Tree";
	}

	@Override
	public Screen getGuiToDisplay() {
		return new KatonJutsuSkillGui(Minecraft.getInstance().player.connection.getAdvancementManager());
	}

	@Override
	public int getOffsetX() {
		return 0;
	}

	@Override
	public int getOffsetY() {
		return 0;
	}

}
