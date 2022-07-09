package com.majong.zelda.api.overlays;

import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.overlays.RenderOverlays;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class ZeldaHealthBarApi {
	//���������ӵ�����������Ѫ��
	//����һ�����һ�Σ�����һ��δ����Ѫ���Զ���ʧ
	//��һ������Ϊ��ǰ����ֵռ�������ֵ�ı�������Χ0-1
	//�ڶ�������Ϊ������ʾ��Ѫ���ϵ�����
	//�ͻ����ϵ��ô˷���
	@SuppressWarnings("deprecation")
	public static void DisplayHealthBarClient(double percentage,Component name) {
			RenderOverlays.DisplayHealthBar(percentage, name);
	}
	//������ϵ��ô˷���
	//����������Ϊ��ʾ��Ѫ�����������
	@SuppressWarnings("deprecation")
	public static void DisplayHealthBarServer(double percentage,Component name,Player player) {
		Networking.BAR.send(
	             PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new HealthBarPack(name,percentage));
	}
}
