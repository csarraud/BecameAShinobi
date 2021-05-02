package fr.sonkuun.becameashinobi.capability;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityProviderEntities implements ICapabilitySerializable<INBT> {

	private final Direction NO_SPECIFIC_SIDE = null;

	private ChakraData chakraData = new ChakraData();
	private HealthData healthData = new HealthData();
 
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (CapabilityBecameAShinobi.CAPABILITY_CHAKRA == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> chakraData);
		}
		else if(CapabilityBecameAShinobi.CAPABILITY_HEALTH == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> healthData);
		}

		return LazyOptional.empty();
	}

	private static final String CHAKRA_DATA_NBT = "chakra_data";
	private static final String HEALTH_DATA_NBT = "health_data";

	@Override
	public INBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();

		INBT chakraDataNBT = CapabilityBecameAShinobi.CAPABILITY_CHAKRA.writeNBT(chakraData, NO_SPECIFIC_SIDE);
		nbt.put(CHAKRA_DATA_NBT, chakraDataNBT);

		INBT healthDataNBT = CapabilityBecameAShinobi.CAPABILITY_HEALTH.writeNBT(healthData, NO_SPECIFIC_SIDE);
		nbt.put(HEALTH_DATA_NBT, healthDataNBT);
		
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		
		INBT chakraDataNBT = tag.get(CHAKRA_DATA_NBT);
		CapabilityBecameAShinobi.CAPABILITY_CHAKRA.readNBT(chakraData, NO_SPECIFIC_SIDE, chakraDataNBT);

		INBT healthDataNBT = tag.get(HEALTH_DATA_NBT);
		CapabilityBecameAShinobi.CAPABILITY_HEALTH.readNBT(healthData, NO_SPECIFIC_SIDE, healthDataNBT);
	}
	
}
