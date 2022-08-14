package com.majong.zelda.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber()
public class LinkTime {
	public static final Map<Player,Integer> LINK_TIME=new HashMap<>();
	@SubscribeEvent
	public static void onPlayerNockArrow(ArrowNockEvent event) {
		if(!event.getLevel().isClientSide) {
			Player player=event.getEntity();
			if(!player.isOnGround()&&!player.isFallFlying()) {
				player.setDeltaMovement(Vec3.ZERO);
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,120,8));
				player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,120,3));
				player.setNoGravity(true);
				slownearmonsters(player,false);
				LINK_TIME.put(player,ZeldaConfig.LINKTIME.get());
			}
			else {
				player.removeEffect(MobEffects.SLOW_FALLING);
				player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				player.setNoGravity(false);
				LINK_TIME.put(player,-1);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLooseArrow(ArrowLooseEvent event) {
		if(!event.getLevel().isClientSide) {
			Player player=event.getEntity();
			player.removeEffect(MobEffects.SLOW_FALLING);
			player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			player.setNoGravity(false);
			slownearmonsters(player,true);
			LINK_TIME.put(player,-1);
		}
	}
	private static void slownearmonsters(Player player,boolean remove) {
		Level Level=player.level;
		List<LivingEntity> entitylist= Level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(24, 24, 24) ,(LivingEntity t)->{
				return t instanceof LivingEntity&&!(t instanceof Player);
			});
		Iterator<LivingEntity> it=entitylist.iterator();
		while(it.hasNext()) {
			LivingEntity entity=it.next();
			if(remove) {
				entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}else {
				entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100,9));
			}
		}
	}
}
