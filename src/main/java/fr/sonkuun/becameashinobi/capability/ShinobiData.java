package fr.sonkuun.becameashinobi.capability;

import fr.sonkuun.becameashinobi.capability.component.ChakraNature;
import fr.sonkuun.becameashinobi.network.BecameAShinobiPacketHandler;
import fr.sonkuun.becameashinobi.network.ShinobiPacket;
import fr.sonkuun.becameashinobi.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.PacketDistributor;

public class ShinobiData {
	
	/*
	 * Miscellanous
	 */
	private static int NB_TICKS_PER_SECOND = 20;
	
	/*
	 * Health
	 */
	private int maxHealth;
	private double health;
	private double baseHealthRegenerationPerSecond;
	private int healthRegenerationTick;
	
	/*
	 * Chakra
	 */
	private double chakraMaxValue;
	private double chakraValue;
	private double baseChakraRegenerationPerSecond;
	private double chakraRegenerationPerSecond;
	private int chakraRegenerationFactor;
	private int chakraRegenerationTick;
	
	/*
	 * Chakra nature
	 */
	private ChakraNature chakraNature;
	
	public ShinobiData() {
		this.maxHealth = 20;
		this.health = 20.0;
		this.baseHealthRegenerationPerSecond = MathUtil.round(1 * this.maxHealth / 100.0, 1);
		this.healthRegenerationTick = 0;

		this.chakraMaxValue = 100;
		this.chakraValue = 0;
		this.baseChakraRegenerationPerSecond = 1.0;
		this.chakraRegenerationPerSecond = 1.0;
		this.chakraRegenerationFactor = 100;
		this.chakraRegenerationTick = 0;
	}
	
	public ShinobiData(ShinobiData data) {
		this.maxHealth = data.getMaxHealth();
		this.health = data.getExactHealth();
		this.baseHealthRegenerationPerSecond = data.getBaseHealthRegenerationPerSecond();
		this.healthRegenerationTick = data.getHealthRegenerationTick();
		
		this.chakraMaxValue = data.getChakraMaxValue();
		this.chakraValue = data.getChakraValue();
		this.baseChakraRegenerationPerSecond = data.getBaseChakraRegenerationPerSecond();
		this.chakraRegenerationPerSecond = data.getChakraRegenerationPerSecond();
		this.chakraRegenerationFactor = data.getChakraRegenerationFactor();
		this.chakraRegenerationTick = data.getChakraRegenerationTick();
	}
	
	/*
	 * ########## Health ##########
	 */
	
	public void updateHealth(PlayerEntity player) {
		regenerateHealth();
		sendDataToClient(player);
	}
	
	public void regenerateHealth() {
		if(this.healthRegenerationTick >= NB_TICKS_PER_SECOND) {
			this.addHealth(this.getTotalRegenerationHealth());
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
	
	public void removeHealth(double amount) {
		if(this.health <= amount) {
			this.health = 0.0;
		}
		else {
			this.health -= amount;
		}
	}
	
	/*
	 * ########## Chakra ##########
	 */
	
	public void updateChakra(PlayerEntity player) {
		if(player.isSneaking()) {
			this.chakraRegenerationPerSecond = this.baseChakraRegenerationPerSecond * this.chakraRegenerationFactor / 100 + this.baseChakraRegenerationPerSecond;
		}
		else {
			this.chakraRegenerationPerSecond = this.baseChakraRegenerationPerSecond;
		}
		
		regenerateChakra();
		sendDataToAllClient(player);
	}
	
	public void regenerateChakra() {
		if(this.chakraRegenerationTick >= NB_TICKS_PER_SECOND) {
			this.addChakra(MathUtil.round(this.chakraRegenerationPerSecond, 1));
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
	
	public void synchronize(ShinobiPacket packet) {
		ShinobiData data = packet.getShinobiData();
		
		this.maxHealth = data.getMaxHealth();
		this.health = data.getExactHealth();
		this.baseHealthRegenerationPerSecond = data.getBaseHealthRegenerationPerSecond();
		this.healthRegenerationTick = data.getHealthRegenerationTick();
		
		this.chakraMaxValue = data.getChakraMaxValue();
		this.chakraValue = data.getChakraValue();
		this.baseChakraRegenerationPerSecond = data.getBaseChakraRegenerationPerSecond();
		this.chakraRegenerationPerSecond = data.getChakraRegenerationPerSecond();
		this.chakraRegenerationFactor = data.getChakraRegenerationFactor();
		this.chakraRegenerationTick = data.getChakraRegenerationTick();
	}
	
	public static ShinobiData fromPacketBuffer(PacketBuffer buffer) {
		return new ShinobiData()
				.setMaxHealth(buffer.readInt())
				.setHealth(buffer.readDouble())
				.setBaseHealthRegenerationPerSecond(buffer.readDouble())
				.setHealthRegenerationTick(buffer.readInt())
				.setChakraValues(buffer.readDouble(), buffer.readDouble())
				.setBaseChakraRegenerationPerSecond(buffer.readDouble())
				.setChakraRegenerationPerSecond(buffer.readDouble())
				.setChakraRegenerationFactor(buffer.readInt())
				.setChakraRegenerationTick(buffer.readInt());
	}
	
	public void sendDataToClient(PlayerEntity player) {
		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ShinobiPacket(player.getUniqueID(), this));
	}
	
	public void sendDataToAllClient(Entity entity) {
		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ShinobiPacket(entity.getUniqueID(), this));
	}
	
	public void sendDataToServer(Entity entity) {
		BecameAShinobiPacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new ShinobiPacket(entity.getUniqueID(), this));
	}
	
	public static final String MAX_HEALTH_NBT = "max_health";
	public static final String HEALTH_NBT = "health";
	public static final String HEALTH_REGENERATION_PER_SECOND_NBT = "health_regeneration_per_second";
	public static final String HEALTH_REGENERATION_TICK_NBT = "health_regeneration_tick";

	public static final String CHAKRA_MAX_VALUE_NBT = "chakra_max_value";
	public static final String CHAKRA_VALUE_NBT = "chakra_value";
	public static final String BASE_CHAKRA_REGENERATION_PER_SECOND_NBT = "base_chakra_regeneration_per_second";
	public static final String CHAKRA_REGENERATION_PER_SECOND_NBT = "chakra_regeneration_per_second";
	public static final String CHAKRA_REGENERATION_FACTOR_NBT = "chakra_regeneration_factor";
	public static final String CHAKRA_REGENERATION_TICK_NBT = "chakra_regeneration_tick";

	public static class ShinobiDataNBTStorage implements Capability.IStorage<ShinobiData> {

		@Override
		public INBT writeNBT(Capability<ShinobiData> capability, ShinobiData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putInt(MAX_HEALTH_NBT, instance.maxHealth);
			tag.putDouble(HEALTH_NBT, instance.health);
			tag.putDouble(HEALTH_REGENERATION_PER_SECOND_NBT, instance.baseHealthRegenerationPerSecond);
			tag.putInt(HEALTH_REGENERATION_TICK_NBT, instance.healthRegenerationTick);

			tag.putDouble(CHAKRA_MAX_VALUE_NBT, instance.chakraMaxValue);
			tag.putDouble(CHAKRA_VALUE_NBT, instance.chakraValue);
			tag.putDouble(BASE_CHAKRA_REGENERATION_PER_SECOND_NBT, instance.baseChakraRegenerationPerSecond);
			tag.putDouble(CHAKRA_REGENERATION_PER_SECOND_NBT, instance.chakraRegenerationPerSecond);
			tag.putInt(CHAKRA_REGENERATION_FACTOR_NBT, instance.getChakraRegenerationFactor());
			tag.putInt(CHAKRA_REGENERATION_TICK_NBT, instance.chakraRegenerationTick);

			return tag;
		}

		@Override
		public void readNBT(Capability<ShinobiData> capability, ShinobiData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;

				instance.setMaxHealth(tag.getInt(MAX_HEALTH_NBT));
				instance.setHealth(tag.getDouble(HEALTH_NBT));
				instance.setBaseHealthRegenerationPerSecond(tag.getDouble(HEALTH_REGENERATION_PER_SECOND_NBT));
				instance.setHealthRegenerationTick(tag.getInt(HEALTH_REGENERATION_TICK_NBT));
				
				instance.setChakraValues(
						tag.getDouble(CHAKRA_MAX_VALUE_NBT),
						tag.getDouble(CHAKRA_VALUE_NBT));
				instance.setBaseChakraRegenerationPerSecond(tag.getDouble(BASE_CHAKRA_REGENERATION_PER_SECOND_NBT));
				instance.setChakraRegenerationPerSecond(tag.getDouble(CHAKRA_REGENERATION_PER_SECOND_NBT));
				instance.setChakraRegenerationFactor(tag.getInt(CHAKRA_REGENERATION_FACTOR_NBT));
				instance.setChakraRegenerationTick(tag.getInt(CHAKRA_REGENERATION_TICK_NBT));
			}
		}
	}
	
	public static ShinobiData createADefaultInstance() {
		return new ShinobiData();
	}
	
	/*
	 * ########## Health ##########
	 */
	
	public ShinobiData setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		
		return this;
	}
	
	public ShinobiData setHealth(double health) {
		this.health = health;
		
		return this;
	}
	
	public ShinobiData setBaseHealthRegenerationPerSecond(double value) {
		this.baseHealthRegenerationPerSecond = value;
		
		return this;
	}
	
	public ShinobiData setHealthRegenerationTick(int tick) {
		this.healthRegenerationTick = tick;
		
		return this;
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
	
	public double getBaseHealthRegenerationPerSecond() {
		return baseHealthRegenerationPerSecond;
	}
	
	public double getTotalRegenerationHealth() {
		double value = this.baseHealthRegenerationPerSecond;
		
		/*
		 * TODO : implement additional health regeneration
		 */
		
		return MathUtil.round(value, 1);
	}
	
	public int getHealthRegenerationTick() {
		return healthRegenerationTick;
	}
	
	/*
	 * ########## Chakra ##########
	 */
	
	public ShinobiData setChakraValues(double maxValue, double value) {
		this.chakraMaxValue = maxValue;
		this.chakraValue = value;
		
		return this;
	}
	
	public ShinobiData setBaseChakraRegenerationPerSecond(double value) {
		this.baseChakraRegenerationPerSecond = value;
		
		return this;
	}
	
	public ShinobiData setChakraRegenerationPerSecond(double value) {
		this.chakraRegenerationPerSecond = value;
		
		return this;
	}
	
	public ShinobiData setChakraRegenerationFactor(int factor) {
		this.chakraRegenerationFactor = factor;
		
		return this;
	}
	
	public ShinobiData setChakraRegenerationTick(int tick) {
		this.chakraRegenerationTick = tick;
		
		return this;
	}

	public double getChakraMaxValue() {
		return chakraMaxValue;
	}

	public double getChakraValue() {
		return chakraValue;
	}

	public double getBaseChakraRegenerationPerSecond() {
		return baseChakraRegenerationPerSecond;
	}

	public double getChakraRegenerationPerSecond() {
		return chakraRegenerationPerSecond;
	}

	public int getChakraRegenerationFactor() {
		return chakraRegenerationFactor;
	}

	public int getChakraRegenerationTick() {
		return chakraRegenerationTick;
	}
	
}
