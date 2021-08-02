package fr.sonkuun.becameashinobi.capability;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityProviderPlayers implements ICapabilitySerializable<INBT> {

	private final Direction NO_SPECIFIC_SIDE = null;

	private ShinobiData shinobiData = new ShinobiData();

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (CapabilityBecameAShinobi.CAPABILITY_SHINOBI == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> shinobiData);
		}

		return LazyOptional.empty();
	}

	private static final String SHINOBI_DATA_NBT = "shinobi_data";
	private static final String ELEMENTAL_NATURE_NBT = "elemental_nature";

	@Override
	public INBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();

		INBT shinobiDataNBT = CapabilityBecameAShinobi.CAPABILITY_SHINOBI.writeNBT(shinobiData, NO_SPECIFIC_SIDE);
		nbt.put(SHINOBI_DATA_NBT, shinobiDataNBT);
		
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		
		INBT shinobiDataNBT = tag.get(SHINOBI_DATA_NBT);
		CapabilityBecameAShinobi.CAPABILITY_SHINOBI.readNBT(shinobiData, NO_SPECIFIC_SIDE, shinobiDataNBT);
	}

}
