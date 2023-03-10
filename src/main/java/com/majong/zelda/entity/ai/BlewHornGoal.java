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
	public boolean canUse() {
		// TODO 自动生成的方法存根
		if(this.attacker.level.getGameTime()-lastattacktime<60)
			return false;
			LivingEntity target=this.attacker.getTarget();
			if(target==null||!target.isAlive())
				return false;
			if(!attacker.canSee(target)||attacker.getHealth()!=attacker.getMaxHealth())
				return false;
		return true;
	}
	@Override
	public void start() {
		this.targetentity=this.attacker.getTarget();
		this.attackprocess=0;
		this.attacker.level.playSound(null,attacker.blockPosition(), SoundLoader.HORN.get(), SoundCategory.BLOCKS, 10f, 1f);
	}
	@Override
	public void tick() {
		if(attackprocess<50) {
			if(attackprocess%10==1)
				attacker.swing(Hand.OFF_HAND);
			 attackprocess++;
		}else {
			HornItem.AwakeOthers(targetentity, attacker);
			attackprocess=0;
			lastattacktime=this.attacker.level.getGameTime();
		}
	}
}
