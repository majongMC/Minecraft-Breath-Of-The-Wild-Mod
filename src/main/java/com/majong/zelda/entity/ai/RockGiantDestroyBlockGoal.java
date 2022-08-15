package com.majong.zelda.entity.ai;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.api.effects.CameraShakeApi;
import com.majong.zelda.entity.RockGiantEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RockGiantDestroyBlockGoal extends Goal{
	private RockGiantEntity owner;
	private final int RANGE=4,MIN_DESTROY_BLOCKS=8;
	private long lastattacktime=0;
	private int attackprocess=0;
	public RockGiantDestroyBlockGoal(RockGiantEntity owner) {
		this.owner=owner;
	}
	@Override
	public boolean canUse() {
		// TODO �Զ����ɵķ������
		if(!owner.getServer().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
			return false;
		if(this.owner.level.getGameTime()-lastattacktime<60||!isblockenough())
			return false;
			return true;
	}
	@Override
	public void start() {
		this.attackprocess=0;
		this.owner.getEntityData().set(RockGiantEntity.KNOCK,true);
		owner.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,40,9));
	}
	@Override
	public boolean canContinueToUse() {
		if(this.attackprocess>20) {
			lastattacktime=this.owner.level.getGameTime();
			return false;
		}
		return true;
	}
	@Override
	public void stop() {
		this.attackprocess=0;
	}
	@Override
	 public void tick() {
		if(attackprocess==10) {
			this.destroy();
			this.owner.getEntityData().set(RockGiantEntity.KNOCK,false);
			owner.level.playSound(null,owner.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.BLOCKS, 10f, 1f);
			List<Player> playerlist=this.owner.level.getEntitiesOfClass(Player.class, this.owner.getBoundingBox().inflate(12));
			Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			CameraShakeApi.CameraShakeServer(player, 30);
    		}
		}
		attackprocess++;
	}
	private void destroy() {
		int X=(int) owner.getX();
		int Y=(int) owner.getY();
		int Z=(int) owner.getZ();
		Level Level=owner.level;
		for(int x=X-RANGE;x<=X+RANGE;x++) {
			for(int z=Z-RANGE;z<=Z+RANGE;z++) {
				for(int y=Y;y<=Y+RANGE;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					BlockState state=Level.getBlockState(pos);
					if(!state.isAir()&&!(state.getBlock() instanceof EntityBlock)&&state.getDestroySpeed(Level, pos)<50&&state.getDestroySpeed(Level, pos)>0) {
						Level.destroyBlock(pos, false, owner);
					}
				}
			}
		}
	}
	private boolean isblockenough() {
		int count=0;
		int X=(int) owner.getX();
		int Y=(int) owner.getY();
		int Z=(int) owner.getZ();
		Level Level=owner.level;
		for(int x=X-RANGE;x<=X+RANGE;x++) {
			for(int z=Z-RANGE;z<=Z+RANGE;z++) {
				for(int y=Y;y<=Y+RANGE;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					BlockState state=Level.getBlockState(pos);
					if(!state.isAir()&&!(state.getBlock() instanceof EntityBlock)&&state.getDestroySpeed(Level, pos)<50&&state.getDestroySpeed(Level, pos)>0) {
						count++;
					}
				}
			}
		}
		return count>=MIN_DESTROY_BLOCKS;
	}

}
