package com.majong.zelda.entity;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class BombEntity extends PathfinderMob{
	public Player owner;
	public BombEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
		super(type, worldIn);
		this.getAttributes().getInstance(Attributes.MAX_HEALTH);
		// TODO �Զ����ɵĹ��캯�����
	}
	public void setowner(Player player) {
		this.owner=player;
	}
	public void explode(boolean windbomb) {
		if(windbomb) {
			this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3, Level.ExplosionInteraction.NONE);
		}
		else {
			if(ZeldaConfig.BOMBDESTROY.get())
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3, Level.ExplosionInteraction.TNT);
			else
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 3,Level.ExplosionInteraction.NONE);
		}
		this.discard();
	}
}
