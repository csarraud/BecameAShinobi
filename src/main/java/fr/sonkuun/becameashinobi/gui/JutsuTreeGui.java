package fr.sonkuun.becameashinobi.gui;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

public class JutsuTreeGui extends Screen implements ClientAdvancementManager.IListener {

	protected JutsuTreeGui(ClientAdvancementManager clientAdvancementManager) {
		super(NarratorChatListener.EMPTY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rootAdvancementAdded(Advancement advancementIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rootAdvancementRemoved(Advancement advancementIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nonRootAdvancementAdded(Advancement advancementIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nonRootAdvancementRemoved(Advancement advancementIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advancementsCleared() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateAdvancementProgress(Advancement arg0, AdvancementProgress arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedTab(Advancement arg0) {
		// TODO Auto-generated method stub
		
	}
}
