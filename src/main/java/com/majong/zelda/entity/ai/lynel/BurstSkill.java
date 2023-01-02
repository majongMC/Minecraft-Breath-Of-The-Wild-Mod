package com.majong.zelda.entity.ai.lynel;

import com.majong.zelda.api.effects.CameraShakeApi;
import com.majong.zelda.api.skill.Skill;
import com.majong.zelda.entity.Lynel;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion.BlockInteraction;

public class BurstSkill extends Skill{
	private final Lynel lynel;
	private int attackprocess=0;
	public BurstSkill(Lynel lynel) {
		super(lynel);
		this.lynel=lynel;
	}

	@Override
	public byte getID() {
		return 2;
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
		this.lynel.level.broadcastEntityEvent(lynel,(byte) 101);
		this.lynel.getNavigation().moveTo(lynel.getTarget(), 1.2);
	}
	@Override
	public void tick() {
		if(this.attackprocess==10) {
			this.lynel.level.broadcastEntityEvent(lynel,(byte) 104);
			this.lynel.playSound(SoundLoader.LYNEL_ROAR.get());
			this.lynel.getNavigation().stop();
			this.lynel.level.broadcastEntityEvent(lynel,(byte) 111);
		}
		else if(this.attackprocess==49)
			burst();
		else if(this.attackprocess==70)
			this.finish();
		attackprocess++;
	}
	private void burst() {
		lynel.level.explode(lynel,lynel.getX(),lynel.getY(),lynel.getZ(),8,true,BlockInteraction.NONE);
		Player player= lynel.level.getNearestPlayer(lynel, 24);
		if(player!=null)
			CameraShakeApi.CameraShakeServer(player, (int) (120-5*lynel.distanceTo(player)));
	}
}
