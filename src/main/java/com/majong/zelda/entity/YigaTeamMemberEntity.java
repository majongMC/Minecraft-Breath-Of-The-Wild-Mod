package com.majong.zelda.entity;

import javax.annotation.Nullable;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.event.EntityHurtEvent;
import com.majong.zelda.gui.OpenDialogBox;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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

public class YigaTeamMemberEntity extends Monster{
	public static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(YigaTeamMemberEntity.class, EntityDataSerializers.BOOLEAN);
	public AnimationState attackstate = new AnimationState();
	public static final byte ATTACK_EVENT=102;
	private int animremaintime=-1;
	public YigaTeamMemberEntity(EntityType<? extends Monster> p_i48553_1_, Level p_i48553_2_) {
		super(p_i48553_1_, p_i48553_2_);
	}
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.0F));
		if(!ZeldaConfig.NPCONLY.get())
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	@Override
	public void handleEntityEvent(byte eventid) {
		switch (eventid) {
        case ATTACK_EVENT:this.attackstate.start(this.tickCount);this.animremaintime=42;break;
        default:super.handleEntityEvent(eventid);
     }
	}
	@Override
	protected void defineSynchedData() {
		// TODO �Զ����ɵķ������
		super.defineSynchedData();
		this.entityData.define(ACTIVATED, false);
	}
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		// TODO �Զ����ɵķ������
		super.readAdditionalSaveData(compound);
		this.entityData.set(ACTIVATED, compound.getBoolean("activated"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		// TODO �Զ����ɵķ������
		super.addAdditionalSaveData(compound);
		compound.putBoolean("activated", this.entityData.get(ACTIVATED));
	}
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			if(animremaintime>-1) {
				animremaintime--;
			}
		}
	}
	@Override
	protected SoundEvent getAmbientSound() {
		if(this.entityData.get(ACTIVATED))
			return SoundEvents.VINDICATOR_AMBIENT;
		else
			return SoundEvents.VILLAGER_AMBIENT;
	   }
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	    if(this.entityData.get(ACTIVATED))
	    	return SoundEvents.VINDICATOR_HURT;
	    else
	    	return SoundEvents.VILLAGER_HURT;
	   }
	@Override
	protected SoundEvent getDeathSound() {
		if(this.entityData.get(ACTIVATED))
			return SoundEvents.VINDICATOR_DEATH;
		else
			return SoundEvents.VILLAGER_DEATH;
	   }
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof Player&&this.entityData.get(ACTIVATED)) {
			this.spawnAtLocation(new ItemStack(Items.EMERALD,1));
		}
	}
	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if(!isactivated()) {
			if(this.level.isClientSide) {
				new OpenDialogBox(this);
			}else {
				if(!ZeldaConfig.NPCONLY.get())
					activate();
			}
		}
	     return super.mobInteract(player, hand);
	   }
	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
		SpawnGroupData data=super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		this.setLeftHanded(false);
		return data;
	}
	private void activate() {
		this.entityData.set(ACTIVATED, true);
		this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ItemLoader.CHOPPING_WIND_BLADE.get(),1));
		this.goalSelector.addGoal(3, new DelayMeleeAttackGoal(this, 1.0D, true,30));
		this.setCustomName(Component.translatable("name.yiga.activated"));
	}
	public boolean isactivated() {
		return this.entityData.get(ACTIVATED);
	}
	public void yigadamage(LivingEntity entity) {
		int damage=7;
		if(entity.level.getServer().isSingleplayer())
			damage=10;
		if(entity.invulnerableTime>10)
			return;
		entity.invulnerableTime=20;
		if(entity instanceof Player) {
			if(EntityHurtEvent.TryReflect((Player) entity, this, damage))
				return;
		}
		if(entity.getHealth()>damage) {
			entity.setHealth(entity.getHealth()-damage);
		}
		else {
			if(entity instanceof Player) {
				Player player=(Player) entity;
				DamageSource yigadamage=level.damageSources().mobAttack(this);
				DataManager.AdjustAllSkills(player, false);
				player.hurt(yigadamage,entity.getHealth()*10);
				if(!player.isDeadOrDying())
					player.kill();
				DataManager.AdjustAllSkills(player, true);
			}else
				entity.kill();
		}
		playSound(SoundLoader.BLADE_HIT.get());
	}
	public boolean isPlayingAnim() {
		return animremaintime>0;
	}
}
