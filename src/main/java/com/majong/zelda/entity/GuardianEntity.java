package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.ChangeDistanceNearestAttackableTargetGoal;
import com.majong.zelda.entity.ai.GuardianAi;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GuardianEntity extends Monster{

	public GuardianEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2,new GuardianAi(this));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new ChangeDistanceNearestAttackableTargetGoal<>(this, Player.class,1, true,false,48));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
	}
}
