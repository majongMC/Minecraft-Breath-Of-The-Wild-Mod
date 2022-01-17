package com.majong.zelda.entity.ai;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class ChangeDistanceNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	public ChangeDistanceNearestAttackableTargetGoal(MobEntity goalOwnerIn, Class<T> targetClassIn, int targetChanceIn,
			boolean checkSight, boolean nearbyOnlyIn, double distance) {
		super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, null);
		// TODO 自动生成的构造函数存根
		this.targetEntitySelector = (new EntityPredicate()).setDistance(distance);
	}
	

}
