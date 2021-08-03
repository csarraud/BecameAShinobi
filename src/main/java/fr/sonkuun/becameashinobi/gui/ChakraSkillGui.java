package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import fr.sonkuun.becameashinobi.gui.common.AbstractCustomGui;
import fr.sonkuun.becameashinobi.gui.widget.EyeJutsuWidget;
import fr.sonkuun.becameashinobi.gui.widget.ShinobiStatsWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChakraSkillGui extends AbstractCustomGui {

	public ChakraSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {

		this.skillWidgets.add(new ShinobiStatsWidget(100, 20, 20, 20));
		this.skillWidgets.add(new EyeJutsuWidget(190, 20, 20, 20));
		
		super.init();
	}
	
	@Override
	public void renderAfter(int left, int top, int right, int bottom) {
		String naturePoint = "0";
		ClientPlayerEntity player = this.minecraft.player;
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			naturePoint = "" + data.getNaturePoint();
		}
		
		this.font.drawString(naturePoint, right - 80, top + 6, 0xFF0000);
		this.font.drawString("nature point", right - 70, top + 6, 0x404040);
		
		super.renderAfter(left, top, right, bottom);
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
