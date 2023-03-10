package com.majong.zelda.entity.ai;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.LaserEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

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
	public boolean canUse() {
		// TODO 自动生成的方法存根
		if(isshooting)
			return true;
		if(this.attacker.level.getGameTime()-lastattacktime<60)
		return false;
		LivingEntity target=this.attacker.getTarget();
		if(target==null||!target.isAlive())
			return false;
		if(!attacker.canSee(target))
			return false;
		return true;
	}
	@Override
	public void start() {
		LivingEntity target=this.attacker.getTarget();
		this.targetentity=target;
		if(target!=null) {
			this.targetlocation[0]=target.getX();
			this.targetlocation[1]=target.getY();
			this.targetlocation[2]=target.getZ();
		}
		this.attackprocess=0;
	}
	@Override
	 public void tick() {
		attacker.getLookControl().setLookAt(targetentity.getX(),targetentity.getEyeY(), targetentity.getZ());
		 if(attackprocess<95) {
			 attackprocess++;
			 this.targetlocation[0]=targetentity.getX();
			 this.targetlocation[1]=targetentity.getY();
			 this.targetlocation[2]=targetentity.getZ();
			 List<PlayerEntity> playerlist= this.attacker.level.getEntitiesOfClass(PlayerEntity.class,this.attacker.getBoundingBox().inflate(48,48,48));
	    		Iterator<PlayerEntity> it=playerlist.iterator();
	    		while(it.hasNext()) {
	    			PlayerEntity player=(PlayerEntity) it.next();
	    			Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) player
		                    ),
		                    new ParticlePack(0,this.attacker.getX(),this.attacker.getY()+1.5,this.attacker.getZ(),this.targetlocation[0],this.targetlocation[1]+1,this.targetlocation[2]));
	    		}
		 }
		 else if(attackprocess>=95&&attackprocess<100) {
			 attackprocess++;
			 if(attackprocess==98&&targetentity instanceof PlayerEntity)
				 Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) targetentity
		                    ),
		                    new ParticlePack(6,this.attacker.getX(),this.attacker.getY()+1.5,this.attacker.getZ(),0,0,0));
		 }
		 else {
			 double rx,ry,rz,yaw,pitch;
			 rx=targetlocation[0]-this.attacker.getX();
			 ry=targetlocation[1]-this.attacker.getY();
			 rz=targetlocation[2]-this.attacker.getZ();
			 if(rx<0)
				 yaw=Math.atan(rz/rx)*180/Math.PI;
			 else
				 yaw=(Math.atan(rz/rx)*180/Math.PI+180);
			 pitch=Math.atan(ry/Math.sqrt(rz*rz+rx*rx))*180/Math.PI;
			 LaserEntity laser=new LaserEntity(EntityLoader.LASER.get(),this.attacker.level);
			 laser.setspeed((float)yaw, (float)pitch);
			 laser.setowner(attacker);
			 laser.setPos(this.attacker.getX(),this.attacker.getY()+0.5,this.attacker.getZ());
			 this.attacker.level.addFreshEntity(laser);
			 attackprocess=0;
			 lastattacktime=this.attacker.level.getGameTime();
		 }
	 }
}
