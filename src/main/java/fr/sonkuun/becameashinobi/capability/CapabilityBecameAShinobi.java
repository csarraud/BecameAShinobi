package fr.sonkuun.becameashinobi.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityBecameAShinobi {

	@CapabilityInject(ShinobiData.class)
	public static Capability<ShinobiData> CAPABILITY_SHINOBI = null;
	
	@CapabilityInject(ElementalNatureData.class)
	public static Capability<ElementalNatureData> CAPABILITY_ELEMENTAL_NATURE = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(
				ShinobiData.class,
				new ShinobiData.ShinobiDataNBTStorage(),
				ShinobiData::createADefaultInstance);
		
		CapabilityManager.INSTANCE.register(
				ElementalNatureData.class, 
				new ElementalNatureData.ElementalNatureNBTStorage(),
				ElementalNatureData::createADefaultInstance);
	}
}
