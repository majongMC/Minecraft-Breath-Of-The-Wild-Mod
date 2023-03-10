package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.ChangeDistanceNearestAttackableTargetGoal;
import com.majong.zelda.entity.ai.GuardianAi;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GuardianEntity extends MonsterEntity{

	public GuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2,new GuardianAi(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new ChangeDistanceNearestAttackableTargetGoal<>(this, PlayerEntity.class,1, true,false,48));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
	}
}
