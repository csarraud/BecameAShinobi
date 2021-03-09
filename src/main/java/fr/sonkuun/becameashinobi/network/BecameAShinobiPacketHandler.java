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
		ChakraPacket chakraPacket = new ChakraPacket();
		PlayerChooseElementalNatureGuiPacket playerChooseElementalNatureGuiPacket = new PlayerChooseElementalNatureGuiPacket();
		
		INSTANCE.registerMessage(ID++, ChakraPacket.class, chakraPacket::encode, chakraPacket::decode, chakraPacket::handle);
		INSTANCE.registerMessage(ID++, PlayerChooseElementalNatureGuiPacket.class, playerChooseElementalNatureGuiPacket::encode, playerChooseElementalNatureGuiPacket::decode, playerChooseElementalNatureGuiPacket::handle);
	}
	
	
}
