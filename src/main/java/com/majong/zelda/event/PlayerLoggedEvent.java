package com.majong.zelda.event;

import java.util.Date;
import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ZeldaNBTPack;
import com.majong.zelda.util.Festival;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class PlayerLoggedEvent {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		Player player=event.getPlayer();
		CompoundTag entityData = player.getPersistentData();
		DataManager.preventnull(player);
		if(entityData.contains("zpd")) {
			DataManager.writefromnbt(entityData.getCompound("zpd"), player);
		}
		ZeldaPlayerData playerdata=DataManager.data.get(player);
		for(int i=0;i<4;i++) {
			switch(i) {
			case 0:if(playerdata.cd[i]>ZeldaConfig.WATER.get())playerdata.cd[i]=ZeldaConfig.WATER.get();break;
			case 1:if(playerdata.cd[i]>ZeldaConfig.WIND.get())playerdata.cd[i]=ZeldaConfig.WIND.get();break;
			case 2:if(playerdata.cd[i]>ZeldaConfig.FIRE.get())playerdata.cd[i]=ZeldaConfig.FIRE.get();break;
			case 3:if(playerdata.cd[i]>ZeldaConfig.THUNDER.get())playerdata.cd[i]=ZeldaConfig.THUNDER.get();break;
			}
		}
		EntityTick.THUNDER_COUNT_TIME.put(player, 100);
		DataManager.preventnull(player);
		DataManager.sendzeldaplayerdatapack(player);
		CompoundTag cdpack=new CompoundTag();
		int[] cd= {ZeldaConfig.WATER.get(),ZeldaConfig.WIND.get(),ZeldaConfig.FIRE.get(),ZeldaConfig.THUNDER.get()};
		cdpack.putIntArray("cd",cd);
		Networking.ZELDANBT.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayer) player
                ),
                new ZeldaNBTPack(2,cdpack));
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.put(player,0L);
		if(Festival.isLunarSpringFestival(new Date())) {
			player.sendMessage(new TranslatableComponent("msg.zelda.lunaryear"),UUID.randomUUID());
			if(ZeldaConfig.REDENVELOPE.get())
				player.addItem(new ItemStack(ItemLoader.RED_ENVELOPE.get(),1));
		}
	}
	@SubscribeEvent
	public static void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
		Player player=event.getPlayer();
		EntityTick.THUNDER_COUNT_TIME.remove(player);
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.remove(player);
		CompoundTag entityData = player.getPersistentData();
		CompoundTag zeldaplayerdata=DataManager.readtonbt(player);
		entityData.put("zpd", zeldaplayerdata);
	}		
}
