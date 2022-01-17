package com.majong.zelda.entity;

import com.majong.zelda.item.ItemLoader;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class WalkingGuardianEntity extends GuardianEntity{

	public WalkingGuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(cause.getTrueSource() instanceof PlayerEntity) {
		if(Math.random()<0.5)
			this.entityDropItem(new ItemStack(ItemLoader.ANCIENT_CORE.get(),1));
		if(Math.random()<0.1)
			this.entityDropItem(new ItemStack(ItemLoader.BIG_ANCIENT_CORE.get(),1));
		this.entityDropItem(new ItemStack(ItemLoader.ANCIENT_GEAR.get(),(int) (Math.random()*3)));
		this.entityDropItem(new ItemStack(ItemLoader.ANCIENT_SHAFT.get(),(int) (Math.random()*4)));
		this.entityDropItem(new ItemStack(ItemLoader.ANCIENT_SPRING.get(),(int) (Math.random()*4)));
		this.entityDropItem(new ItemStack(ItemLoader.ANCIENT_SCREW.get(),(int) (Math.random()*4)));
		}
	}
}
