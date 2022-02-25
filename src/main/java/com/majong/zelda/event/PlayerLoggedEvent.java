package com.majong.zelda.event;

import java.util.Date;
import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.Festival;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerLoggedEvent {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		PlayerEntity player=event.getPlayer();
		PlayerTick.THUNDER_COUNT_TIME.put(player, 100);
		DataManager.preventnull(player);
		DataManager.sendzeldaplayerdatapack(player);
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.put(player,0L);
		if(Festival.isLunarSpringFestival(new Date())) {
			player.sendMessage(new TranslationTextComponent("msg.zelda.lunaryear"), UUID.randomUUID());
			if(ZeldaConfig.REDENVELOPE.get())
				player.addItem(new ItemStack(ItemLoader.RED_ENVELOPE.get(),1));
		}
	}
	@SubscribeEvent
	public static void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
		PlayerEntity player=event.getPlayer();
		PlayerTick.THUNDER_COUNT_TIME.remove(player);
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.remove(player);
	}		
}
