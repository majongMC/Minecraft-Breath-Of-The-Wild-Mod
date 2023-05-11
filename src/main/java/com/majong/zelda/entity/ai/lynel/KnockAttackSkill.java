package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.entity.Lynel;

import majongmc.hllib.common.skill.Skill;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class KnockAttackSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	public KnockAttackSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}
	@Override
	public byte getID() {
		return 4;
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
	public void start() {
		LivingEntity target=lynel.getTarget();
		this.attackprocess=0;
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 101);
		this.lynel.getNavigation().moveTo(target, 1.2);
	}
	@Override
	public void tick() {
		if(this.attackprocess==5)
			this.lynel.level.broadcastEntityEvent(lynel,(byte) 105);
		else if(this.attackprocess==16) {
			checkandperformattack();
		}
		else if(this.attackprocess==20) {
			Vec3 pos=lynel.RandPosNearTarget(24);
			this.lynel.getNavigation().moveTo(pos.x,pos.y,pos.z,1.2);
		}
		else if(this.attackprocess==33) {
			finish();
		}
		attackprocess++;
	}
	private void checkandperformattack() {
		LivingEntity target=lynel.getTarget();
		double d0 = this.getAttackReachSqr(target);
		double d1=this.lynel.distanceToSqr(target);
		if(d1<d0)
			lynel.doHurtTarget(target);
	}
	private double getAttackReachSqr(LivingEntity p_25556_) {
	      return 3*(this.lynel.getBbWidth() * 2.0F * this.lynel.getBbWidth() * 2.0F + p_25556_.getBbWidth());
	   }
}
