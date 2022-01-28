package com.majong.zelda.event;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.BombEntity;

import net.minecraft.entity.LivingEntity;
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
	@SubscribeEvent
	public static void onPlayerNockArrow(ArrowNockEvent event) {
		if(!event.getWorld().isRemote) {
			if(!event.getPlayer().isOnGround()&&!event.getPlayer().isElytraFlying()) {
				event.getPlayer().setMotion(Vector3d.ZERO);
				event.getPlayer().addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,120,8));
				event.getPlayer().addPotionEffect(new EffectInstance(Effects.SLOWNESS,120,3));
				event.getPlayer().setNoGravity(true);
				slownearmonsters(event.getPlayer(),false);
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						PlayerEntity player=event.getPlayer();
						for(int i=0;i<ZeldaConfig.LINKTIME.get();i++) {
						try {Thread.sleep(50);} catch (InterruptedException e) {break;}
						if(player==null||!player.hasNoGravity())
							break;
						}
						if(!(player==null))
						event.getPlayer().setNoGravity(false);
					}});
				t.start();
			}
			else {
				event.getPlayer().removePotionEffect(Effects.SLOW_FALLING);
				event.getPlayer().removePotionEffect(Effects.SLOWNESS);
				event.getPlayer().setNoGravity(false);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLooseArrow(ArrowLooseEvent event) {
		if(!event.getWorld().isRemote) {
			event.getPlayer().removePotionEffect(Effects.SLOW_FALLING);
			event.getPlayer().removePotionEffect(Effects.SLOWNESS);
			event.getPlayer().setNoGravity(false);
			slownearmonsters(event.getPlayer(),true);
		}
	}
	private static void slownearmonsters(PlayerEntity player,boolean remove) {
		World world=player.world;
		List<LivingEntity> entitylist= world.getEntitiesWithinAABB(LivingEntity.class,player.getBoundingBox().grow(24, 24, 24) ,new Predicate<Object>() {

			@Override
			public boolean test(Object t) {
				// TODO 自动生成的方法存根
				return t instanceof LivingEntity&&!(t instanceof PlayerEntity);
			}});
		Iterator<LivingEntity> it=entitylist.iterator();
		while(it.hasNext()) {
			LivingEntity entity=it.next();
			if(remove) {
				entity.removePotionEffect(Effects.SLOWNESS);
			}else {
				entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS,100,9));
			}
		}
	}
}
