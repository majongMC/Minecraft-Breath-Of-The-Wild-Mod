package com.majong.zelda.entity.ai.lynel;

import java.util.UUID;

import com.majong.zelda.api.skill.Skill;
import com.majong.zelda.entity.Lynel;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CollideSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	private final AttributeModifier attributemodifier=new AttributeModifier(UUID.fromString("FB70D631-C479-E14F-8720-7202AE58CE86"), "attackknockback_modifier",5, AttributeModifier.Operation.ADDITION);
	public CollideSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}

	@Override
	public byte getID() {
		return 5;
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
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 109);
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 100);
		Vec2 direction=new Vec2((float)(target.getX()-lynel.getX()),(float)(target.getZ()-lynel.getZ())).normalized().scale(5);
		this.lynel.getNavigation().moveTo(target.getX()+direction.x,target.getY(),target.getZ()+direction.y, 1.5);
		lynel.getAttribute(Attributes.ATTACK_KNOCKBACK).addTransientModifier(attributemodifier);
	}
	@Override
	public void stop() {
		lynel.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(attributemodifier);
	}
	@Override
	public void tick() {
		if(this.attackprocess>=2&&this.attackprocess<=22)
			checkandperformattack();
		else if(this.attackprocess==23) {
			Vec3 pos=lynel.RandPosNearTarget(24);
			this.lynel.getNavigation().moveTo(pos.x,pos.y,pos.z,1.2);
		}
		else if(this.attackprocess==35) {
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
	      return 1*(this.lynel.getBbWidth() * 2.0F * this.lynel.getBbWidth() * 2.0F + p_25556_.getBbWidth());
	}
}
