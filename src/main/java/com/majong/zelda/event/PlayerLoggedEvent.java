package com.majong.zelda.event;

import com.majong.zelda.data.DataManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerLoggedEvent {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		PlayerTick.THUNDER_COUNT_TIME.put(event.getPlayer(), 100);
		DataManager.preventnull(event.getPlayer());
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.put(event.getPlayer(),0L);
	}
	@SubscribeEvent
	public static void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
		PlayerEntity player=event.getPlayer();
		DataManager.removedata(player);
		PlayerTick.THUNDER_COUNT_TIME.remove(player);
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.remove(player);
	}		
}
