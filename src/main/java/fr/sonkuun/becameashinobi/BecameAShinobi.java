package fr.sonkuun.becameashinobi;

import fr.sonkuun.becameashinobi.capability.CapabilityAttachEventHandler;
import fr.sonkuun.becameashinobi.capability.CapabilityBecameAShinobi;
import fr.sonkuun.becameashinobi.listener.BecameAShinobiListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod("becameashinobi")
public class BecameAShinobi
{
	public static final String MODID = "becameashinobi";

	public BecameAShinobi() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::enqueueIMC);
		modEventBus.addListener(this::processIMC);
		modEventBus.addListener(this::doClientStuff);
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new BecameAShinobiListener());
		
	}

	private void setup(final FMLCommonSetupEvent event) {
		CapabilityBecameAShinobi.register();
		
		MinecraftForge.EVENT_BUS.register(CapabilityAttachEventHandler.class);
	}


	private void doClientStuff(final FMLClientSetupEvent event) {
		
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		
	}

	private void processIMC(final InterModProcessEvent event) {
		
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		
	}
}