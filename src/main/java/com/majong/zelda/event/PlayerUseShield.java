package com.majong.zelda.event;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerUseShield {
	public static final Map<Player,Long> PLAYER_LAST_USE_SHIELD=new HashMap<>();
	@SubscribeEvent
	public static void onPlayerUseSheild(RightClickItem event) {
		if(!event.getWorld().isClientSide) {
		Item mainhand,offhand;
		mainhand=event.getPlayer().getMainHandItem().getItem();
		offhand=event.getPlayer().getOffhandItem().getItem();
		if(mainhand instanceof ShieldItem||offhand instanceof ShieldItem) {
			PLAYER_LAST_USE_SHIELD.put(event.getPlayer(),event.getWorld().getGameTime());
		}
	}
	}
}
