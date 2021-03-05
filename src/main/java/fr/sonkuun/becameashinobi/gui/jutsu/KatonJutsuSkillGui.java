package fr.sonkuun.becameashinobi.gui.jutsu;

import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.AbstractSkillObject;
import fr.sonkuun.becameashinobi.gui.jutsu.skillobject.FireballSkillObject;
import fr.sonkuun.becameashinobi.util.Color;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class KatonJutsuSkillGui extends AbstractJutsuGui {

	public KatonJutsuSkillGui(ClientAdvancementManager clientAdvancementManager) {
		super(clientAdvancementManager);
	}

	@Override
	protected void init() {
		
		this.skillObjects.add(new FireballSkillObject(20, 20, 20, 20));
		
		super.init();
	}

	@Override
	protected void drawInside(int boxLeft, int boxTop, int boxRight, int boxBottom, int insideWidth, int insideHeight) {
		//System.out.println(String.format("[Zoom = %s, deltaX = %s, deltaY = %s]",
				//this.zoom, this.deltaX, this.deltaY));
		
		for(AbstractSkillObject skillItem : this.skillObjects) {
			this.itemRenderer.renderItemIntoGUI(skillItem.getItemstack(), skillItem.getX() + this.deltaX, skillItem.getY() + this.deltaY);
		}
		
		for(AbstractSkillObject skillItem : this.skillObjects) {
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
