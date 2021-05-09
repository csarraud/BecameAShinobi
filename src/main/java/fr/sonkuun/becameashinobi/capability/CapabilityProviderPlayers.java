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
	private ElementalNatureData elementalNatureData = new ElementalNatureData();

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (CapabilityBecameAShinobi.CAPABILITY_SHINOBI == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> shinobiData);
		}
		else if(CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> elementalNatureData);
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
		
		INBT elementalNatureDataNBT = CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE.writeNBT(elementalNatureData, NO_SPECIFIC_SIDE);
		nbt.put(ELEMENTAL_NATURE_NBT, elementalNatureDataNBT);
		
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		
		INBT shinobiDataNBT = tag.get(SHINOBI_DATA_NBT);
		CapabilityBecameAShinobi.CAPABILITY_SHINOBI.readNBT(shinobiData, NO_SPECIFIC_SIDE, shinobiDataNBT);
		
		INBT elementalNatureDataNBT = tag.get(ELEMENTAL_NATURE_NBT);
		CapabilityBecameAShinobi.CAPABILITY_ELEMENTAL_NATURE.readNBT(elementalNatureData, NO_SPECIFIC_SIDE, elementalNatureDataNBT);
	}

}
