package com.majong.zelda.util;

import com.majong.zelda.Utils;

import majongmc.hllib.mixin.common.DamageSourcesInvoker;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

public class CustomDamageSources {
	private static final ResourceKey<DamageType> CHICKEN = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Utils.MOD_ID,"chicken"));
	public static DamageSource chicken(DamageSources sources,LivingEntity entity) {
		return ((DamageSourcesInvoker)sources).source(CHICKEN, entity, entity);
	}
}
