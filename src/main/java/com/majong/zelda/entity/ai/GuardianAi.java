package com.majong.zelda.entity.ai;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.LaserEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class GuardianAi extends Goal{
	private final PathfinderMob attacker;
	LivingEntity targetentity;
	double targetlocation[]=new double[] {0,0,0};
	private long lastattacktime=0;
	private int attackprocess;
	//private boolean isshooting=false;
	public GuardianAi(PathfinderMob creature) {
		this.attacker = creature;
	}
	@Override
	public boolean canUse() {
		// TODO �Զ����ɵķ������
		/*if(isshooting)
			return true;*/
		if(this.attacker.level.getGameTime()-lastattacktime<60)
		return false;
		LivingEntity target=this.attacker.getTarget();
		if(target==null||!target.isAlive())
			return false;
		if(!attacker.hasLineOfSight(target))
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
	/*@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}
	@Override
	public void stop() {
		this.start();
	}*/
	public boolean requiresUpdateEveryTick() {
        return true;
     }
	@Override
	 public void tick() {
		attacker.getLookControl().setLookAt(targetentity);
		 if(attackprocess<95) {
			 attackprocess++;
			 this.targetlocation[0]=targetentity.getX();
			 this.targetlocation[1]=targetentity.getY();
			 this.targetlocation[2]=targetentity.getZ();
			 List<Player> playerlist= this.attacker.level.getEntitiesOfClass(Player.class,this.attacker.getBoundingBox().inflate(48,48,48));
	    		Iterator<Player> it=playerlist.iterator();
	    		while(it.hasNext()) {
	    			Player player=(Player) it.next();
	    			Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayer) player
		                    ),
		                    new ParticlePack(0,this.attacker.getX(),this.attacker.getY()+1.5,this.attacker.getZ(),this.targetlocation[0],this.targetlocation[1]+1,this.targetlocation[2]));
	    		}
		 }
		 else if(attackprocess>=95&&attackprocess<100) {
			 attackprocess++;
			 if(attackprocess==98&&targetentity instanceof Player)
				 Networking.PARTICLE.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayer) targetentity
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
