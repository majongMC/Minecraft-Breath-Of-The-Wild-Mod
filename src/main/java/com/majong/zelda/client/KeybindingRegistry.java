package com.majong.zelda.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegistry {
	@SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        		ClientRegistry.registerKeyBinding(KeyBoardInput.SKILL_KEY);
        		ClientRegistry.registerKeyBinding(KeyBoardInput.DETONATE_KEY);
        	});
    }
}
