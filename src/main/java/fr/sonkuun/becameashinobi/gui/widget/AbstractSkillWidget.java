package fr.sonkuun.becameashinobi.gui.widget;

import java.util.List;

import net.minecraft.item.ItemStack;

public abstract class AbstractSkillWidget {

	protected int x, y, width, height;
	protected boolean isMouseOver;
	protected ItemStack itemstack;
	protected List<String> description;
	
	public AbstractSkillWidget(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.isMouseOver = false;
		this.itemstack = createItemStack();
		this.description = createDescription();
	}
	
	public boolean isMouseOver(double x, double y, int deltaX, int deltaY) {
		isMouseOver = (x >= this.x + deltaX && x <= this.x + deltaX + this.width) && (y >= this.y + deltaY && y <= this.y + deltaY + this.height);
		return isMouseOver;
	}
	
	public boolean isMouseOver() {
		return isMouseOver;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ItemStack getItemstack() {
		return itemstack;
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	protected abstract ItemStack createItemStack();
	protected abstract List<String> createDescription();

}
