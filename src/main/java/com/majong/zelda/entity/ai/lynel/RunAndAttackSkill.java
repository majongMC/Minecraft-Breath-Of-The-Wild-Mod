package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.entity.Lynel;

import majongmc.hllib.common.skill.Skill;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class RunAndAttackSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	private boolean animnotplayed=true;
	private boolean upattacked=true;
	public RunAndAttackSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}

	@Override
	public byte getID() {
		return 6;
	}
	@Override
	public void start() {
		this.animnotplayed=true;
		this.upattacked=true;
		this.attackprocess=0;
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 101);
		Vec3 pos=lynel.RandPosNearTarget(6);
		this.lynel.getNavigation().moveTo(pos.x,pos.y,pos.z,1.2);
	}
	@Override
	public boolean additionalCanUse() {
		LivingEntity target=this.lynel.getTarget();
		if(target==null||!target.isAlive())
			return false;
		if(!lynel.isAlive()||lynel.isdizzy())
			return false;
		return true;
	}
	@Override
	public void tick() {
		LivingEntity target=this.lynel.getTarget();
		lynel.getLookControl().setLookAt(target);
		if(attackprocess==15) {
			Vec3 pos=lynel.RandPosNearTarget(24);
			this.lynel.getNavigation().moveTo(pos.x,pos.y,pos.z,1.2);
		}
		else if(attackprocess==30) {
			Vec2 direction=new Vec2((float)(target.getX()-lynel.getX()),(float)(target.getZ()-lynel.getZ())).normalized().scale(5);
			this.lynel.getNavigation().moveTo(target.getX()+direction.x,target.getY(),target.getZ()+direction.y, 1.3);
		}
		else if(attackprocess>30&&attackprocess<60) {
			checkandperformattack();
		}
		else if(this.attackprocess==60) {
			Vec3 pos=lynel.RandPosNearTarget(24);
			this.lynel.getNavigation().moveTo(pos.x,pos.y,pos.z,1.2);
		}
		else if(attackprocess==70) {
			this.finish();
		}
		attackprocess++;
	}
	private void checkandperformattack() {
		LivingEntity target=lynel.getTarget();
		double d0 = this.getAttackReachSqr(target);
		double d1=this.lynel.distanceToSqr(target);
		if(animnotplayed&&d1<49) {
			this.lynel.level.broadcastEntityEvent(lynel,(byte) 106);
			animnotplayed=false;
		}
		if(this.upattacked&&d1<d0) {
			lynel.doHurtTarget(target);
			this.upattacked=false;
		}
	}
	private double getAttackReachSqr(LivingEntity p_25556_) {
	      return 1*(this.lynel.getBbWidth() * 2.0F * this.lynel.getBbWidth() * 2.0F + p_25556_.getBbWidth());
	}
}
