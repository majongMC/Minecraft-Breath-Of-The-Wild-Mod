package com.majong.zelda.entity.ai;

import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.item.HornItem;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class BlewHornGoal extends Goal{
	private BokoBrinEntity attacker;
	private long lastattacktime=0;
	private int attackprocess;
	LivingEntity targetentity;
	public BlewHornGoal(BokoBrinEntity creature) {
		this.attacker=creature;
	}
	@Override
	public boolean canUse() {
		// TODO �Զ����ɵķ������
		if(this.attacker.level.getGameTime()-lastattacktime<60)
			return false;
			LivingEntity target=this.attacker.getTarget();
			if(target==null||!target.isAlive())
				return false;
			if(!attacker.hasLineOfSight(target)||attacker.getHealth()!=attacker.getMaxHealth())
				return false;
		return true;
	}
	@Override
	public void start() {
		this.targetentity=this.attacker.getTarget();
		this.attackprocess=0;
	}
	@Override
	public void tick() {
		if(attackprocess==0) {
			this.attacker.level.broadcastEntityEvent(attacker,BokoBrinEntity.BLEW_EVENT);
			attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,48,9,false,false));
		}
		if(attackprocess==2)
			this.attacker.level.playSound(null,attacker.blockPosition(), SoundLoader.HORN.get(), SoundSource.BLOCKS, 10f, 1f);
		if(attackprocess<25) {
			 attackprocess++;
		}else {
			HornItem.AwakeOthers(targetentity, attacker);
			attackprocess=0;
			lastattacktime=this.attacker.level.getGameTime();
		}
	}
}
