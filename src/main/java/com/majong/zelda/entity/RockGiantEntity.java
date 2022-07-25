package com.majong.zelda.entity;

import com.majong.zelda.api.overlays.ZeldaBloodBarApi;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.entity.ai.DestroyBlockGoal;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class RockGiantEntity extends MonsterEntity{
	public static final DataParameter<Integer> HANDSWING = EntityDataManager.defineId(RockGiantEntity.class, DataSerializers.INT);
	public float lasty=0;
	public float currenty=0;
	public RockGiantEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO 自动生成的构造函数存根
		this.goalSelector.addGoal(1, new DestroyBlockGoal(this,4));
		this.goalSelector.addGoal(2, new DelayMeleeAttackGoal(this, 1.0D, true,10));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 50.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
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
		if(entityIn instanceof PlayerEntity&&!this.level.isClientSide) {
			PlayerEntity player=(PlayerEntity) entityIn;
			if(player.getMainHandItem().getToolTypes().contains(ToolType.PICKAXE)) {
				this.hurt(new EntityDamageSource("pickaxe",player), 20);
			}
		}
	      return false;
	   }
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(!this.level.isClientSide&&(cause.getEntity() instanceof PlayerEntity||cause.isExplosion())) {
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
	@OnlyIn(Dist.CLIENT)
	@Override
	public void tick() {
		super.tick();
		if(this.level.isClientSide) {
			lasty=currenty;
			currenty=this.getEntityData().get(RockGiantEntity.HANDSWING);
			ZeldaBloodBarApi.DisplayBloodBarClient(this.getHealth()/this.getMaxHealth(),this.getName());
			if(EntitySpottedEvent.SoundRemainTime==0&&!this.dead) {
				ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),this.blockPosition(), SoundLoader.ROCK_GIANT.get(), SoundCategory.AMBIENT, 10f, 1f);
				EntitySpottedEvent.SoundRemainTime=1200;
			}
		}
	}
}
