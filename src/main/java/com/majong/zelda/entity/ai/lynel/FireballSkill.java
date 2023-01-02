package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.api.skill.Skill;
import com.majong.zelda.entity.Lynel;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;

public class FireballSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	public FireballSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}
	@Override
	public byte getID() {
		return 3;
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
		this.attackprocess=0;
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 110);
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 101);
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 112);
		this.lynel.getNavigation().stop();
	}
	@Override
	public void tick() {
		LivingEntity target=this.lynel.getTarget();
		lynel.getLookControl().setLookAt(target);
		if(attackprocess==31||attackprocess==41||attackprocess==51)
			fireball(target);
		else if(attackprocess==70)
			this.finish();
		attackprocess++;
	}
	private void fireball(LivingEntity target) {
		double rx,ry,rz;
		 rx=target.getX()-lynel.getX();
		 ry=target.getY()+1.5-lynel.getEyeY();
		 rz=target.getZ()-lynel.getZ();
		 Vec3 direction=new Vec3(rx,ry,rz).normalize();
		 lynel.playSound(SoundEvents.BLAZE_SHOOT);
		 SmallFireball fireball=new SmallFireball(lynel.level,lynel,direction.x*1.6,direction.y*1.6,direction.z*1.6);
		 fireball.setPos(fireball.getX(), this.lynel.getEyeY()-0.5, fireball.getZ());
		 lynel.level.addFreshEntity(fireball);
	}
}
