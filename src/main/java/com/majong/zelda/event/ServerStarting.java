package com.majong.zelda.event;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ServerStarting {
	@SubscribeEvent
	public static void onServerAboutToStartStarting(ServerAboutToStartEvent event) {
		ServerTick.server=event.getServer();
	}
}
