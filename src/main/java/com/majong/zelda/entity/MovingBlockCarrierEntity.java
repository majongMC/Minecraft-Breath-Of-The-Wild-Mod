package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class MovingBlockCarrierEntity extends MobEntity{
	public static final ResourceLocation CANT_MOVE=new ResourceLocation(Utils.MOD_ID,"cant_move");
	private PlayerEntity owner;
	private Entity passenger;
	private double distance=0;
	private boolean loose=false;
	protected MovingBlockCarrierEntity(EntityType<? extends MobEntity> p_21368_, World p_21369_) {
		super(p_21368_, p_21369_);
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		this.setInvulnerable(true);
		this.setNoGravity(true);
	}
	public static boolean MoveBlock(PlayerEntity player, BlockPos pos, BlockState state) {
		if(!canMove(state,player.level,pos))
			return false;
		Vector3d headpos=new Vector3d(player.getX() ,player.getY()+1.5,player.getZ());
		Vector3d blockpos=new Vector3d(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
		MovingBlockCarrierEntity carrier=new MovingBlockCarrierEntity(EntityLoader.MOVING_BLOCK_CARRIER.get(),player.level);
		carrier.owner=player;
		carrier.distance=headpos.distanceTo(blockpos);
		carrier.setPos(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5);
		player.level.addFreshEntity(carrier);
		FallingBlockEntity block=fall(player.level, pos, state);
		block.setPos(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5);
		block.startRiding(carrier);
		carrier.passenger=block;
		return true;
	}
	private static FallingBlockEntity fall(World p_201972_, BlockPos p_201973_, BlockState p_201974_) {
	      FallingBlockEntity fallingblockentity = new FallingBlockEntity(p_201972_, (double)p_201973_.getX() + 0.5D, (double)p_201973_.getY(), (double)p_201973_.getZ() + 0.5D, p_201972_.getBlockState(p_201973_));
	      p_201972_.setBlock(p_201973_, p_201974_.getFluidState().createLegacyBlock(), 3);
	      p_201972_.addFreshEntity(fallingblockentity);
	      return fallingblockentity;
	  }
	public boolean loose(PlayerEntity player) {
		if(this.owner!=null&&this.owner!=player)
			return false;
		else {
			this.loose=true;
			this.setNoGravity(false);
			return true;
		}
	}
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
		if((owner==null||owner.isDeadOrDying()||passenger==null||!passenger.isAlive())) {
			loose(owner);
		}
		if(loose) {
			if(this.isOnGround())
				this.remove();
		}else
			performmagneticforce();
		doCollision();
		if(passenger!=null&&passenger instanceof FallingBlockEntity) {
			((FallingBlockEntity)passenger).time=1;
		}
		}
	}
	private void performmagneticforce() {
		if(owner==null)
			return;
		float yaw=owner.yHeadRot;
		float pitch=owner.xRotO;
		double sinyaw=Math.sin((yaw+90)*Math.PI/180);
		double cosyaw=Math.cos((yaw+90)*Math.PI/180);
		double sinpitch=-Math.sin(pitch*Math.PI/180);
		double cospitch=Math.cos(pitch*Math.PI/180);
		Vector3d headpos=new Vector3d(owner.getX(),owner.getY()+1.5,owner.getZ());
		double x,y,z,r;
		y=distance*sinpitch;
		r=distance*cospitch;
		x=r*cosyaw;
		z=r*sinyaw;
		Vector3d center=headpos.add(x, y, z);
		Vector3d blockpos=new Vector3d(this.getX(),this.getY(),this.getZ());
		Vector3d force=center.subtract(blockpos);
		force=force.scale(0.1);
		this.setDeltaMovement(force);
	}
	private void doCollision() {
		Vector3d vecspeed=this.getDeltaMovement();
		double speed=this.getDeltaMovement().length();
		if(speed>0.1) {
			List<LivingEntity> list=this.level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(0, 1, 0));
			if(list.isEmpty())
				return;
			Iterator<LivingEntity> it=list.iterator();
			while(it.hasNext()) {
				LivingEntity entity=it.next();
				if(entity.hurt(DamageSource.FALLING_BLOCK,(float) (10*speed))) {
					entity.setDeltaMovement(vecspeed.scale(2));
					this.setDeltaMovement(vecspeed.scale(0.5));
					return;
				}
			}
		}
	}
	private static boolean canMove(BlockState state,World level,BlockPos pos) {
		return !state.isAir()&&(!state.getBlock().hasTileEntity(state)||ZeldaConfig.CANMOVEBE.get())&&state.getDestroySpeed(level, pos)<50&&state.getDestroySpeed(level, pos)>=0&&!state.getBlock().getTags().contains(CANT_MOVE);
	}
}
