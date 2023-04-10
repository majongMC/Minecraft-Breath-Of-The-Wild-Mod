package com.majong.zelda.advancement;

import com.majong.zelda.Utils;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class TriggerRegistery {
	public static PlayerTrigger HEART_FULL,WIND_BOMB,SONIC_BOOM,SONIC_BOOM_WARDEN,CHICKEN_GOD,HORN,SHIELD_REFLECT,REFLECT_LASER,KILL_GUARDIAN,KILL_WARDEN,ATTRIBUTE_RESTRAINTED,COLLIDE_MOBS;
	public static void register() {
		HEART_FULL=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"heart_full")));
		WIND_BOMB=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"wind_bomb")));
		SONIC_BOOM=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"sonic_boom")));
		SONIC_BOOM_WARDEN=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"sonic_boom_warden")));
		CHICKEN_GOD=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"chicken_god")));
		HORN=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"horn")));
		SHIELD_REFLECT=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"shield_reflect")));
		REFLECT_LASER=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"reflect_laser")));
		KILL_GUARDIAN=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"kill_guardian")));
		KILL_WARDEN=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"kill_warden")));
		ATTRIBUTE_RESTRAINTED=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"attribute_restraint")));
		COLLIDE_MOBS=CriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(Utils.MOD_ID,"collide_mobs")));
	}
}
