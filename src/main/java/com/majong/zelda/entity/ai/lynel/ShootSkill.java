package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.api.skill.Skill;
import com.majong.zelda.entity.Lynel;
import com.majong.zelda.item.AbstractAttributeArrow;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;

public class ShootSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	public ShootSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}

	@Override
	public byte getID() {
		return 0;
	}

	@Override
	public boolean additionalCanUse() {
		LivingEntity target=this.lynel.getTarget();
		if(target==null||!target.isAlive())
			return false;
		if(!lynel.isAlive()||lynel.isdizzy()||!lynel.hasLineOfSight(target))
			return false;
		return true;
	}
	@Override
	public void start() {
		this.attackprocess=0;
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 107);
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 102);
	}
	@Override
	public boolean canContinueToUse() {
		if(lynel.getskill()!=getID())
			return false;
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
		if(attackprocess==4)
			this.lynel.level.broadcastEntityEvent(lynel,(byte) 103);
		else if(attackprocess==10)
			shoot(target);
		else if(attackprocess==30) {
			lynel.getNavigation().moveTo(target, 1.0D);
			this.finish();
		}
		attackprocess++;
	}
	private void shoot(LivingEntity target) {
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 102);
		ItemStack itemstack =this.lynel.getProjectile(ItemStack.EMPTY);
		AbstractArrow abstractarrow =((AbstractAttributeArrow)(ItemLoader.ELECTRICITY_ARROW.get())).createArrow(lynel.level, itemstack, lynel);
		AbstractArrow abstractarrow1 =((AbstractAttributeArrow)(ItemLoader.ELECTRICITY_ARROW.get())).createArrow(lynel.level, itemstack, lynel);
		AbstractArrow abstractarrow2 =((AbstractAttributeArrow)(ItemLoader.ELECTRICITY_ARROW.get())).createArrow(lynel.level, itemstack, lynel);
		double d0 = target.getX() - lynel.getX();
	      double d1 = target.getY(0.3333333333333333D) - abstractarrow.getY();
	      double d2 = target.getZ() - lynel.getZ();
	      double d3 = Math.sqrt(d0 * d0 + d2 * d2);
	      abstractarrow.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - lynel.level.getDifficulty().getId() * 4));
	      abstractarrow1.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - lynel.level.getDifficulty().getId() * 4));
	      abstractarrow2.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - lynel.level.getDifficulty().getId() * 4));
	      abstractarrow.setBaseDamage(abstractarrow.getBaseDamage()+3);
	      abstractarrow1.setBaseDamage(abstractarrow1.getBaseDamage()+3);
	      abstractarrow2.setBaseDamage(abstractarrow2.getBaseDamage()+3);
	      lynel.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (lynel.getRandom().nextFloat() * 0.4F + 0.8F));
	      lynel.level.addFreshEntity(abstractarrow);
	      lynel.level.addFreshEntity(abstractarrow1);
	      lynel.level.addFreshEntity(abstractarrow2);
	}
}
