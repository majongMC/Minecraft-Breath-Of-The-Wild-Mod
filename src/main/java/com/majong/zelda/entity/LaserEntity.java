package com.majong.zelda.entity;

import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.event.PlayerUseShield;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.entity.EntityPredicate;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class LaserEntity extends ThrowableEntity{
	public static final DataParameter<Integer> YAW = EntityDataManager.createKey(LaserEntity.class, DataSerializers.VARINT);
	public static final DataParameter<Integer> PITCH = EntityDataManager.createKey(LaserEntity.class, DataSerializers.VARINT);
	private long spawntime=0;
	private LivingEntity owner;
	private float range=0.5F;
	public LaserEntity(EntityType<? extends ThrowableEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setNoGravity(true);
		this.setMotion(0,0,0);
		spawntime=this.world.getGameTime();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.world.isRemote) {
		if(this.world.getGameTime()-spawntime>100) {
			this.onKillCommand();
			return;
		}
		if(this.onGround)
		{
			explode();
			return;
		}
		LivingEntity target=this.world.getClosestEntityWithinAABB(LivingEntity.class,new EntityPredicate().setDistance(range), null, this.getPosX(), this.getPosY(), this.getPosZ(),this.getBoundingBox().grow(range, range, range));
		if(target!=null&&target!=owner) {
			if(target instanceof PlayerEntity) {
				trysheldreflect((PlayerEntity) target);
			}
			else
				explode();
		}
		}
		if(this.world.isRemote) {
			world.addOptionalParticle(ParticleTypes.CLOUD, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
		}
	}
	@Override
	protected void registerData() {
		// TODO 自动生成的方法存根
		this.dataManager.register(YAW, 0);
		this.dataManager.register(PITCH, 0);
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		this.dataManager.set(YAW, compound.getInt("yaw"));
		this.dataManager.set(PITCH, compound.getInt("pitch"));
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		// TODO 自动生成的方法存根
		compound.putInt("yaw", this.dataManager.get(YAW));
		compound.putInt("pitch", this.dataManager.get(PITCH));
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		// TODO 自动生成的方法存根
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	public void setspeed(float yaw,float pitch) {
		float f = 5F;
		double mz = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f / 2D;
		double mx = -(MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f) / 2D;
		double my = MathHelper.sin(pitch / 180.0F * (float) Math.PI) * f / 2D;
		Vector3d speed=new Vector3d(mx,my,mz);
		this.dataManager.set(YAW, (int)yaw);
		this.dataManager.set(PITCH, (int)pitch);
		this.setMotion(speed);
	}
	private void explode() {
		this.world.createExplosion(owner,this.getPosX(),this.getPosY(),this.getPosZ(),5,Mode.NONE);
		this.onKillCommand();
	}
	private void trysheldreflect(PlayerEntity player) {
		long respondtime=this.world.getGameTime()-PlayerUseShield.PLAYER_LAST_USE_SHIELD.get(player);
		if(ZeldaConfig.DISPLAYTIME.get())
			player.sendMessage(new TranslationTextComponent("反应时间"+respondtime), UUID.randomUUID());
		if(respondtime<=ZeldaConfig.SHIELD.get()) {
			reflect(player);
		}
		else if(DataManager.data.get(player).skill[2]>0&&player.isSneaking()) {
			reflect(player);
			DataManager.data.get(player).skill[2]--;
			DataManager.sendzeldaplayerdatapack(player);
		}
		else if(player.getHeldItemMainhand().getItem()==ItemLoader.ANCIENT_SHIELD.get()) {
			player.getHeldItemMainhand().damageItem(16, player,(entity) -> {
		         entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		      });
			reflect(player);
		}
		else if(player.getHeldItemOffhand().getItem()==ItemLoader.ANCIENT_SHIELD.get()) {
			player.getHeldItemOffhand().damageItem(16, player,(entity) -> {
		         entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		      });
			reflect(player);
		}
		else
			explode();
	}
	private void reflect(PlayerEntity player) {
		this.setowner(player);
		this.setMotion(this.getMotion().inverse());
		this.range=1.5F;
	}
	public void setowner(LivingEntity owner) {
		this.owner=owner;
	}
}
