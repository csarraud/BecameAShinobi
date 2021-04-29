package fr.sonkuun.becameashinobi.capability;

import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.HealthPacket;
import fr.sonkuun.becameashinobi.util.MathUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.PacketDistributor;

public class HealthData {

	private static int NB_TICKS_PER_SECOND = 20;
	
	private int maxHealth;
	private double health;
	private double healthRegenerationPerSecond;
	private int healthRegenerationTick;
	
	public HealthData() {
		this.maxHealth = 20;
		this.health = 20.0;
		this.healthRegenerationPerSecond = 0.5;
		this.healthRegenerationTick = 0;
	}
	
	public HealthData(HealthData data) {
		this.maxHealth = data.getMaxHealth();
		this.health = data.getExactHealth();
		this.healthRegenerationPerSecond = data.getHealthRegenerationPerSecond();
		this.healthRegenerationTick = data.getHealthRegenerationTick();
	}
	
	public HealthData setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		
		return this;
	}
	
	public HealthData setHealth(double health) {
		this.health = health;
		
		return this;
	}
	
	public HealthData setHealthRegenerationPerSecond(double value) {
		this.healthRegenerationPerSecond = value;
		
		return this;
	}
	
	public HealthData setHealthRegenerationTick(int tick) {
		this.healthRegenerationTick = tick;
		
		return this;
	}
	
	public void updateHealth(PlayerEntity player) {
		regenerateHealth();
		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new HealthPacket(player.getUniqueID(), this));
	}
	
	public void regenerateHealth() {
		if(this.healthRegenerationTick >= NB_TICKS_PER_SECOND) {
			this.addHealth(MathUtil.round(this.healthRegenerationPerSecond, 1));
			this.healthRegenerationTick = 0;
		}
		else {
			this.healthRegenerationTick++;
		}
	}
	
	public void addHealth(double amount) {
		if((this.health + amount) >= (double) this.maxHealth) {
			this.health = (double) this.maxHealth;
		}
		else {
			this.health += amount;
		}
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
		this.healthRegenerationPerSecond = data.getHealthRegenerationPerSecond();
		this.healthRegenerationTick = data.getHealthRegenerationTick();
	}
	
	public static HealthData fromPacketBuffer(PacketBuffer buffer) {
		return new HealthData()
				.setMaxHealth(buffer.readInt())
				.setHealth(buffer.readDouble())
				.setHealthRegenerationPerSecond(buffer.readDouble())
				.setHealthRegenerationTick(buffer.readInt());
	}
	
	public static final String MAX_HEALTH_NBT = "max_health";
	public static final String HEALTH_NBT = "health";
	public static final String HEALTH_REGENERATION_PER_SECOND_NBT = "health_regeneration_per_second";
	public static final String HEALTH_REGENERATION_TICK_NBT = "health_regeneration_tick";
	
	public static class HealthDataNBTStorage implements Capability.IStorage<HealthData> {

		@Override
		public INBT writeNBT(Capability<HealthData> capability, HealthData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putInt(MAX_HEALTH_NBT, instance.maxHealth);
			tag.putDouble(HEALTH_NBT, instance.health);
			tag.putDouble(HEALTH_REGENERATION_PER_SECOND_NBT, instance.healthRegenerationPerSecond);
			tag.putInt(HEALTH_REGENERATION_TICK_NBT, instance.healthRegenerationTick);

			return tag;
		}

		@Override
		public void readNBT(Capability<HealthData> capability, HealthData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

				instance.setMaxHealth(tag.getInt(MAX_HEALTH_NBT));
				instance.setHealth(tag.getDouble(HEALTH_NBT));
				instance.setHealthRegenerationPerSecond(tag.getDouble(HEALTH_REGENERATION_PER_SECOND_NBT));
				instance.setHealthRegenerationTick(tag.getInt(HEALTH_REGENERATION_TICK_NBT));
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
		return (int) health;
	}
	
	public double getHealthRegenerationPerSecond() {
		return healthRegenerationPerSecond;
	}
	
	public int getHealthRegenerationTick() {
		return healthRegenerationTick;
	}
}
