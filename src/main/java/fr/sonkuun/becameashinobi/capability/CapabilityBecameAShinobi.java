package fr.sonkuun.becameashinobi.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityBecameAShinobi {
	
	@CapabilityInject(ChakraData.class)
	public static Capability<ChakraData> CAPABILITY_CHAKRA = null;

	@CapabilityInject(HealthData.class)
	public static Capability<HealthData> CAPABILITY_HEALTH = null;
	
	@CapabilityInject(ElementalNatureData.class)
	public static Capability<ElementalNatureData> CAPABILITY_ELEMENTAL_NATURE = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(
				ChakraData.class,
				new ChakraData.ChakraDataNBTStorage(),
				ChakraData::createADefaultInstance);

		CapabilityManager.INSTANCE.register(
				HealthData.class,
				new HealthData.HealthDataNBTStorage(),
				HealthData::createADefaultInstance);
		
		CapabilityManager.INSTANCE.register(
				ElementalNatureData.class, 
				new ElementalNatureData.ElementalNatureNBTStorage(),
				ElementalNatureData::createADefaultInstance);
	}
}
