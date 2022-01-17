package com.majong.zelda.network;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkEventHandler {
	@SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(Networking::registerMessage);
	}
}
