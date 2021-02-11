package fr.sonkuun.becameashinobi.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class ChakraData {

	private double chakraMaxValue;
	private double chakraValue;
	private double chakraRegenerationPerSecond;
	private int chakraRegenerationTick;

	public ChakraData() {
		this.chakraMaxValue = 100;
		this.chakraValue = 0;
		this.chakraRegenerationPerSecond = 1.0;
		this.chakraRegenerationTick = 0;
	}
	
	public void setChakraValues(double maxValue, double value) {
		this.chakraMaxValue = maxValue;
		this.chakraValue = value;
	}
	
	public void setChakraRegenerationPerSecond(double value) {
		this.chakraRegenerationPerSecond = value;
	}
	
	public void setChakraRegenerationTick(int tick) {
		this.chakraRegenerationTick = tick;
	}
	
	public void updateChakra() {
		if(this.chakraRegenerationTick >= 40) {
			this.addChakra(this.chakraRegenerationPerSecond);
			this.chakraRegenerationTick = 0;
		}
		else {
			this.chakraRegenerationTick++;
		}
	}
	
	public void addChakra(double amount) {
		if((this.chakraValue + amount) > this.chakraMaxValue) {
			this.chakraValue = this.chakraMaxValue;
		}
		else {
			this.chakraValue += amount;
		}
	}
	
	public static final String CHAKRA_MAX_VALUE_NBT = "chakra_max_value";
	public static final String CHAKRA_VALUE_NBT = "chakra_value";
	public static final String CHAKRA_REGENERATION_PER_SECOND_NBT = "chakra_regeneration_per_second";
	public static final String CHAKRA_REGENERATION_TICK_NBT = "chakra_regeneration_tick";

	public static class ShinobiWeaponDataNBTStorage implements Capability.IStorage<ChakraData> {

		@Override
		public INBT writeNBT(Capability<ChakraData> capability, ChakraData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putDouble(CHAKRA_MAX_VALUE_NBT, instance.chakraMaxValue);
			tag.putDouble(CHAKRA_VALUE_NBT, instance.chakraValue);
			tag.putDouble(CHAKRA_REGENERATION_PER_SECOND_NBT, instance.chakraRegenerationPerSecond);
			tag.putInt(CHAKRA_REGENERATION_TICK_NBT, instance.chakraRegenerationTick);

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
				instance.setChakraRegenerationPerSecond(tag.getDouble(CHAKRA_REGENERATION_PER_SECOND_NBT));
				instance.setChakraRegenerationTick(tag.getInt(CHAKRA_REGENERATION_TICK_NBT));
			}
		}

	}

	public static ChakraData createADefaultInstance() {
		return new ChakraData();
	}

}
