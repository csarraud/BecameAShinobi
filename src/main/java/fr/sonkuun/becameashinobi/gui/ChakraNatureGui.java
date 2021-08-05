package fr.sonkuun.becameashinobi.gui;

import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.capability.ShinobiData;
import fr.sonkuun.becameashinobi.gui.common.AbstractCustomGui;
import fr.sonkuun.becameashinobi.gui.widget.AbstractSkillWidget;
import fr.sonkuun.becameashinobi.gui.widget.nature.DotonWidget;
import fr.sonkuun.becameashinobi.gui.widget.nature.FutonWidget;
import fr.sonkuun.becameashinobi.gui.widget.nature.KatonWidget;
import fr.sonkuun.becameashinobi.gui.widget.nature.RaitonWidget;
import fr.sonkuun.becameashinobi.gui.widget.nature.SuitonWidget;
import fr.sonkuun.becameashinobi.gui.widget.IUpgradeNatureWidget;
import fr.sonkuun.becameashinobi.gui.widget.ValidationNaturePointWidget;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class ChakraNatureGui extends AbstractCustomGui {

	private int naturePoints;
	
	public ChakraNatureGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		ClientPlayerEntity player = this.minecraft.player;
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			this.naturePoints = data.getNaturePoint();
		}
		
		this.skillWidgets.add(new KatonWidget(190, 30, 20, 20));
		this.skillWidgets.add(new SuitonWidget(140, 80, 20, 20));
		this.skillWidgets.add(new DotonWidget(160, 130, 20, 20));
		this.skillWidgets.add(new RaitonWidget(220, 130, 20, 20));
		this.skillWidgets.add(new FutonWidget(240, 80, 20, 20));
		
		this.skillWidgets.add(new ValidationNaturePointWidget(this, 300, 150, 20, 20));
		
		super.init();
	}
	
	@Override
	public void renderAfter(int left, int top, int right, int bottom) {
		
		this.font.drawString(naturePoints + "", right - 80, top + 6, 0xFF0000);
		this.font.drawString("nature point", right - 70, top + 6, 0x404040);
		
		super.renderAfter(left, top, right, bottom);
	}

	@Override
	public boolean mouseClicked(double x, double y, int modifiers) {

		for(AbstractSkillWidget skillItem : this.skillWidgets) {
			if(skillItem.isMouseOver()) {
				if(skillItem instanceof IUpgradeNatureWidget) {
					naturePoints = ((IUpgradeNatureWidget) skillItem).mouseClickedWithNaturePoint(naturePoints, x, y, modifiers);
				}
				else {
					skillItem.mouseClicked(x, y, modifiers);
				}
			}
		}
		
		return super.mouseClicked(x, y, modifiers);
	}
	
	public void validateChanges() {
		
		for(AbstractSkillWidget widget : this.skillWidgets) {
			if(widget instanceof IUpgradeNatureWidget) {
				((IUpgradeNatureWidget) widget).validateChanges();
			}
		}
		
		ClientPlayerEntity player = this.minecraft.player;
		
		if(player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).isPresent()) {
			ShinobiData data = player.getCapability(CapabilityBecameAShinobi.CAPABILITY_SHINOBI).orElse(null);
			data.sendDataToServer(player);
		}
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80, 80);
	}

	@Override
	protected String getGuiTitle() {
		return "Chakra nature";
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
