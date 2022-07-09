package com.majong.zelda.api.util;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.world.entity.LivingEntity;

public class AttributeDamageApi {
	public static final Collection<Class<? extends LivingEntity>> FIRE_RESTRAINTED=new ArrayList<>();//�������Կ���
	public static final Collection<Class<? extends LivingEntity>> ICE_RESTRAINTED=new ArrayList<>();//�������Կ���
	public static final Collection<Class<? extends LivingEntity>> ANCIENT_RESTRAINTED=new ArrayList<>();//���Ŵ��˺�����
	//��FMLServerStartingEvent�¼���ʹ�����·���ע�����Կ���(ע���Ŀ�������ܵ���Ӧ�����˺�ʱ����ɱ)
	//��һ������Ϊ�����Ƶ����ԣ����Ϸ��ĳ�����ѡ��һ������
	//�ڶ�������Ϊ������������������
	//ʹ��ʾ����com.majong.zelda.event.ServerStarting
	public static void registerrestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	restrainted_list.add(entityclass);
    }
	//��FMLServerStartingEvent�¼���ʹ�����·����Ƴ����Կ���(��ʹ����͵����ȼ�)
	public static void removerestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	if(restrainted_list.contains(entityclass))
    		restrainted_list.remove(entityclass);
    }
}
