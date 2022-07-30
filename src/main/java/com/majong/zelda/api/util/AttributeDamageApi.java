package com.majong.zelda.api.util;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.entity.LivingEntity;

public class AttributeDamageApi {
	public static final Collection<Class<? extends LivingEntity>> FIRE_RESTRAINTED=new ArrayList<>();//被火属性克制
	public static final Collection<Class<? extends LivingEntity>> ICE_RESTRAINTED=new ArrayList<>();//被冰属性克制
	public static final Collection<Class<? extends LivingEntity>> ANCIENT_RESTRAINTED=new ArrayList<>();//被古代伤害克制
	//不推荐使用，给实体类型添加相应标签即可实现相同功能
	//在FMLServerStartingEvent事件中使用以下方法注册属性克制(注册后当目标生物受到对应属性伤害时被秒杀)
	//第一个参数为被克制的属性，从上方的常量中选择一个填入
	//第二个参数为被克制生物所属的类
	//使用示例见com.majong.zelda.event.ServerStarting
	@Deprecated
	public static void registerrestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	restrainted_list.add(entityclass);
    }
	//在FMLServerStartingEvent事件中使用以下方法移除属性克制(请使用最低的优先级)
	@Deprecated
	public static void removerestraint(Collection<Class<? extends LivingEntity>> restrainted_list,Class<? extends LivingEntity> entityclass) {
    	if(restrainted_list.contains(entityclass))
    		restrainted_list.remove(entityclass);
    }
}
