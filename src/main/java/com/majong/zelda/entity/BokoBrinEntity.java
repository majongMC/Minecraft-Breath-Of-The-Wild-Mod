package com.majong.zelda.entity;

import javax.annotation.Nullable;

import com.majong.zelda.entity.ai.BlewHornGoal;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.animation.AnimationState;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;

public class BokoBrinEntity extends Monster{
	public static final byte BLEW_EVENT=101;
	public static final byte ATTACK_EVENT=102;
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
	}
	@Override
	public void handleEntityEvent(byte eventid) {
		switch (eventid) {
        case BLEW_EVENT:this.blewhornstate.start(this.tickCount);break;
        case ATTACK_EVENT:this.attackstate.start(this.tickCount);break;
        default:super.handleEntityEvent(eventid);
     }
	}
	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
		SpawnGroupData data=super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		this.setLeftHanded(false);
		return data;
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
}
