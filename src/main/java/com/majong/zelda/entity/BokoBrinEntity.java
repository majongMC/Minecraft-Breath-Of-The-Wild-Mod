package com.majong.zelda.entity;

import com.majong.zelda.entity.ai.BlewHornGoal;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BokoBrinEntity extends Monster{
	public static final EntityDataAccessor<Boolean> BLEW_HORN = SynchedEntityData.defineId(RockGiantEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(RockGiantEntity.class, EntityDataSerializers.BOOLEAN);
	private int animremaintime=-1;
	public AnimationState blewhornstate = new AnimationState();
	public AnimationState attackstate = new AnimationState();
	public BokoBrinEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new BlewHornGoal(this));
		this.goalSelector.addGoal(3, new DelayMeleeAttackGoal(this, 1.0D, true,18));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
		this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.WOODEN_SWORD,1));
		this.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(ItemLoader.HORN.get(),1));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BLEW_HORN, false);
		this.entityData.define(ATTACK, false);
	}
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.PIGLIN_AMBIENT;
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	      return SoundEvents.PIG_HURT;
	   }
	@Override
	protected SoundEvent getDeathSound() {
	      return SoundEvents.PIG_DEATH;
	   }
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			if(this.getEntityData().get(ATTACK)&&!isPlayingAnim()) {
				this.attackstate.start(this.tickCount);
				this.animremaintime=24;
			}
			if(this.getEntityData().get(BLEW_HORN)&&!isPlayingAnim()) {
				this.blewhornstate.start(this.tickCount);
				this.animremaintime=52;
			}
			if(animremaintime==0) {
				this.stopallanim();
			}
			if(animremaintime>-1) {
				animremaintime--;
			}
		}else {
			if(this.getTarget()==null||this.getTarget().isDeadOrDying()) {
				this.getEntityData().set(ATTACK, false);
				this.getEntityData().set(BLEW_HORN, false);
			}
			if(this.getHealth()<this.getMaxHealth()) {
				this.getEntityData().set(BLEW_HORN, false);
			}
		}
	}
	private void stopallanim() {
		blewhornstate.stop();
		attackstate.stop();
	}
	public boolean isPlayingAnim() {
		return animremaintime>0;
	}
}
