package fr.sonkuun.becameashinobi.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class ChakraData {

	private double chakraMaxValue;
	private double chakraValue;

	public ChakraData() {
		this.chakraMaxValue = 0;
		this.chakraValue = 0;
	}
	
	public void setChakraValues(double maxValue, double value) {
		this.chakraMaxValue = maxValue;
		this.chakraValue = value;
	}
	
	public static final String CHAKRA_MAX_VALUE_NBT = "chakra_max_value";
	public static final String CHAKRA_VALUE_NBT = "chakra_value";

	public static class ShinobiWeaponDataNBTStorage implements Capability.IStorage<ChakraData> {

		@Override
		public INBT writeNBT(Capability<ChakraData> capability, ChakraData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putDouble(CHAKRA_MAX_VALUE_NBT, instance.chakraMaxValue);
			tag.putDouble(CHAKRA_VALUE_NBT, instance.chakraValue);

			return tag;
		}

		@Override
		public void readNBT(Capability<ChakraData> capability, ChakraData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

				instance.setChakraValues(
						tag.getDouble(CHAKRA_MAX_VALUE_NBT),
						tag.getDouble(CHAKRA_VALUE_NBT));
			}
		}

	}

	public static ChakraData createADefaultInstance() {
		return new ChakraData();
	}

}