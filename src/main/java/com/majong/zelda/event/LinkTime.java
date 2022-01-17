package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
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
		}
	}
}
