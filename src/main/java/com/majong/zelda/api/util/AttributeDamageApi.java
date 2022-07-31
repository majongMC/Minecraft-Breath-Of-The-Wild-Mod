package com.majong.zelda.api.util;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.world.entity.LivingEntity;

public class AttributeDamageApi {
	public static final Collection<Class<? extends LivingEntity>> FIRE_RESTRAINTED=new ArrayList<>();//�������Կ���
	public static final Collection<Class<? extends LivingEntity>> ICE_RESTRAINTED=new ArrayList<>();//�������Կ���
	public static final Collection<Class<? extends LivingEntity>> ANCIENT_RESTRAINTED=new ArrayList<>();//���Ŵ��˺�����
	//不推荐使用，给实体类型添加相应标签即可实现相同功能
	@Deprecated
	public static void registerrestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	restrainted_list.add(entityclass);
    }
	@Deprecated
	public static void removerestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	if(restrainted_list.contains(entityclass))
    		restrainted_list.remove(entityclass);
    }
}
