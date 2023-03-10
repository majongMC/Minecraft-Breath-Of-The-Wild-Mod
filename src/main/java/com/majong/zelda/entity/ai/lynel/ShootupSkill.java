package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.api.skill.Skill;
import com.majong.zelda.api.tickutils.Delayer;
import com.majong.zelda.entity.Lynel;
import com.majong.zelda.item.AbstractAttributeArrow;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;

public class ShootupSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	public ShootupSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}

	@Override
	public byte getID() {
		return 1;
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
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 108);
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
	      abstractarrow.shoot(0,1,0, 1.6F, (float)(3 - lynel.level.getDifficulty().getId()));
	      abstractarrow1.shoot(0,1,0, 1.6F, (float)(3 - lynel.level.getDifficulty().getId()));
	      abstractarrow2.shoot(0,1,0, 1.6F, (float)(3 - lynel.level.getDifficulty().getId()));
	      abstractarrow.setBaseDamage(abstractarrow.getBaseDamage()+8);
	      abstractarrow1.setBaseDamage(abstractarrow1.getBaseDamage()+8);
	      abstractarrow2.setBaseDamage(abstractarrow2.getBaseDamage()+8);
	      lynel.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (lynel.getRandom().nextFloat() * 0.4F + 0.8F));
	      lynel.level.addFreshEntity(abstractarrow);
	      lynel.level.addFreshEntity(abstractarrow1);
	      lynel.level.addFreshEntity(abstractarrow2);
	      new Delayer<Entity>(20,abstractarrow,abstractarrow1,abstractarrow2,target) {

			@Override
			public boolean isclientside() {
				return false;
			}

			@Override
			public void finish() {
				Entity[] e=this.getParaments();
				for(int i=0;i<3;i++) {
					e[i].teleportTo(e[3].getX(), e[i].getY(), e[3].getZ());
				}
			}};
	}
}
