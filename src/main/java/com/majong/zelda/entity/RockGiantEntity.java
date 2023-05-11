package com.majong.zelda.entity;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.entity.ai.RockGiantDestroyBlockGoal;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;

public class RockGiantEntity extends Monster{
	public static final byte KNOCK_EVENT=101;
	public static final byte ATTACK_EVENT=102;
	public AnimationState attackaAnimationState = new AnimationState();
	public AnimationState attackbAnimationState = new AnimationState();
	public AnimationState knockaAnimationState = new AnimationState();
	public AnimationState knockbAnimationState = new AnimationState();
	public RockGiantEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
	}
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RockGiantDestroyBlockGoal(this));
		this.goalSelector.addGoal(2, new DelayMeleeAttackGoal(this, 1.0D, true,20));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
	}
	@Override
	public void handleEntityEvent(byte eventid) {
		switch (eventid) {
        case KNOCK_EVENT:performknockanim();break;
        case ATTACK_EVENT:performattackanim();break;
        default:super.handleEntityEvent(eventid);
     }
	}
	@Override
	public boolean skipAttackInteraction(Entity entityIn) {
		if(entityIn instanceof Player&&!this.level.isClientSide) {
			Player player=(Player) entityIn;
			if(player.getMainHandItem().getItem() instanceof PickaxeItem) {
				this.hurt(level.damageSources().mobAttack(player), 20);
			}
		}
	      return false;
	   }
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(this.level.isClientSide) {
			EntitySpottedEvent.SoundRemainTime=0;
			ClientUtils.ClientStopSound();
		}
	}
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()&&!this.dead) {
				ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),this.blockPosition(), SoundLoader.ROCK_GIANT.get(), SoundSource.AMBIENT, 10f, 1f);
				EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.ROCK_GIANT.get();
			}
		}
	}
	private void performknockanim() {
		if(Math.random()>0.5)
			this.knockaAnimationState.start(this.tickCount);
		else
			this.knockbAnimationState.start(this.tickCount);
	}
	private void performattackanim() {
			if(Math.random()>0.5)
	    		  this.attackaAnimationState.start(this.tickCount);
		         else
		        	 this.attackbAnimationState.start(this.tickCount);
	}
}
