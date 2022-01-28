package com.majong.zelda.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.LaserEntity;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.network.Networking;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class GuardianAi extends Goal{
	private final CreatureEntity attacker;
	LivingEntity targetentity;
	double targetlocation[]=new double[] {0,0,0};
	private long lastattacktime=0;
	private int attackprocess;
	private boolean isshooting=false;
	public GuardianAi(CreatureEntity creature) {
		this.attacker = creature;
	}
	@Override
	public boolean shouldExecute() {
		// TODO 自动生成的方法存根
		if(isshooting)
			return true;
		if(this.attacker.world.getGameTime()-lastattacktime<60)
		return false;
		LivingEntity target=this.attacker.getAttackTarget();
		if(target==null||!target.isAlive())
			return false;
		if(!attacker.canEntityBeSeen(target))
			return false;
		return true;
	}
	@Override
	public void startExecuting() {
		LivingEntity target=this.attacker.getAttackTarget();
		this.targetentity=target;
		if(target!=null) {
			this.targetlocation[0]=target.getPosX();
			this.targetlocation[1]=target.getPosY();
			this.targetlocation[2]=target.getPosZ();
		}
		this.attackprocess=0;
	}
	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}
	@Override
	public void resetTask() {
		this.startExecuting();
	}
	@Override
	 public void tick() {
		 if(attackprocess<95) {
			 attackprocess++;
			 this.targetlocation[0]=targetentity.getPosX();
			 this.targetlocation[1]=targetentity.getPosY();
			 this.targetlocation[2]=targetentity.getPosZ();
			 List<PlayerEntity> playerlist= this.attacker.world.getEntitiesWithinAABB(PlayerEntity.class,this.attacker.getBoundingBox().grow(48,48,48) ,new Predicate<Object>() {

					@Override
					public boolean test(Object t) {
						// TODO 自动生成的方法存根
						if(t instanceof PlayerEntity) 
							return true;
						else
							return false;
					}});
	    		Iterator<PlayerEntity> it=playerlist.iterator();
	    		while(it.hasNext()) {
	    			PlayerEntity player=(PlayerEntity) it.next();
	    			Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) player
		                    ),
		                    new ParticlePack(0,this.attacker.getPosX(),this.attacker.getPosY()+1.5,this.attacker.getPosZ(),this.targetlocation[0],this.targetlocation[1]+1,this.targetlocation[2]));
	    		}
		 }
		 else if(attackprocess>=95&&attackprocess<100) {
			 attackprocess++;
			 if(attackprocess==98&&targetentity instanceof PlayerEntity)
				 Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) targetentity
		                    ),
		                    new ParticlePack(6,this.attacker.getPosX(),this.attacker.getPosY()+1.5,this.attacker.getPosZ(),0,0,0));
		 }
		 else {
			 double rx,ry,rz,yaw,pitch;
			 rx=targetlocation[0]-this.attacker.getPosX();
			 ry=targetlocation[1]-this.attacker.getPosY();
			 rz=targetlocation[2]-this.attacker.getPosZ();
			 if(rx<0)
				 yaw=Math.atan(rz/rx)*180/Math.PI;
			 else
				 yaw=(Math.atan(rz/rx)*180/Math.PI+180);
			 pitch=Math.atan(ry/Math.sqrt(rz*rz+rx*rx))*180/Math.PI;
			 LaserEntity laser=new LaserEntity(EntityLoader.LASER.get(),this.attacker.world);
			 laser.setspeed((float)yaw, (float)pitch);
			 laser.setowner(attacker);
			 laser.setPosition(this.attacker.getPosX(),this.attacker.getPosY()+1.5,this.attacker.getPosZ());
			 this.attacker.world.addEntity(laser);
			 attackprocess=0;
			 lastattacktime=this.attacker.world.getGameTime();
		 }
	 }
}
