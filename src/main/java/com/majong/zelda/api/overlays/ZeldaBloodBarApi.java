package com.majong.zelda.api.overlays;

import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.overlays.RenderOverlays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ZeldaBloodBarApi {
	//该方法不推荐使用，给实体类型添加zelda:has_healthbar标签即可实现相同功能
	//让你的生物拥有塞尔达风格的血条
	//至少一秒调用一次，超过一秒未调用血条自动消失
	//第一个参数为当前生命值占最大生命值的比例，范围0-1
	//第二个参数为你想显示在血条上的名称
	//客户端上调用此方法
	@Deprecated
	public static void DisplayBloodBarClient(double percentage,ITextComponent name) {
			RenderOverlays.DisplayBloodBar(percentage, name,"");
	}
	@Deprecated
	public static void DisplayBloodBarClient(double percentage,ITextComponent name,String at) {
			RenderOverlays.DisplayBloodBar(percentage, name,at);
	}
	//该方法不推荐使用，给实体类型添加zelda:has_healthbar标签即可实现
	//服务端上调用此方法
	//第三个参数为显示该血条的玩家名称
	@Deprecated
	public static void DisplayBloodBarServer(double percentage,ITextComponent name,PlayerEntity player) {
		Networking.BAR.send(
	             PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new HealthBarPack(name,percentage));
	}
}
