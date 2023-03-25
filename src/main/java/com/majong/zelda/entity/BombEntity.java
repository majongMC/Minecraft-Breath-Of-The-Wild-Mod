package com.majong.zelda.entity;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.Level;


public class BombEntity extends Mob{
	public Player owner;
	private int life=0;
	public BombEntity(EntityType<? extends Mob> type, Level worldIn) {
		super(type, worldIn);
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			if(life>1200)
				this.discard();
			life++;
		}
	}
	public void setowner(Player player) {
		this.owner=player;
	}
	public void explode(boolean windbomb) {
		if(windbomb) {
			this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3, BlockInteraction.NONE);
		}
		else {
			if(ZeldaConfig.BOMBDESTROY.get())
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3, BlockInteraction.BREAK);
			else
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3, BlockInteraction.NONE);
		}
		this.discard();
	}
}
