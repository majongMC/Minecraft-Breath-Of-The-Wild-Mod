package com.majong.zelda.entity;

import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.event.PlayerUseShield;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class LaserEntity extends ThrowableEntity{
	public static final DataParameter<Integer> YAW = EntityDataManager.defineId(LaserEntity.class, DataSerializers.INT);
	public static final DataParameter<Integer> PITCH = EntityDataManager.defineId(LaserEntity.class, DataSerializers.INT);
	private long spawntime=0;
	private LivingEntity owner;
	public LaserEntity(EntityType<? extends ThrowableEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setNoGravity(true);
		this.setDeltaMovement(0,0,0);
		spawntime=this.level.getGameTime();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
		if(this.level.getGameTime()-spawntime>100) {
			this.kill();
			return;
		}
		}
		if(this.level.isClientSide) {
			level.addAlwaysVisibleParticle(ParticleTypes.CLOUD, this.getX(), this.getY()+0.5, this.getZ(), 0, 0, 0);
		}
	}
	protected void onHitEntity(EntityRayTraceResult result) {
		super.onHitEntity(result);
		Entity target=result.getEntity();
		if(!target.level.isClientSide&&target!=owner&&target instanceof LivingEntity) {
			if(target instanceof PlayerEntity) {
				trysheldreflect((PlayerEntity) target);
			}
			else 
				explode();
		}
	}
	protected void onHitBlock(BlockRayTraceResult result) {
		super.onHitBlock(result);
		if(!this.level.isClientSide&&FallingBlock.isFree(this.level.getBlockState(result.getBlockPos())))
			explode();
	}
	@Override
	protected void defineSynchedData() {
		// TODO 自动生成的方法存根
		this.entityData.define(YAW, 0);
		this.entityData.define(PITCH, 0);
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		this.entityData.set(YAW, compound.getInt("yaw"));
		this.entityData.set(PITCH, compound.getInt("pitch"));
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		compound.putInt("yaw", this.entityData.get(YAW));
		compound.putInt("pitch", this.entityData.get(PITCH));
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		// TODO 自动生成的方法存根
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	public void setspeed(float yaw,float pitch) {
		float f = 5F;
		double mz = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f / 2D;
		double mx = -(MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f) / 2D;
		double my = MathHelper.sin(pitch / 180.0F * (float) Math.PI) * f / 2D;
		Vector3d speed=new Vector3d(mx,my,mz);
		this.entityData.set(YAW, (int)yaw);
		this.entityData.set(PITCH, (int)pitch);
		this.setDeltaMovement(speed);
	}
	private void explode() {
		this.level.explode(owner,this.getX(),this.getY(),this.getZ(),5,Mode.NONE);
		this.kill();
	}
	private void trysheldreflect(PlayerEntity player) {
		long respondtime=this.level.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
		if(ZeldaConfig.DISPLAYTIME.get()) {
			if(respondtime>100)
				player.sendMessage(new TranslationTextComponent("反应时间"+respondtime+"(过晚)").withStyle(TextFormatting.RED), UUID.randomUUID());
			else if(respondtime>ZeldaConfig.SHIELD.get()) {
				player.sendMessage(new TranslationTextComponent("反应时间"+respondtime+"(过早)").withStyle(TextFormatting.RED), UUID.randomUUID());
			}else {
				player.sendMessage(new TranslationTextComponent("反应时间"+respondtime).withStyle(TextFormatting.GREEN), UUID.randomUUID());
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
		         entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
		      });
			reflect(player);
		}
		else if(player.getOffhandItem().getItem()==ItemLoader.ANCIENT_SHIELD.get()) {
			player.getOffhandItem().hurtAndBreak(16, player,(entity) -> {
		         entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
		      });
			reflect(player);
		}
		else
			explode();
	}
	private void reflect(PlayerEntity player) {
		this.setowner(player);
		this.setDeltaMovement(this.getDeltaMovement().reverse());
	}
	public void setowner(LivingEntity owner) {
		this.owner=owner;
	}
}
