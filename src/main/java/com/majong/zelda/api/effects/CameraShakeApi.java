package com.majong.zelda.api.effects;

import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ZeldaNBTPack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class CameraShakeApi {
	public static int shakeframe=0;
	public static void CameraShakeClient(int shakeframe) {
		if(ZeldaConfigClient.CAMERA_SHAKE.get())
			CameraShakeApi.shakeframe=shakeframe;
	}
	public static void CameraShakeServer(Player player ,int shakeframe) {
		CompoundTag pack=new CompoundTag();
		pack.putInt("frame", shakeframe);
		Networking.ZELDANBT.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayer) player
                ),
                new ZeldaNBTPack(3,pack));
	}
}
