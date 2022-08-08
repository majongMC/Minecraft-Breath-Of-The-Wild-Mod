package com.majong.zelda.event;

import com.majong.zelda.api.effects.CameraShakeApi;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class CameraShake {
	@SubscribeEvent
    public static void onSetupCamera(ViewportEvent.ComputeCameraAngles event) {
		if(CameraShakeApi.shakeframe>0) {
			Player player=Minecraft.getInstance().player;
			float yaw=player.yHeadRot;
			float pitch=player.xRotO;
			event.setPitch(pitch+Mth.sin(CameraShakeApi.shakeframe*2));
			event.setYaw(yaw+0.5F*Mth.cos(CameraShakeApi.shakeframe));
			CameraShakeApi.shakeframe--;
		}
	}
}
