package fr.sonkuun.becameashinobi.network;


import fr.sonkuun.becameashinobi.BecameAShinobi;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BecameAShinobiPacketHandler {
	
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation(BecameAShinobi.MODID, "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	private static int ID = 1;
	
	public static void register() {
		ShinobiPacket shinobiPacket = new ShinobiPacket();
		PlayerChooseElementalNatureGuiPacket playerChooseElementalNatureGuiPacket = new PlayerChooseElementalNatureGuiPacket();
		ElementalNaturePacket elementalNaturePacket = new ElementalNaturePacket();
		
		INSTANCE.registerMessage(ID++, ShinobiPacket.class, shinobiPacket::encode, shinobiPacket::decode, shinobiPacket::handle);
		INSTANCE.registerMessage(ID++, PlayerChooseElementalNatureGuiPacket.class, playerChooseElementalNatureGuiPacket::encode, playerChooseElementalNatureGuiPacket::decode, playerChooseElementalNatureGuiPacket::handle);
		INSTANCE.registerMessage(ID++, ElementalNaturePacket.class, elementalNaturePacket::encode, elementalNaturePacket::decode, elementalNaturePacket::handle);
	}
}
