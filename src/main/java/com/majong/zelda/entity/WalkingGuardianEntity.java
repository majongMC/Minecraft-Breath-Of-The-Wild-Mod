package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.MoveToTargetGoal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class WalkingGuardianEntity extends GuardianEntity{

	public WalkingGuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MoveToTargetGoal(this, 8, 1));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	}
}
