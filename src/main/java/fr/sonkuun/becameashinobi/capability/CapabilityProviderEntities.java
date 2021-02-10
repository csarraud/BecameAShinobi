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

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> chakraData);
		}

		return LazyOptional.empty();
	}

	private static final String CHAKRA_DATA_NBT = "chakra_data";

	@Override
	public INBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();

		INBT chakraDataNBT = CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI.writeNBT(chakraData, NO_SPECIFIC_SIDE);

		nbt.put(CHAKRA_DATA_NBT, chakraDataNBT);
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		INBT chakraDataNBT = tag.get(CHAKRA_DATA_NBT);
		
		CapabilityBecameAShinobi.CAPABILITY_BECAME_A_SHINOBI.readNBT(chakraData, NO_SPECIFIC_SIDE, chakraDataNBT);
	}

}
