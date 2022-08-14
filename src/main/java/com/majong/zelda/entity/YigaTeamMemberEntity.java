package com.majong.zelda.entity;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.event.PlayerHurtEvent;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class YigaTeamMemberEntity extends MonsterEntity{
	public static final DataParameter<Boolean> ACTIVATED = EntityDataManager.defineId(YigaTeamMemberEntity.class, DataSerializers.BOOLEAN);
	public YigaTeamMemberEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
		super(p_i48553_1_, p_i48553_2_);
		// TODO 自动生成的构造函数存根
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 0.0F));
		if(!ZeldaConfig.NPCONLY.get())
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	@Override
	protected void defineSynchedData() {
		// TODO 自动生成的方法存根
		super.defineSynchedData();
		this.entityData.define(ACTIVATED, false);
	}
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		super.readAdditionalSaveData(compound);
		this.entityData.set(ACTIVATED, compound.getBoolean("activated"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		super.addAdditionalSaveData(compound);
		compound.putBoolean("activated", this.entityData.get(ACTIVATED));
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
		if(cause.getEntity() instanceof PlayerEntity&&this.entityData.get(ACTIVATED)) {
			this.spawnAtLocation(new ItemStack(Items.EMERALD,1));
		}
	}
	public void activate() {
		this.entityData.set(ACTIVATED, true);
		if(ZeldaConfig.DELAY_ATTACK.get())
			this.goalSelector.addGoal(3, new DelayMeleeAttackGoal(this, 1.0D, true,8));
		else
			this.goalSelector.addGoal(3, new DelayMeleeAttackGoal(this, 1.0D, true,0));
		this.setCustomName(new TranslationTextComponent("name.yiga.activated"));
	}
	public boolean isactivated() {
		return this.entityData.get(ACTIVATED);
	}
	public void yigadamage(LivingEntity entity) {
		int damage=7;
		if(entity.level.getServer().isSingleplayer())
			damage=10;
		if(entity instanceof PlayerEntity) {
			if(PlayerHurtEvent.TryReflect((PlayerEntity) entity, this, damage))
				return;
		}
		if(entity.getHealth()>damage) {
			entity.setHealth(entity.getHealth()-damage);
			if(entity instanceof PlayerEntity) {
				Networking.SOUND.send(
					      PacketDistributor.PLAYER.with(
					                            () -> (ServerPlayerEntity) entity
					             ),
					             new SoundPack(11,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
			}
		}
		else {
			if(entity instanceof PlayerEntity) {
				PlayerEntity player=(PlayerEntity) entity;
				DamageSource yigadamage=(new DamageSource("yigadamage")).bypassArmor().bypassInvul();
				DataManager.AdjustAllSkills(player, false);
				player.hurt(yigadamage,20);
				if(!player.isDeadOrDying())
					player.kill();
				DataManager.AdjustAllSkills(player, true);
			}else
				entity.kill();
		}
	}
}
