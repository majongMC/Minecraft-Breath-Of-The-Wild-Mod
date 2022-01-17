package com.majong.zelda.entity;

import com.majong.zelda.config.ZeldaConfig;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.Explosion.Mode;

public class BombEntity extends CreatureEntity{
	public PlayerEntity owner;
	public BombEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
		// TODO 自动生成的构造函数存根
	}
	public void setowner(PlayerEntity player) {
		this.owner=player;
	}
	public void explode(boolean windbomb) {
		if(windbomb) {
			this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Mode.NONE);
		}
		else {
			if(ZeldaConfig.BOMBDESTROY.get())
				this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Mode.BREAK);
			else
				this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Mode.NONE);
		}
		this.onKillCommand();
	}
}
