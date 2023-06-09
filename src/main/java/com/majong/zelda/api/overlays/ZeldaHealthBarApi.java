package com.majong.zelda.api.overlays;

import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;

import majongmc.hllib.client.overlays.HealthBarAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class ZeldaHealthBarApi {
	//该方法不推荐使用，给实体类型添加hllib:has_healthbar标签即可实现相同功能
	@Deprecated
	public static void DisplayHealthBarClient(float percentage,Component name,String at) {
		HealthBarAPI.DisplayHealthBar(percentage, name, Component.translatable(at));
	}
	@Deprecated
	public static void DisplayHealthBarClient(float percentage,Component name) {
		HealthBarAPI.DisplayHealthBar(percentage, name, Component.translatable(""));
	}
	//该方法不推荐使用，给实体类型添加hllib:has_healthbar标签即可实现相同功能
	@Deprecated
	public static void DisplayHealthBarServer(double percentage,Component name,Player player) {
		Networking.BAR.send(
	             PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new HealthBarPack(name,percentage));
	}
}
