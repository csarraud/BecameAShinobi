package fr.sonkuun.becameashinobi.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class ElementalNatureData {
	

	public ElementalNatureData() {
	}
	
	public ElementalNatureData(ElementalNatureData data) {
	}
	
	public void synchronize() {
	}
	
	public static ElementalNatureData fromPacketBuffer(PacketBuffer buffer) {
		return new ElementalNatureData();
	}

	public static class ElementalNatureNBTStorage implements Capability.IStorage<ElementalNatureData> {

		@Override
		public INBT writeNBT(Capability<ElementalNatureData> capability, ElementalNatureData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			return tag;
		}

		@Override
		public void readNBT(Capability<ElementalNatureData> capability, ElementalNatureData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

			}
		}

	}

	public static ElementalNatureData createADefaultInstance() {
		return new ElementalNatureData();
	}
}
