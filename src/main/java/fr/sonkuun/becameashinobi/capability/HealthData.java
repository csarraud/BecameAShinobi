package fr.sonkuun.becameashinobi.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class HealthData {
	
	private int maxHealth;
	
	public HealthData() {
		this.maxHealth = 20;
	}
	
	public HealthData(HealthData data) {
		this.maxHealth = data.getMaxHealth();
	}
	
	public HealthData setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		
		return this;
	}
	
	public static final String MAX_HEALTH_NBT = "max_health";
	
	public static class HealthDataNBTStorage implements Capability.IStorage<HealthData> {

		@Override
		public INBT writeNBT(Capability<HealthData> capability, HealthData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putDouble(MAX_HEALTH_NBT, instance.maxHealth);

			return tag;
		}

		@Override
		public void readNBT(Capability<HealthData> capability, HealthData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

				instance.setMaxHealth(tag.getInt(MAX_HEALTH_NBT));
			}
		}

	}
	
	public static HealthData createADefaultInstance() {
		return new HealthData();
	}

	public int getMaxHealth() {
		return maxHealth;
	}
}
