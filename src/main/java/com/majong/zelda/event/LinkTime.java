package com.majong.zelda.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.util.EntityFreezer;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber()
public class LinkTime {
	public static final Map<PlayerEntity,Integer> LINK_TIME=new HashMap<>();
	@SubscribeEvent
	public static void onPlayerNockArrow(ArrowNockEvent event) {
		if(!event.getWorld().isClientSide) {
			PlayerEntity player=event.getPlayer();
			if(!player.isOnGround()&&!player.isFallFlying()) {
				player.setDeltaMovement(Vector3d.ZERO);
				player.addEffect(new EffectInstance(Effects.SLOW_FALLING,120,8));
				player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,120,3));
				player.setNoGravity(true);
				slownearmonsters(player,false);
				LINK_TIME.put(player,ZeldaConfig.LINKTIME.get());
			}
			else {
				player.removeEffect(Effects.SLOW_FALLING);
				player.removeEffect(Effects.MOVEMENT_SLOWDOWN);
				player.setNoGravity(false);
				LINK_TIME.put(player,-1);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLooseArrow(ArrowLooseEvent event) {
		if(!event.getWorld().isClientSide) {
			PlayerEntity player=event.getPlayer();
			player.removeEffect(Effects.SLOW_FALLING);
			player.removeEffect(Effects.MOVEMENT_SLOWDOWN);
			player.setNoGravity(false);
			slownearmonsters(player,true);
			LINK_TIME.put(player,-1);
		}
	}
	private static void slownearmonsters(PlayerEntity player,boolean remove) {
		World world=player.level;
		List<MobEntity> entitylist= world.getEntitiesOfClass(MobEntity.class,player.getBoundingBox().inflate(24, 24, 24) ,(MobEntity t)->true);
		Iterator<MobEntity> it=entitylist.iterator();
		while(it.hasNext()) {
			MobEntity mob=it.next();
			if(remove) {
				EntityFreezer.unFreezeMobEntity(mob);
			}else {
				EntityFreezer.FreezeMobEntity(mob,100);
			}
		}
	}
}
