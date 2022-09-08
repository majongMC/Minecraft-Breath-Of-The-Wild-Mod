package com.majong.zelda.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

public class MoveToTargetGoal extends Goal{
	protected final PathfinderMob mob;
	protected final int keepdistance;
	private final double speedModifier;
	private int delayer=0;
	public MoveToTargetGoal(PathfinderMob mob,int keepdistance,double speedModifier) {
		this.mob=mob;
		this.keepdistance=keepdistance*keepdistance;
		this.speedModifier=speedModifier;
	}

	@Override
	public boolean canUse() {
		// TODO Auto-generated method stub
		PathNavigation navigation=mob.getNavigation();
		LivingEntity target=mob.getTarget();
		if(target==null||target.isDeadOrDying()||mob.isDeadOrDying()) {
			navigation.stop();
			return false;
			}
		if(delayer<=0) {
		double d0=mob.distanceToSqr(target);
		if(d0>keepdistance) {
			if(d0-keepdistance>25)
				delayer=5;
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
