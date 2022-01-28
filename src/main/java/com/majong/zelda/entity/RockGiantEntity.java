package com.majong.zelda.entity;

import com.majong.zelda.api.overlays.ZeldaBloodBarApi;
import com.majong.zelda.entity.ai.DelayMeleeAttackGoal;
import com.majong.zelda.entity.ai.DestroyBlockGoal;
import com.majong.zelda.event.PlayerSpottedEvent;
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
import net.minecraftforge.common.ToolType;

public class RockGiantEntity extends MonsterEntity{
	public static final DataParameter<Integer> HANDSWING = EntityDataManager.createKey(RockGiantEntity.class, DataSerializers.VARINT);
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
		this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
		this.getAttributeManager().createInstanceIfAbsent(Attributes.MOVEMENT_SPEED);
		this.getAttributeManager().createInstanceIfAbsent(Attributes.ATTACK_DAMAGE);
	}
	@Override
	public void registerData() {
		super.registerData();
		this.dataManager.register(HANDSWING, 0);
	}
	@Override
	public boolean hitByEntity(Entity entityIn) {
		if(entityIn instanceof PlayerEntity&&!this.world.isRemote) {
			PlayerEntity player=(PlayerEntity) entityIn;
			if(player.getHeldItemMainhand().getToolTypes().contains(ToolType.PICKAXE)) {
				this.attackEntityFrom(new EntityDamageSource("pickaxe",player), 20);
			}
		}
	      return false;
	   }
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(!this.world.isRemote&&(cause.getTrueSource() instanceof PlayerEntity||cause.isExplosion())) {
		this.entityDropItem(new ItemStack(Items.ANCIENT_DEBRIS,(int) (Math.random()*2)));
		this.entityDropItem(new ItemStack(Items.DIAMOND_ORE,(int) (Math.random()*3)));
		this.entityDropItem(new ItemStack(Items.EMERALD_ORE,(int) (Math.random()*3)));
		this.entityDropItem(new ItemStack(Items.REDSTONE_ORE,(int) (Math.random()*5)));
		this.entityDropItem(new ItemStack(Items.LAPIS_ORE,(int) (Math.random()*5)));
		this.entityDropItem(new ItemStack(Items.IRON_ORE,(int) (Math.random()*9)));
		this.entityDropItem(new ItemStack(Items.GOLD_ORE,(int) (Math.random()*10)));
		this.entityDropItem(new ItemStack(Items.NETHER_QUARTZ_ORE,(int) (Math.random()*12)));
		this.entityDropItem(new ItemStack(Items.COAL_ORE,(int) (Math.random()*13)));
		this.entityDropItem(new ItemStack(Items.COBBLESTONE,(int) (Math.random()*64)));
		}
		if(this.world.isRemote) {
			PlayerSpottedEvent.SoundRemainTime=0;
			Minecraft.getInstance().getSoundHandler().stop();
		}
	}
	@Override
	public void tick() {
		super.tick();
		if(this.world.isRemote) {
			lasty=currenty;
			currenty=this.getDataManager().get(RockGiantEntity.HANDSWING);
			ZeldaBloodBarApi.DisplayBloodBarClient(this.getHealth()/this.getMaxHealth(),this.getName());
			if(PlayerSpottedEvent.SoundRemainTime==0&&!this.dead) {
				Minecraft.getInstance().world.playSound(Minecraft.getInstance().player,this.getPosition(), SoundLoader.ROCK_GIANT.get(), SoundCategory.AMBIENT, 10f, 1f);
				PlayerSpottedEvent.SoundRemainTime=1200;
			}
		}
	}
}
