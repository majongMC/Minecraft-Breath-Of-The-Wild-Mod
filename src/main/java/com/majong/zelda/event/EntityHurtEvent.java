package com.majong.zelda.event;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.advancement.TriggerRegistery;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.sound.SoundLoader;

import majongmc.hllib.common.event.LivingDamageEvent;
import majongmc.hllib.mixin.common.MobAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;

public class EntityHurtEvent {
	public static void onEntityHurt(LivingDamageEvent event) {
		if(Math.random()<0.33&&event.getEntity() instanceof Chicken&&!event.getEntity().level.isClientSide&&event.getSource().getEntity()!=null&&event.getSource().getEntity() instanceof LivingEntity) {
			Chicken chicken=(Chicken) event.getEntity();
			chicken.playSound(SoundLoader.CHICKEN_GOD.get());
			if(event.getSource().getEntity() instanceof Player)
				TriggerRegistery.CHICKEN_GOD.trigger((ServerPlayer) event.getSource().getEntity());
			List<Chicken> chickenlist= chicken.level.getEntitiesOfClass(Chicken.class,chicken.getBoundingBox().inflate(16, 16, 16));
			Iterator<Chicken> it=chickenlist.iterator();
			while(it.hasNext()) {
				Chicken chickens=it.next();
				((MobAccessor)chickens).getgoalSelector().addGoal(0,new DelayMeleeAttackGoal(chickens,1,true,1));
				chickens.setTarget((LivingEntity) event.getSource().getEntity());
			}
		}
		if(event.getEntity() instanceof Player&&!event.getEntity().level.isClientSide) {
			event.setCanceled(TryReflect((Player) event.getEntity(),event.getSource().getEntity(),event.getAmount()));
		}
	}
	public static boolean TryReflect(Player player,Entity source,float amount) {
		long respondtime=player.level.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
		if(respondtime<=ZeldaConfig.SHIELD.get()) {
			if(source==null||!(source instanceof LivingEntity)||source instanceof Player||source instanceof Chicken)
				return false;
			float amountback=0;
			float maxhealth=((LivingEntity)source).getMaxHealth();
			if(maxhealth>100) {
				if(amount*3+5>0.1F*maxhealth)
					amountback=0.1F*maxhealth;
				else
					amountback=amount*3+5;
			}
			else
				amountback=amount*3+5;
			PlayerUseShield.SHIELD_REFLECT_ACCOMPLISH.put(player,true);
			TriggerRegistery.SHIELD_REFLECT.trigger((ServerPlayer) player);
			source.hurt(source.level.damageSources().thorns(player), amountback);
			float yaw=player.yHeadRot;
			float f = 2F;
			double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			source.setDeltaMovement(source.getDeltaMovement().add(mx,0.1, mz));
			player.level.playSound(null,player.blockPosition(), SoundLoader.REFLECT.get(), SoundSource.BLOCKS, 10f, 1f);
			return true;
		}
		if(DataManager.data.get(player).unlocked[2]&&DataManager.data.get(player).skill[2]>0&&source instanceof LivingEntity&&source!=player&&amount>0&&player.isShiftKeyDown()&&!(source instanceof Chicken)) {
			float amountback=0;
			float maxhealth=((LivingEntity)source).getMaxHealth();
			if(maxhealth>100) {
				if(amount*6+10>0.15F*maxhealth)
					amountback=0.15F*maxhealth;
				else
					amountback=amount*6+10;
			}
			else
				amountback=amount*6+10;
			source.hurt(source.level.damageSources().thorns(player), amountback);
			DataManager.data.get(player).skill[2]--;
			DataManager.sendzeldaplayerdatapack(player);
			player.level.playSound(null,player.blockPosition(), SoundLoader.REFLECT.get(), SoundSource.BLOCKS, 10f, 1f);
			return true;
		}
		return false;
	}
}
