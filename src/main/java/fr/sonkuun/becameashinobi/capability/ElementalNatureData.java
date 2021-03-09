package fr.sonkuun.becameashinobi.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class ElementalNatureData {
	
	private int katonLevel;
	private int suitonLevel;
	private int futonLevel;
	private int raitonLevel;
	private int dotonLevel;
	
	public ElementalNatureData() {
		this.katonLevel = 0;
		this.suitonLevel = 0;
		this.futonLevel = 0;
		this.raitonLevel = 0;
		this.dotonLevel = 0;
	}
	
	public ElementalNatureData(ElementalNatureData data) {
		this.katonLevel = data.getKatonLevel();
		this.suitonLevel = data.getSuitonLevel();
		this.futonLevel = data.getFutonLevel();
		this.raitonLevel = data.getRaitonLevel();
		this.dotonLevel = data.getDotonLevel();
	}
	
	public ElementalNatureData setKatonLevel(int katonLevel) {
		this.katonLevel = katonLevel;
		
		return this;
	}

	public ElementalNatureData setSuitonLevel(int suitonLevel) {
		this.suitonLevel = suitonLevel;
		
		return this;
	}

	public ElementalNatureData setFutonLevel(int futonLevel) {
		this.futonLevel = futonLevel;
		
		return this;
	}

	public ElementalNatureData setRaitonLevel(int raitonLevel) {
		this.raitonLevel = raitonLevel;
		
		return this;
	}

	public ElementalNatureData setDotonLevel(int dotonLevel) {
		this.dotonLevel = dotonLevel;
		
		return this;
	}

	public void synchronize() {
	}
	
	public static ElementalNatureData fromPacketBuffer(PacketBuffer buffer) {
		return new ElementalNatureData();
	}

	public static final String KATON_LEVEL_NBT = "katon_level";
	public static final String SUITON_LEVEL_NBT = "suiton_level";
	public static final String FUTON_LEVEL_NBT = "futon_level";
	public static final String RAITON_LEVEL_NBT = "raiton_level";
	public static final String DOTON_LEVEL_NBT = "doton_level";
	
	public static class ElementalNatureNBTStorage implements Capability.IStorage<ElementalNatureData> {

		@Override
		public INBT writeNBT(Capability<ElementalNatureData> capability, ElementalNatureData instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();

			tag.putInt(KATON_LEVEL_NBT, instance.getKatonLevel());
			tag.putInt(SUITON_LEVEL_NBT, instance.getSuitonLevel());
			tag.putInt(FUTON_LEVEL_NBT, instance.getFutonLevel());
			tag.putInt(RAITON_LEVEL_NBT, instance.getRaitonLevel());
			tag.putInt(DOTON_LEVEL_NBT, instance.getDotonLevel());
			
			return tag;
		}

		@Override
		public void readNBT(Capability<ElementalNatureData> capability, ElementalNatureData instance, Direction side,
				INBT nbt) {
			if(nbt instanceof CompoundNBT) {
				CompoundNBT tag = (CompoundNBT) nbt;
				
				instance.setKatonLevel(tag.getInt(KATON_LEVEL_NBT));
				instance.setSuitonLevel(tag.getInt(SUITON_LEVEL_NBT));
				instance.setFutonLevel(tag.getInt(FUTON_LEVEL_NBT));
				instance.setRaitonLevel(tag.getInt(RAITON_LEVEL_NBT));
				instance.setDotonLevel(tag.getInt(DOTON_LEVEL_NBT));
			}
		}

	}

	public static ElementalNatureData createADefaultInstance() {
		return new ElementalNatureData();
	}

	public int getKatonLevel() {
		return katonLevel;
	}

	public int getSuitonLevel() {
		return suitonLevel;
	}

	public int getFutonLevel() {
		return futonLevel;
	}

	public int getRaitonLevel() {
		return raitonLevel;
	}

	public int getDotonLevel() {
		return dotonLevel;
	}

	@Override
	public String toString() {
		return "ElementalNatureData [katonLevel=" + katonLevel + ", suitonLevel=" + suitonLevel + ", futonLevel="
				+ futonLevel + ", raitonLevel=" + raitonLevel + ", dotonLevel=" + dotonLevel + "]";
	}
}
