package com.majong.zelda.entity.ai;

import com.majong.zelda.item.HornItem;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class BlewHornGoal extends Goal{
	private PathfinderMob attacker;
	private long lastattacktime=0;
	private int attackprocess;
	LivingEntity targetentity;
	public BlewHornGoal(PathfinderMob creature) {
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
		this.attacker.level.playSound(null,attacker.blockPosition(), SoundLoader.HORN.get(), SoundSource.BLOCKS, 10f, 1f);
	}
	@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}
	@Override
	public void stop() {
		this.start();
	}
	@Override
	public void tick() {
		if(attackprocess<50) {
			if(attackprocess%10==1)
				attacker.swing(InteractionHand.OFF_HAND);
			 attackprocess++;
		}else {
			HornItem.AwakeOthers(targetentity, attacker);
			attackprocess=0;
			lastattacktime=this.attacker.level.getGameTime();
		}
	}
}
