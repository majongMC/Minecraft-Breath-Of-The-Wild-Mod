package com.majong.zelda.entity;

import com.brandon3055.draconicevolution.entity.guardian.DraconicGuardianEntity;
import com.majong.zelda.Utils;
import com.majong.zelda.entity.ai.ChangeDistanceNearestAttackableTargetGoal;
import com.majong.zelda.entity.ai.GuardianAi;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class GuardianEntity extends MonsterEntity{

	public GuardianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO 自动生成的构造函数存根
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2,new GuardianAi(this));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 50.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		if(Utils.DRACONIC_EVOLUTION_LOADED) {
			this.targetSelector.addGoal(2, new ChangeDistanceNearestAttackableTargetGoal<>(this, DraconicGuardianEntity.class,1, false,false,64));
		}
		this.targetSelector.addGoal(3, new ChangeDistanceNearestAttackableTargetGoal<>(this, PlayerEntity.class,1, true,false,48));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
	}
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof PlayerEntity) {
		if(Math.random()<0.2)
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_CORE.get(),1));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_GEAR.get(),(int) (Math.random()*3)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SHAFT.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SPRING.get(),(int) (Math.random()*4)));
		this.spawnAtLocation(new ItemStack(ItemLoader.ANCIENT_SCREW.get(),(int) (Math.random()*4)));
		}
	}
}
