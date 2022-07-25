package com.majong.zelda.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientUtils {
	public static PlayerEntity GetClientPlayer() {
		return Minecraft.getInstance().player;
	}
	public static World GetClientLevel() {
		return Minecraft.getInstance().level;
	}
	public static void ClientStopSound() {
		Minecraft.getInstance().getSoundManager().stop();
	}
}
