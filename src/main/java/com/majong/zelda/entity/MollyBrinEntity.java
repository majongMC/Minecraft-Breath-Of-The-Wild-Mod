package com.majong.zelda.entity;

import com.majong.zelda.item.ItemLoader;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MollyBrinEntity extends SkeletonEntity{
	private int type=0;
	public MollyBrinEntity(EntityType<? extends SkeletonEntity> p_i50194_1_, World p_i50194_2_) {
		super(p_i50194_1_, p_i50194_2_);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		type=compound.getInt("type");
	}
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("type", type);
	}
	@Override
	protected AbstractArrowEntity getArrow(ItemStack arrowStack, float distanceFactor) {
		switch(type) {
			case 0:return super.getArrow(new ItemStack(ItemLoader.FIRE_ARROW.get(),arrowStack.getCount()), distanceFactor);
			case 1:return super.getArrow(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),arrowStack.getCount()), distanceFactor);
			case 2:return super.getArrow(new ItemStack(ItemLoader.ICE_ARROW.get(),arrowStack.getCount()), distanceFactor);
		}
		return super.getArrow(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),arrowStack.getCount()), distanceFactor);
	}
	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
		type=(int)(Math.random()*3);
	}
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(cause.getEntity() instanceof PlayerEntity) {
			switch(type) {
			case 0:this.spawnAtLocation(new ItemStack(ItemLoader.ELECTRICITY_ARROW.get(),(int)(Math.random()*3)));break;
			case 1:this.spawnAtLocation(new ItemStack(ItemLoader.FIRE_ARROW.get(),(int)(Math.random()*3)));break;
			case 2:this.spawnAtLocation(new ItemStack(ItemLoader.ICE_ARROW.get(),(int)(Math.random()*3)));break;
			}
			this.spawnAtLocation(new ItemStack(Items.BONE,(int)(Math.random()*3)));
		}
	}
}
