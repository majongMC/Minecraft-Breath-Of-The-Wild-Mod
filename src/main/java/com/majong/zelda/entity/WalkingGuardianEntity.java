package com.majong.zelda.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WalkingGuardianEntity extends GuardianEntity{

	public WalkingGuardianEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		// TODO �Զ����ɵĹ��캯�����
	}
	/*@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof Player) {
		if(Math.random()<0.5)
			this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_CORE.get(),1));
		if(Math.random()<0.1)
			this.spawnAtLocation(new ItemStack(ItemLoader.BIG_ANCIENT_CORE.get(),1));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_GEAR.get(),(int) (Math.random()*3)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SHAFT.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SPRING.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SCREW.get(),(int) (Math.random()*4)));
		}
	}*/
}
