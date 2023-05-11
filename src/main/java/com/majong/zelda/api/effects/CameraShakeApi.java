package com.majong.zelda.api.effects;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ZeldaNBTPack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class CameraShakeApi {
	public static int shakeframe=0;
	public static void CameraShakeServer(Player player ,int shakeframe) {
		CompoundTag pack=new CompoundTag();
		pack.putInt("frame", shakeframe);
		Networking.ZELDANBT.send((ServerPlayer) player,new ZeldaNBTPack(3,pack));
	}
}
