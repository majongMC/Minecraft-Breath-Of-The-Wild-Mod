package com.majong.zelda.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegistry {
	@SubscribeEvent
    public static void onClientSetup(RegisterKeyMappingsEvent event) {
        		event.register(KeyBoardInput.SKILL_KEY);
        		event.register(KeyBoardInput.DETONATE_KEY);
    }
}
