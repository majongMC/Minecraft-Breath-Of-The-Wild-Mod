package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.ChangeDistanceNearestAttackableTargetGoal;
import com.majong.zelda.entity.ai.GuardianAi;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GuardianEntity extends Monster{

	public GuardianEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2,new GuardianAi(this));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new ChangeDistanceNearestAttackableTargetGoal<>(this, Player.class,1, true,false,48));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
	}
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof Player) {
		if(Math.random()<0.2)
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_CORE.get(),1));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_GEAR.get(),(int) (Math.random()*3)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SHAFT.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SPRING.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SCREW.get(),(int) (Math.random()*4)));
		}
	}
}
