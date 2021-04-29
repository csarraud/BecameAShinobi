package fr.sonkuun.becameashinobi.capability;

import fr.sonkuun.becameashinobi.network.HealthPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class HealthData {
	
	private int maxHealth;
	private double health;
	
	public HealthData() {
		this.maxHealth = 20;
		this.health = 20.0;
	}
	
	public HealthData(HealthData data) {
		this.maxHealth = data.getMaxHealth();
		this.health = data.getExactHealth();
	}
	
	public HealthData setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		
		return this;
	}
	
	public HealthData setHealth(double health) {
		this.health = health;
		
		return this;
	}
	
	public void removeHealth(int amount) {
		if(this.health <= amount) {
			this.health = 0.0;
		}
		else {
			this.health -= amount;
		}
	}
	
	public void synchronize(HealthPacket packet) {
		HealthData data = packet.getHealthData();
		
		this.maxHealth = data.getMaxHealth();
		this.health = data.getExactHealth();
	}
	
	public static HealthData fromPacketBuffer(PacketBuffer buffer) {
		return new HealthData()
				.setMaxHealth(buffer.readInt())
				.setHealth(buffer.readDouble());
	}
	
	public static final String MAX_HEALTH_NBT = "max_health";
	public static final String HEALTH_NBT = "health";
	
	public static class HealthDataNBTStorage implements Capability.IStorage<HealthData> {

		@Override
		public INBT writeNBT(Capability<HealthData> capability, HealthData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putInt(MAX_HEALTH_NBT, instance.maxHealth);
			tag.putDouble(HEALTH_NBT, instance.health);

			return tag;
		}

		@Override
		public void readNBT(Capability<HealthData> capability, HealthData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

				instance.setMaxHealth(tag.getInt(MAX_HEALTH_NBT))
						.setHealth(tag.getDouble(HEALTH_NBT));
			}
		}

	}
	
	public static HealthData createADefaultInstance() {
		return new HealthData();
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public double getExactHealth() {
		return health;
	}
	
	public int getHealth() {
		return (int) Math.round(health);
	}
}
