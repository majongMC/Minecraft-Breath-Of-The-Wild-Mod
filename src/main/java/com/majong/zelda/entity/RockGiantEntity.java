package com.majong.zelda.entity;

import com.majong.zelda.api.overlays.ZeldaHealthBarApi;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.entity.ai.DestroyBlockGoal;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;

public class RockGiantEntity extends Monster{
	public static final EntityDataAccessor<Integer> HANDSWING = SynchedEntityData.defineId(RockGiantEntity.class, EntityDataSerializers.INT);
	public float lasty=0;
	public float currenty=0;
	public RockGiantEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
		this.goalSelector.addGoal(1, new DestroyBlockGoal(this,4));
		this.goalSelector.addGoal(2, new DelayMeleeAttackGoal(this, 1.0D, true,10));
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
		this.entityData.define(HANDSWING, 0);
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
		if(!this.level.isClientSide&&(cause.getEntity() instanceof Player||cause.isExplosion())) {
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
		}
		if(this.level.isClientSide) {
			EntitySpottedEvent.SoundRemainTime=0;
			Minecraft.getInstance().getSoundManager().stop();
		}
	}
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			lasty=currenty;
			currenty=this.getEntityData().get(RockGiantEntity.HANDSWING);
			ZeldaHealthBarApi.DisplayHealthBarClient(this.getHealth()/this.getMaxHealth(),this.getName());
			if(EntitySpottedEvent.SoundRemainTime<=0&&!this.dead) {
				Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,this.blockPosition(), SoundLoader.ROCK_GIANT.get(), SoundSource.AMBIENT, 10f, 1f);
				EntitySpottedEvent.SoundRemainTime=1200;
			}
		}
	}
}
