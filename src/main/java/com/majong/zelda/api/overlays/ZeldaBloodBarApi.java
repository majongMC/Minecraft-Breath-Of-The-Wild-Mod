package com.majong.zelda.api.overlays;

import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.overlays.RenderOverlays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ZeldaBloodBarApi {
	//�÷������Ƽ�ʹ�ã���ʵ���������zelda:has_healthbar��ǩ����ʵ����ͬ����
	//���������ӵ�����������Ѫ��
	//����һ�����һ�Σ�����һ��δ����Ѫ���Զ���ʧ
	//��һ������Ϊ��ǰ����ֵռ�������ֵ�ı�������Χ0-1
	//�ڶ�������Ϊ������ʾ��Ѫ���ϵ�����
	//�ͻ����ϵ��ô˷���
	@Deprecated
	public static void DisplayBloodBarClient(double percentage,ITextComponent name) {
			RenderOverlays.DisplayBloodBar(percentage, name,"");
	}
	@Deprecated
	public static void DisplayBloodBarClient(double percentage,ITextComponent name,String at) {
			RenderOverlays.DisplayBloodBar(percentage, name,at);
	}
	//�÷������Ƽ�ʹ�ã���ʵ���������zelda:has_healthbar��ǩ����ʵ��
	//������ϵ��ô˷���
	//����������Ϊ��ʾ��Ѫ�����������
	@Deprecated
	public static void DisplayBloodBarServer(double percentage,ITextComponent name,PlayerEntity player) {
		Networking.BAR.send(
	             PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new HealthBarPack(name,percentage));
	}
}
