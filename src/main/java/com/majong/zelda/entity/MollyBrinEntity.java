package com.majong.zelda.entity;

import com.majong.zelda.item.ItemLoader;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MollyBrinEntity extends Skeleton{
	private int type=0;
	public MollyBrinEntity(EntityType<? extends Skeleton> p_i50194_1_, Level p_i50194_2_) {
		super(p_i50194_1_, p_i50194_2_);
		type=(int)(Math.random()*3);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		type=compound.getInt("type");
	}
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("type", type);
	}
	@Override
	protected AbstractArrow getArrow(ItemStack arrowStack, float distanceFactor) {
		switch(type) {
			case 0:return super.getArrow(new ItemStack(ItemLoader.FIRE_ARROW.get(),arrowStack.getCount()), distanceFactor);
			case 1:return super.getArrow(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),arrowStack.getCount()), distanceFactor);
			case 2:return super.getArrow(new ItemStack(ItemLoader.ICE_ARROW.get(),arrowStack.getCount()), distanceFactor);
		}
		return super.getArrow(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),arrowStack.getCount()), distanceFactor);
	}
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof Player) {
			switch(type) {
			case 0:this.spawnAtLocation(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),(int)(Math.random()*3)));break;
			case 1:this.spawnAtLocation(new ItemStack(ItemLoader.FIRE_ARROW.get(),(int)(Math.random()*3)));break;
			case 2:this.spawnAtLocation(new ItemStack(ItemLoader.ICE_ARROW.get(),(int)(Math.random()*3)));break;
			}
			this.spawnAtLocation(new ItemStack(Items.BONE,(int)(Math.random()*3)));
		}
	}
}
