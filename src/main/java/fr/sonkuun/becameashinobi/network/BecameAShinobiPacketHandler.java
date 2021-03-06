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
		ChakraPacket message = new ChakraPacket();
		INSTANCE.registerMessage(ID++, ChakraPacket.class, message::encode, message::decode, message::handle);
		
	}
	
	
}
