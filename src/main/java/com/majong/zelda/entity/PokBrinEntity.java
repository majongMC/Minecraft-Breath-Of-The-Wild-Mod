package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.BlewHornGoal;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class PokBrinEntity extends MonsterEntity{

	public PokBrinEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new BlewHornGoal(this));
		this.goalSelector.addGoal(3, new DelayMeleeAttackGoal(this, 1.0D, true,8));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 0.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
		this.getAttributeManager().createInstanceIfAbsent(Attributes.MOVEMENT_SPEED);
		this.getAttributeManager().createInstanceIfAbsent(Attributes.ATTACK_DAMAGE);
		this.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.WOODEN_SWORD,1));
		this.setHeldItem(Hand.OFF_HAND, new ItemStack(ItemLoader.HORN.get(),1));
		// TODO 自动生成的构造函数存根
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	      return SoundEvents.ENTITY_PIG_HURT;
	   }
	@Override
	protected SoundEvent getDeathSound() {
	      return SoundEvents.ENTITY_PIG_DEATH;
	   }
}
