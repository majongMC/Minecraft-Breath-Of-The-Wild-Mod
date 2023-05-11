package com.majong.zelda.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class ChangeDistanceNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	public ChangeDistanceNearestAttackableTargetGoal(Mob goalOwnerIn, Class<T> targetClassIn, int targetChanceIn,
			boolean checkSight, boolean nearbyOnlyIn, double distance) {
		super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, null);
		// TODO �Զ����ɵĹ��캯�����
		this.targetConditions = (TargetingConditions.forCombat().range(distance));
	}
	

}
