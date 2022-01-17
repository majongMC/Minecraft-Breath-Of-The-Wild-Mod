package com.majong.zelda.api.overlays;

import com.majong.zelda.network.BloodBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.overlays.RenderOverlays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ZeldaBloodBarApi {
	//让你的生物拥有塞尔达风格的血条
	//至少一秒调用一次，超过一秒未调用血条自动消失
	//第一个参数为当前生命值占最大生命值的比例，范围0-1
	//第二个参数为你想显示在血条上的名称
	//客户端上调用此方法
	@SuppressWarnings("deprecation")
	public static void DisplayBloodBarClient(double percentage,ITextComponent name) {
			RenderOverlays.DisplayBloodBar(percentage, name);
	}
	//服务端上调用此方法
	//第三个参数为显示该血条的玩家名称
	@SuppressWarnings("deprecation")
	public static void DisplayBloodBarServer(double percentage,ITextComponent name,PlayerEntity player) {
		Networking.BAR.send(
	             PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new BloodBarPack(name,percentage));
	}
}
