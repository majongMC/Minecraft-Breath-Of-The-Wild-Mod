package com.majong.zelda.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientUtils {
	public static Player GetClientPlayer() {
		return Minecraft.getInstance().player;
	}
	public static Level GetClientLevel() {
		return Minecraft.getInstance().level;
	}
	public static void ClientStopSound() {
		Minecraft.getInstance().getSoundManager().stop();
	}
	/*8public static Entity GetClientEntityByUUID(UUID uuid) {
		ClientLevel level=Minecraft.getInstance().level;
		for(int i = 0; i < level.getEntityCount(); ++i) {
	         Entity entity = level.getEntity(i);
	         if (uuid.equals(entity.getUUID())) {
	            return entity;
	         }
	      }
		return null;
	}*/
}
