package com.majong.zelda.entity.ai;

import java.util.EnumSet;

import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.RockGiantEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

public class DelayMeleeAttackGoal extends Goal{
	protected final PathfinderMob mob;
	   private final double speedModifier;
	   private final boolean followingTargetEvenIfNotSeen;
	   private Path path;
	   private double pathedTargetX;
	   private double pathedTargetY;
	   private double pathedTargetZ;
	   private int ticksUntilNextPathRecalculation;
	   private int ticksUntilNextAttack;
	   //private final int attackInterval = 20;
	   private long lastCanUseCheck;
	   private int failedPathFindingPenalty = 0;
	   private boolean canPenalize = false;
	   private int delay=-1;
	   //private boolean reverse=true;
	   private int delaytime=0,yigadelay=-1;
	   public DelayMeleeAttackGoal(PathfinderMob p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_,int delaytime) {
	      this.mob = p_i1636_1_;
	      this.speedModifier = p_i1636_2_;
	      this.followingTargetEvenIfNotSeen = p_i1636_4_;
	      this.delaytime=delaytime;
	      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	   }

	   public boolean canUse() {
	      long i = this.mob.level.getGameTime();
	      if (i - this.lastCanUseCheck < 20L) {
	         return false;
	      } else {
	         this.lastCanUseCheck = i;
	         LivingEntity livingentity = this.mob.getTarget();
	         if (livingentity == null) {
	            return false;
	         } else if (!livingentity.isAlive()) {
	            return false;
	         } else {
	           if (canPenalize) {
	               if (--this.ticksUntilNextPathRecalculation <= 0) {
	                  this.path = this.mob.getNavigation().createPath(livingentity, 0);
	                  this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
	                  return this.path != null;
	               } else {
	                  return true;
	               }
	            }
	            this.path = this.mob.getNavigation().createPath(livingentity, 0);
	            if (this.path != null) {
	               return true;
	            } else {
	               return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
	            }
	         }
	      }
	   }

	   public boolean canContinueToUse() {
	      LivingEntity livingentity = this.mob.getTarget();
	      if (livingentity == null) {
	         return false;
	      } else if (!livingentity.isAlive()) {
	         return false;
	      } else if (!this.followingTargetEvenIfNotSeen) {
	         return !this.mob.getNavigation().isDone();
	      } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
	         return false;
	      } else {
	         return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
	      }
	   }
	   public boolean requiresUpdateEveryTick() {
	        return true;
	     }
	   public void start() {
	      this.mob.getNavigation().moveTo(this.path, this.speedModifier);
	      this.mob.setAggressive(true);
	      this.ticksUntilNextPathRecalculation = 0;
	      this.ticksUntilNextAttack = 0;
	   }

	   public void stop() {
	      LivingEntity livingentity = this.mob.getTarget();
	      if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
	         this.mob.setTarget((LivingEntity)null);
	      }

	      this.mob.setAggressive(false);
	      this.mob.getNavigation().stop();
	   }

	   public void tick() {
	      LivingEntity livingentity = this.mob.getTarget();
	      this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
	      double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
	      this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
	      if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
	         this.pathedTargetX = livingentity.getX();
	         this.pathedTargetY = livingentity.getY();
	         this.pathedTargetZ = livingentity.getZ();
	         this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
	         if (this.canPenalize) {
	            this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
	            if (this.mob.getNavigation().getPath() != null) {
	            	net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
	               if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
	                  failedPathFindingPenalty = 0;
	               else
	                  failedPathFindingPenalty += 10;
	            } else {
	               failedPathFindingPenalty += 10;
	            }
	         }
	         if (d0 > 1024.0D) {
	            this.ticksUntilNextPathRecalculation += 10;
	         } else if (d0 > 256.0D) {
	            this.ticksUntilNextPathRecalculation += 5;
	         }

	         if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
	            this.ticksUntilNextPathRecalculation += 15;
	         }
	      }

	      this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
	      this.checkAndPerformAttack(livingentity, d0);
	   }

	   protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_) {
	      double d0 = this.getAttackReachSqr(p_190102_1_);
	      if (p_190102_2_ <= d0 && this.ticksUntilNextAttack <= 0) {
	         this.resetAttackCooldown();
	         delay=delaytime;
	         if(this.mob instanceof RockGiantEntity) {
	        	 this.mob.level.broadcastEntityEvent(this.mob, RockGiantEntity.ATTACK_EVENT);
		    	  
	         }else if(this.mob instanceof BokoBrinEntity) {
	        	 this.mob.level.broadcastEntityEvent(mob,BokoBrinEntity.ATTACK_EVENT);
	        	 this.mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,24,9,false,false));
	         }else if(this.mob instanceof YigaTeamMemberEntity) {
	        	 float yaw=mob.yHeadRot;
			 		float f = -4F;
			 		double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
			 		double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
			 		mob.setDeltaMovement(mob.getDeltaMovement().add(mx,0.4, mz));
			 		mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,9,9,false,false));
			 		this.mob.level.broadcastEntityEvent(mob,YigaTeamMemberEntity.ATTACK_EVENT);
	         }else
	        	 this.mob.swing(InteractionHand.MAIN_HAND);
	      }
	      if(delay==8&&this.mob instanceof BokoBrinEntity) {
	        	 if(p_190102_2_ <= d0) {
	        	 this.mob.doHurtTarget(p_190102_1_);
	        	 p_190102_1_.invulnerableTime=0;
	        	 }
	        	 float yaw=mob.yHeadRot;
		 		float f = 1F;
		 		double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
		 		double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
		 		mob.setDeltaMovement(mob.getDeltaMovement().add(mx,0, mz));
	      }
	      if(delay==10&&this.mob instanceof YigaTeamMemberEntity) {
	    	  float yaw=mob.yHeadRot;
		 		float f = 12F;
		 		double mz = Math.cos(yaw / 180.0F * (float) Math.PI) * f / 2D;
		 		double mx = -Math.sin(yaw / 180.0F * (float) Math.PI) * f / 2D;
		 		mob.setDeltaMovement(mob.getDeltaMovement().add(mx,0, mz));
	      }
	      if(delay>0&&delay<10&&yigadelay<0&&this.mob instanceof YigaTeamMemberEntity&&p_190102_2_ <= 3*d0) {
	    	  yigadelay=4;
	      }
	      if(yigadelay==0&&this.mob instanceof YigaTeamMemberEntity) {
	    	  ((YigaTeamMemberEntity)this.mob).yigadamage(p_190102_1_);
	      }
	      if(delay==0) {
	    	  if(p_190102_2_ <= d0) {
	    	  if(!(this.mob instanceof YigaTeamMemberEntity)&&!(this.mob instanceof Chicken))
	    		  this.mob.doHurtTarget(p_190102_1_);
	    	  if(this.mob instanceof Chicken) {
	    		  p_190102_1_.invulnerableTime=0;
	    		  p_190102_1_.hurt(new EntityDamageSource("chicken",mob).bypassArmor().bypassInvul(), 1);
	    		  mob.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH,1,1);
	    	  }
	    	  }
	      }
	      delay--;yigadelay--;
	   }

	   protected void resetAttackCooldown() {
	      this.ticksUntilNextAttack = 40;
	   }

	   protected boolean isTimeToAttack() {
	      return this.ticksUntilNextAttack <= 0;
	   }

	   protected int getTicksUntilNextAttack() {
	      return this.ticksUntilNextAttack;
	   }

	   protected int getAttackInterval() {
	      return 20;
	   }

	   protected double getAttackReachSqr(LivingEntity p_179512_1_) {
	      return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
	   }
}
