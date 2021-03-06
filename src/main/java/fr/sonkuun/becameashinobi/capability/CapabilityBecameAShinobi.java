package fr.sonkuun.becameashinobi.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityBecameAShinobi {
	
	@CapabilityInject(ChakraData.class)
	public static Capability<ChakraData> CAPABILITY_BECAME_A_SHINOBI = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(
				ChakraData.class,
				new ChakraData.ShinobiWeaponDataNBTStorage(),
				ChakraData::createADefaultInstance);
	}
}
