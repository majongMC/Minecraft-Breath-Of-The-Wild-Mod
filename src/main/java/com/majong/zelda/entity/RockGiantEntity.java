package com.majong.zelda.entity;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.entity.ai.RockGiantDestroyBlockGoal;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;

public class RockGiantEntity extends Monster{
	public static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(RockGiantEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> KNOCK = SynchedEntityData.defineId(RockGiantEntity.class, EntityDataSerializers.BOOLEAN);
	//public float lasty=0;
	//public float currenty=0;
	private int animremaintime=-1;
	public AnimationState attackaAnimationState = new AnimationState();
	public AnimationState attackbAnimationState = new AnimationState();
	public AnimationState knockaAnimationState = new AnimationState();
	public AnimationState knockbAnimationState = new AnimationState();
	public RockGiantEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
		this.goalSelector.addGoal(1, new RockGiantDestroyBlockGoal(this));
		this.goalSelector.addGoal(2, new DelayMeleeAttackGoal(this, 1.0D, true,20));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 50.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
		this.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
	}
	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ATTACK, false);
		this.entityData.define(KNOCK, false);
	}
	@Override
	public boolean skipAttackInteraction(Entity entityIn) {
		if(entityIn instanceof Player&&!this.level.isClientSide) {
			Player player=(Player) entityIn;
			if(player.getMainHandItem().getItem().canPerformAction(player.getMainHandItem(), ToolActions.PICKAXE_DIG)) {
				this.hurt(new EntityDamageSource("pickaxe",player), 20);
			}
		}
	      return false;
	   }
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		/*if(!this.level.isClientSide&&(cause.getEntity() instanceof Player||cause.isExplosion())) {
		this.spawnAtLocation(new ItemStack(Items.ANCIENT_DEBRIS,(int) (Math.random()*2)));
		this.spawnAtLocation(new ItemStack(Items.DIAMOND_ORE,(int) (Math.random()*3)));
		this.spawnAtLocation(new ItemStack(Items.EMERALD_ORE,(int) (Math.random()*3)));
		this.spawnAtLocation(new ItemStack(Items.REDSTONE_ORE,(int) (Math.random()*5)));
		this.spawnAtLocation(new ItemStack(Items.LAPIS_ORE,(int) (Math.random()*5)));
		this.spawnAtLocation(new ItemStack(Items.IRON_ORE,(int) (Math.random()*9)));
		this.spawnAtLocation(new ItemStack(Items.GOLD_ORE,(int) (Math.random()*10)));
		this.spawnAtLocation(new ItemStack(Items.NETHER_QUARTZ_ORE,(int) (Math.random()*12)));
		this.spawnAtLocation(new ItemStack(Items.COAL_ORE,(int) (Math.random()*13)));
		this.spawnAtLocation(new ItemStack(Items.COBBLESTONE,(int) (Math.random()*64)));
		}*/
		if(this.level.isClientSide) {
			EntitySpottedEvent.SoundRemainTime=0;
			ClientUtils.ClientStopSound();
		}
	}
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			//lasty=currenty;
			//currenty=this.getEntityData().get(RockGiantEntity.HANDSWING);
			if(this.getEntityData().get(RockGiantEntity.ATTACK)&&!isPlayingAnim()) {
				this.animremaintime=40;
				if(Math.random()>0.5)
		    		  this.attackaAnimationState.start(this.tickCount);
			         else
			        	 this.attackbAnimationState.start(this.tickCount);
			}
			if(this.getEntityData().get(RockGiantEntity.KNOCK)&&!isPlayingAnim()) {
				if(Math.random()>0.5)
					this.knockaAnimationState.start(this.tickCount);
				else
					this.knockbAnimationState.start(this.tickCount);
				this.animremaintime=40;
			}
			//ZeldaHealthBarApi.DisplayHealthBarClient(this.getHealth()/this.getMaxHealth(),this.getName());
			if(EntitySpottedEvent.SoundRemainTime<=0&&!this.dead) {
				ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),this.blockPosition(), SoundLoader.ROCK_GIANT.get(), SoundSource.AMBIENT, 10f, 1f);
				EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.ROCK_GIANT.get();
			}
			if(animremaintime==0) {
				this.stopallanim();
			}
			if(animremaintime>-1) {
				animremaintime--;
			}
		}else {
			if(this.getTarget()==null||this.getTarget().isDeadOrDying())
				this.getEntityData().set(RockGiantEntity.ATTACK, false);
		}
	}
	private void stopallanim() {
		this.attackaAnimationState.stop();
		this.attackbAnimationState.stop();
		this.knockaAnimationState.stop();
		this.knockbAnimationState.stop();
	}
	public boolean isPlayingAnim() {
		return animremaintime>0;
	}
}
