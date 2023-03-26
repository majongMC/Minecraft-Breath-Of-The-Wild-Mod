package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.MoveToTargetGoal;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WalkingGuardianEntity extends GuardianEntity{

	public WalkingGuardianEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
	}
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MoveToTargetGoal(this, 8, 1));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
}
