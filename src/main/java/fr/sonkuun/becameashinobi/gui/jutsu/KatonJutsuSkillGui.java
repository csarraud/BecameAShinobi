package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.jutsu.items.AbstractSkillItem;
import fr.sonkuun.becameashinobi.gui.jutsu.items.KatonActiveSkillItem;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class KatonJutsuSkillGui extends AbstractJutsuGui {

	public KatonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.skillItems.add(new KatonActiveSkillItem(20, 20, 20, 20));
		
		super.init();
	}

	@Override
	protected void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {
		//System.out.println(String.format("[Zoom = %s, deltaX = %s, deltaY = %s]",
				//this.zoom, this.deltaX, this.deltaY));
		
		for(AbstractSkillItem skillItem : this.skillItems) {
			this.itemRenderer.renderItemIntoGUI(skillItem.getItemstack(), skillItem.getX() + this.deltaX, skillItem.getY() + this.deltaY);
		}
		
		for(AbstractSkillItem skillItem : this.skillItems) {
			if(skillItem.isMouseOver()) {
				this.renderTooltip(skillItem.getDescription(), skillItem.getX() + 5 + this.deltaX, skillItem.getY() + this.deltaY);
			}
		}
	}

	@Override
	public String getGuiTitle() {
		return "Katon Skill Tree";
	}

	@Override
	protected Color getFontColor() {
		return new Color(50, 80, 80, 80);
	}
	
}
