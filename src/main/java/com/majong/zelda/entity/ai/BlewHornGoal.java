package com.majong.zelda.entity.ai;

import com.majong.zelda.item.HornItem;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;

public class BlewHornGoal extends Goal{
	private CreatureEntity attacker;
	private long lastattacktime=0;
	private int attackprocess;
	LivingEntity targetentity;
	public BlewHornGoal(CreatureEntity creature) {
		this.attacker=creature;
	}
	@Override
	public boolean shouldExecute() {
		// TODO 自动生成的方法存根
		if(this.attacker.world.getGameTime()-lastattacktime<60)
			return false;
			LivingEntity target=this.attacker.getAttackTarget();
			if(target==null||!target.isAlive())
				return false;
			if(!attacker.canEntityBeSeen(target)||attacker.getHealth()!=attacker.getMaxHealth())
				return false;
		return true;
	}
	@Override
	public void startExecuting() {
		this.targetentity=this.attacker.getAttackTarget();
		this.attackprocess=0;
		this.attacker.world.playSound(null,attacker.getPosition(), SoundLoader.HORN.get(), SoundCategory.BLOCKS, 10f, 1f);
	}
	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}
	@Override
	public void resetTask() {
		this.startExecuting();
	}
	@Override
	public void tick() {
		if(attackprocess<50) {
			if(attackprocess%10==1)
				attacker.swingArm(Hand.MAIN_HAND);
			 attackprocess++;
		}else {
			HornItem.AwakeOthers(targetentity, attacker);
			attackprocess=0;
			lastattacktime=this.attacker.world.getGameTime();
		}
	}
}
