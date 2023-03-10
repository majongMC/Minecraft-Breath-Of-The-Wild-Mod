package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.api.skill.ISkillable;
import com.majong.zelda.api.tickutils.Processable;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.entity.ai.ChangeDistanceNearestAttackableTargetGoal;
import com.majong.zelda.entity.ai.lynel.BurstSkill;
import com.majong.zelda.entity.ai.lynel.CollideSkill;
import com.majong.zelda.entity.ai.lynel.FireballSkill;
import com.majong.zelda.entity.ai.lynel.KnockAttackSkill;
import com.majong.zelda.entity.ai.lynel.RunAndAttackSkill;
import com.majong.zelda.entity.ai.lynel.ShootSkill;
import com.majong.zelda.entity.ai.lynel.ShootupSkill;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ShriekParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
/**事件列表：
 * <p>100~103：改变手持物品（将held_item变量改为事件ID-100）</p>
 * <p>104~110：播放动画（根据AnimationState定义顺序确定，除了deathAnimationState）</p>
 * <p>111~112：粒子事件（分别为地爆天星的蓄力阶段粒子和喷火球蓄力阶段的粒子）</p>
 * <p>113：击晕事件</p>
 * */
public class Lynel extends Monster implements ISkillable{
	public byte held_item=0;
	private byte skill=0;
	private int deathtime_a=0;
	private int dizzytime=-1;
	public AnimationState deathAnimationState = new AnimationState();
	public AnimationState burstAnimationState = new AnimationState();
	public AnimationState knock1AnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState shootupAnimationState = new AnimationState();
	public AnimationState collideAnimationState = new AnimationState();
	public AnimationState fireballAnimationState = new AnimationState();
	public Lynel(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
	    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
	}
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new ShootSkill(this));
		this.goalSelector.addGoal(3, new ShootupSkill(this));
		this.goalSelector.addGoal(4, new BurstSkill(this));
		this.goalSelector.addGoal(5, new FireballSkill(this));
		this.goalSelector.addGoal(6, new KnockAttackSkill(this));
		this.goalSelector.addGoal(7, new CollideSkill(this));
		this.goalSelector.addGoal(8, new RunAndAttackSkill(this));
		this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 0.4D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new ChangeDistanceNearestAttackableTargetGoal<>(this, Player.class,1, true,false,64));
	}
	@Override
	public void handleEntityEvent(byte eventID) {
		switch(eventID) {
		case 104:this.burstAnimationState.start(this.tickCount);break;
		case 105:this.knock1AnimationState.start(this.tickCount);break;
		case 106:this.attackAnimationState.start(this.tickCount);break;
		case 107:this.shootAnimationState.start(this.tickCount);break;
		case 108:this.shootupAnimationState.start(this.tickCount);break;
		case 109:this.collideAnimationState.start(this.tickCount);break;
		case 110:this.fireballAnimationState.start(this.tickCount);break;
		case 111:prepareburst();break;
		case 112:preparefireball();break;
		case 113:stopanimexceptdeath();;break;
		default:
			if(eventID>=100&&eventID<=103)
				this.held_item=(byte) (eventID-100);
			else
				super.handleEntityEvent(eventID);
		}
	}
	public void dizzy() {
		level.broadcastEntityEvent(this,(byte) 113);
		this.dizzytime=120;
	}
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			dizzytime--;
			if(dizzytime==0) {
				List<Entity> passengers=this.getPassengers();
				Iterator<Entity> it=passengers.iterator();
				while(it.hasNext())
					it.next().stopRiding();
			}
		}
	}
	public boolean isdizzy() {
		return this.dizzytime>0;
	}
	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if(!this.level.isClientSide&&this.isdizzy()&&!this.isPassenger())
			player.startRiding(this);
	    return super.mobInteract(player, hand);
	}
	@Override
	public void positionRider(Entity entity) {
		if (this.hasPassenger(entity)) {
	         double d0 = this.getY() + 1;
	         double sita=yHeadRot/180*Math.PI-Math.PI/2;
	         double rou=0.8;
	         entity.setPos(this.getX()+rou*Math.cos(sita), d0, this.getZ()+rou*Math.sin(sita));
	      }
	}
	@Override
	public boolean canRiderInteract() {
		return true;
	}
	private void prepareburst() {
		new Processable<Lynel>(this) {
			@Override
			public boolean isclientside() {
				return true;
			}
			@Override
			public void tick() {
				super.tick();
				Lynel lynel=this.getParaments()[0];
				int process=this.getProcess();
				for(int i=0;i<36;i++) {
					int rou=4;
					double sita=i*Math.PI/18+process*Math.PI/180;
					lynel.level.addAlwaysVisibleParticle(ParticleTypes.CLOUD, lynel.getX()+rou*Math.cos(sita), lynel.getY(), lynel.getZ()+rou*Math.sin(sita),1.6*Math.cos(sita+Math.PI/2), 1, 1.6*Math.sin(sita+Math.PI/2));
				}
				if(this.getProcess()%2==0)
					lynel.level.addAlwaysVisibleParticle(new ShriekParticleOption(0), lynel.getX(), lynel.getY()+3, lynel.getZ(), 0, 0.4, 0);
				if(this.getProcess()>32)
					this.remove();
			}
			};
	}
	private void preparefireball() {
		new Processable<Lynel>(this) {
			@Override
			public boolean isclientside() {
				return true;
			}
			@Override
			public void tick() {
				super.tick();
				Lynel lynel=this.getParaments()[0];
				for(int i=0;i<18;i++) {
					int r=1;
					double fai=i*Math.PI/18;
					double sita=lynel.yHeadRot/180*Math.PI;
					lynel.level.addAlwaysVisibleParticle(ParticleTypes.FLAME, lynel.getX()+r*Math.sin(fai)*Math.cos(sita+Math.PI), lynel.getEyeY()+r*Math.cos(fai), lynel.getZ()+r*Math.sin(fai)*Math.sin(sita+Math.PI),-0.1*r*Math.sin(fai)*Math.cos(sita+Math.PI), -0.1*r*Math.cos(fai), -0.1*r*Math.sin(fai)*Math.sin(sita+Math.PI));
					lynel.level.addAlwaysVisibleParticle(ParticleTypes.FLAME, lynel.getX()+r*Math.sin(fai)*Math.cos(sita), lynel.getEyeY()+r*Math.cos(fai), lynel.getZ()+r*Math.sin(fai)*Math.sin(sita),-0.1*r*Math.sin(fai)*Math.cos(sita), -0.1*r*Math.cos(fai), -0.1*r*Math.sin(fai)*Math.sin(sita));
				}
				if(this.getProcess()>50)
					this.remove();
			}
			};
	}
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(this.level.isClientSide) {
			this.stopanimexceptdeath();
			this.deathAnimationState.start(this.tickCount);
			EntitySpottedEvent.SoundRemainTime=0;
			ClientUtils.ClientStopSound();
		}
	}
	@Override
	protected void tickDeath() {
	      ++this.deathtime_a;
	      if(this.deathtime_a==1&& !this.level.isClientSide())
	    	  this.playSound(SoundLoader.LYNEL_DEATH.get());
	      if (this.deathtime_a == 180 && !this.level.isClientSide()) {
	         this.level.broadcastEntityEvent(this, (byte)60);
	         this.remove(Entity.RemovalReason.KILLED);
	      }

	   }
	@Override
	public boolean removeWhenFarAway(double p_27598_) {
	      return false;
	}
	@Override
	public ItemStack getProjectile(ItemStack p_33038_) {
		return new ItemStack(ItemLoader.ELECTRICITY_ARROW.get());
	}
	@Override
	public byte getskill() {
		return skill;
	}
	@Override
	public byte nextskill(byte currentskill) {
		if(this.isPassenger()&&currentskill!=2)
			return 2;
		if(shouldremoteattack())
			return allocateremoteskill(currentskill);
		else
			return allocatemeleeskill(currentskill);
	}
	private boolean shouldremoteattack() {
		if(this.distanceTo(this.getTarget())>36) 
			return true;
		Path path = this.getNavigation().createPath(this.getTarget(), 0);
		if(path==null) {
			if(this.isOnGround())
				return true;
			else
				return false;
		}
		Node finalPathPoint = path.getEndNode();
		if(this.isOnGround()&&(finalPathPoint==null||this.getTarget().distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) > 1.5))
			return true;
		return false;
	}
	private byte allocateremoteskill(byte currentskill) {
		byte skill=(byte) Mth.nextInt(random, 0, 1);
		if(skill!=currentskill)
			return skill;
		else
			return allocateremoteskill(currentskill);
	}
	private byte allocatemeleeskill(byte currentskill) {
		byte skill=(byte) Mth.nextInt(random, 2, 6);
		if(skill!=currentskill)
			return skill;
		else
			return allocatemeleeskill(currentskill);
	}
	@Override
	public boolean fireImmune() {
		return true;
	}
	@Override
	public void setskill(byte skill) {
		this.skill=skill;
	}
	public Vec3 RandPosNearTarget(double distance) {
		double sita=Math.random()*2*Math.PI;
		return new Vec3(getTarget().getX()+distance*Math.cos(sita),getTarget().getY(),getTarget().getZ()+distance*Math.sin(sita));
	}
	private void stopanimexceptdeath() {
		burstAnimationState.stop();
		knock1AnimationState.stop();
		attackAnimationState.stop();
		shootAnimationState.stop();
		shootupAnimationState.stop();
		collideAnimationState.stop();
		fireballAnimationState.stop();
	}
}
