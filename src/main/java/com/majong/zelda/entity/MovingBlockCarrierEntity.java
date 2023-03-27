package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.tag.BlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MovingBlockCarrierEntity extends Mob{
	private Player owner;
	private Entity passenger;
	private double distance=0;
	private boolean loose=false;
	protected MovingBlockCarrierEntity(EntityType<? extends Mob> p_21368_, Level p_21369_) {
		super(p_21368_, p_21369_);
		this.setInvulnerable(true);
		this.setNoGravity(true);
	}
	public static boolean MoveBlock(Player player, BlockPos pos, BlockState state) {
		if(!canMove(state,player.level,pos))
			return false;
		Vec3 headpos=new Vec3(player.getX() ,player.getY()+1.5,player.getZ());
		Vec3 blockpos=new Vec3(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
		MovingBlockCarrierEntity carrier=new MovingBlockCarrierEntity(EntityLoader.MOVING_BLOCK_CARRIER.get(),player.level);
		carrier.owner=player;
		carrier.distance=headpos.distanceTo(blockpos);
		carrier.setPos(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5);
		player.level.addFreshEntity(carrier);
		FallingBlockEntity block=FallingBlockEntity.fall(player.level, pos, state);
		block.setPos(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5);
		block.startRiding(carrier);
		carrier.passenger=block;
		return true;
	}
	public boolean loose(Player player) {
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
		if((owner==null||owner.isDeadOrDying()||passenger==null||passenger.isRemoved())) {
			loose(owner);
		}
		if(loose) {
			if(this.isOnGround())
				this.discard();
		}else
			performmagneticforce();
		doCollision();
		if(passenger!=null&&passenger instanceof FallingBlockEntity) {
			((FallingBlockEntity)passenger).time=0;
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
		Vec3 headpos=new Vec3(owner.getX(),owner.getY()+1.5,owner.getZ());
		double x,y,z,r;
		y=distance*sinpitch;
		r=distance*cospitch;
		x=r*cosyaw;
		z=r*sinyaw;
		Vec3 center=headpos.add(x, y, z);
		Vec3 blockpos=new Vec3(this.getX(),this.getY(),this.getZ());
		Vec3 force=center.subtract(blockpos);
		force=force.scale(0.1);
		this.setDeltaMovement(force);
	}
	private void doCollision() {
		Vec3 vecspeed=this.getDeltaMovement();
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
	private static boolean canMove(BlockState state,Level level,BlockPos pos) {
		return !state.isAir()&&(!(state.getBlock() instanceof EntityBlock)||ZeldaConfig.CANMOVEBE.get())&&state.getDestroySpeed(level, pos)<50&&state.getDestroySpeed(level, pos)>=0&&!state.getBlock().builtInRegistryHolder().tags().anyMatch((TagKey<Block> t)->t.equals(BlockTag.CANT_MOVE));
	}
}
