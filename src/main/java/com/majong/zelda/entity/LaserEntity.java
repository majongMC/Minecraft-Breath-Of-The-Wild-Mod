package com.majong.zelda.entity;

import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.event.PlayerUseShield;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class LaserEntity extends ThrowableProjectile{
	public static final EntityDataAccessor<Integer> YAW = SynchedEntityData.defineId(LaserEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> PITCH = SynchedEntityData.defineId(LaserEntity.class, EntityDataSerializers.INT);
	private long spawntime=0;
	private LivingEntity owner;
	public LaserEntity(EntityType<? extends ThrowableProjectile> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
		this.setNoGravity(true);
		this.setDeltaMovement(0,0,0);
		spawntime=this.level.getGameTime();
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
		if(this.level.getGameTime()-spawntime>100) {
			this.discard();
			return;
		}
		}
		if(this.level.isClientSide) {
			level.addAlwaysVisibleParticle(ParticleTypes.CLOUD, this.getX(), this.getY()+0.5, this.getZ(), 0, 0, 0);
		}
	}
	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity target=result.getEntity();
		if(!target.level.isClientSide&&target!=owner&&target instanceof LivingEntity) {
			if(target instanceof Player) {
				trysheldreflect((Player) target);
			}
			else 
				explode();
		}
	}
	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if(!this.level.isClientSide&&FallingBlock.isFree(this.level.getBlockState(result.getBlockPos())))
			explode();
	}
	@Override
	protected void defineSynchedData() {
		// TODO �Զ����ɵķ������
		this.entityData.define(YAW, 0);
		this.entityData.define(PITCH, 0);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		// TODO �Զ����ɵķ������
		this.entityData.set(YAW, compound.getInt("yaw"));
		this.entityData.set(PITCH, compound.getInt("pitch"));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		// TODO �Զ����ɵķ������
		compound.putInt("yaw", this.entityData.get(YAW));
		compound.putInt("pitch", this.entityData.get(PITCH));
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		// TODO �Զ����ɵķ������
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	public void setspeed(float yaw,float pitch) {
		float f = 5F;
		double mz = -Math.sin(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI) * f / 2D;
		double mx = -(Math.cos(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI) * f) / 2D;
		double my = Math.sin(pitch / 180.0F * (float) Math.PI) * f / 2D;
		Vec3 speed=new Vec3(mx,my,mz);
		this.entityData.set(YAW, (int)yaw);
		this.entityData.set(PITCH, (int)pitch);
		this.setDeltaMovement(speed);
	}
	private void explode() {
		this.level.explode(owner,this.getX(),this.getY(),this.getZ(),5,BlockInteraction.NONE);
		this.kill();
	}
	private void trysheldreflect(Player player) {
		long respondtime=this.level.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
		if(ZeldaConfig.DISPLAYTIME.get()) {
		if(respondtime>100)
			player.sendMessage(new TranslatableComponent("反应时间"+respondtime+"(过晚)").withStyle(ChatFormatting.RED), UUID.randomUUID());
		else if(respondtime>ZeldaConfig.SHIELD.get()) {
			player.sendMessage(new TranslatableComponent("反应时间"+respondtime+"(过早)").withStyle(ChatFormatting.RED), UUID.randomUUID());
		}else {
			player.sendMessage(new TranslatableComponent("反应时间"+respondtime).withStyle(ChatFormatting.GREEN), UUID.randomUUID());
		}
		}
		if(respondtime<=ZeldaConfig.SHIELD.get()) {
			reflect(player);
		}
		else if(DataManager.data.get(player).unlocked[2]&&DataManager.data.get(player).skill[2]>0&&player.isShiftKeyDown()) {
			reflect(player);
			DataManager.data.get(player).skill[2]--;
			DataManager.sendzeldaplayerdatapack(player);
		}
		else if(player.getMainHandItem().getItem()==ItemLoader.ANCIENT_SHIELD.get()) {
			player.getMainHandItem().hurtAndBreak(16, player,(entity) -> {
		         entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		      });
			reflect(player);
		}
		else if(player.getOffhandItem().getItem()==ItemLoader.ANCIENT_SHIELD.get()) {
			player.getOffhandItem().hurtAndBreak(16, player,(entity) -> {
		         entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		      });
			reflect(player);
		}
		else
			explode();
	}
	private void reflect(Player player) {
		this.setowner(player);
		this.setDeltaMovement(this.getDeltaMovement().reverse());
	}
	public void setowner(LivingEntity owner) {
		this.owner=owner;
	}
}
