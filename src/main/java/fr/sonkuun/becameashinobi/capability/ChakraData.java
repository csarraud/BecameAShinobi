package fr.sonkuun.becameashinobi.capability;

import fr.sonkuun.becameashinobi.network.ChakraPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
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
	
	public ChakraData(ChakraData data) {
		this.chakraMaxValue = data.getChakraMaxValue();
		this.chakraValue = data.getChakraValue();
		this.chakraRegenerationPerSecond = data.getChakraRegenerationPerSecond();
		this.chakraRegenerationTick = data.getChakraRegenerationTick();
	}
	
	public ChakraData setChakraValues(double maxValue, double value) {
		this.chakraMaxValue = maxValue;
		this.chakraValue = value;
		
		return this;
	}
	
	public ChakraData setChakraRegenerationPerSecond(double value) {
		this.chakraRegenerationPerSecond = value;
		
		return this;
	}
	
	public ChakraData setChakraRegenerationTick(int tick) {
		this.chakraRegenerationTick = tick;
		
		return this;
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
	
	public void useChakra(double amount) {
		if((this.chakraValue - amount) < 0) {
			this.chakraValue = 0.0;
		}
		else {
			this.chakraValue -= amount;
		}
	}
	
	public void synchronize(ChakraPacket packet) {
		ChakraData data = packet.getChakraData();
		
		this.chakraMaxValue = data.getChakraMaxValue();
		this.chakraValue = data.getChakraValue();
		this.chakraRegenerationPerSecond = data.getChakraRegenerationPerSecond();
		this.chakraRegenerationTick = data.getChakraRegenerationTick();
	}
	
	public static ChakraData fromPacketBuffer(PacketBuffer buffer) {
		return new ChakraData()
				.setChakraValues(buffer.readDouble(), buffer.readDouble())
				.setChakraRegenerationPerSecond(buffer.readDouble())
				.setChakraRegenerationTick(buffer.readInt());
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

	public double getChakraMaxValue() {
		return chakraMaxValue;
	}

	public double getChakraValue() {
		return chakraValue;
	}

	public double getChakraRegenerationPerSecond() {
		return chakraRegenerationPerSecond;
	}

	public int getChakraRegenerationTick() {
		return chakraRegenerationTick;
	}

	@Override
	public String toString() {
		return "ChakraData [chakraMaxValue=" + chakraMaxValue + ", chakraValue=" + chakraValue
				+ ", chakraRegenerationPerSecond=" + chakraRegenerationPerSecond + ", chakraRegenerationTick="
				+ chakraRegenerationTick + "]";
	}
}
