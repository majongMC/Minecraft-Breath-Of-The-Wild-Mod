package com.majong.zelda.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractAttributeArrow extends ArrowItem{
	public AbstractAttributeArrow() {
		super(new Properties());
	}
	@Override
	public abstract AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter);
	@Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
		if(player.isCreative())
			return true;
		else
			return false;
	}
}
