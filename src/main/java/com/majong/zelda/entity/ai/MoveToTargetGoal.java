package com.majong.zelda.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNavigator;

public class MoveToTargetGoal extends Goal{
	protected final CreatureEntity mob;
	protected final int keepdistance;
	private final double speedModifier;
	private int delayer=0;
	public MoveToTargetGoal(CreatureEntity mob,int keepdistance,double speedModifier) {
		this.mob=mob;
		this.keepdistance=keepdistance*keepdistance;
		this.speedModifier=speedModifier;
	}

	@Override
	public boolean canUse() {
		// TODO Auto-generated method stub
		PathNavigator navigation=mob.getNavigation();
		LivingEntity target=mob.getTarget();
		if(target==null||target.isDeadOrDying()||mob.isDeadOrDying()) {
			navigation.stop();
			return false;
			}
		if(delayer<=0) {
		double d0=mob.distanceToSqr(target);
		if(d0>keepdistance) {
			if(d0-keepdistance>25)
				delayer=10;
			else
				delayer=0;
			navigation.moveTo(target, this.speedModifier);
		}else
			navigation.stop();
		}
		delayer--;
		return false;
	}
}
