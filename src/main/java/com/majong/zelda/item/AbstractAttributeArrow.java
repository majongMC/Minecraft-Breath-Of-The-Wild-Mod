package com.majong.zelda.item;

import com.majong.zelda.Utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AbstractAttributeArrow extends ArrowItem{
	public AbstractAttributeArrow() {
		super(new Properties().tab(Utils.ZELDA_CREATIVE_TAB));
		// TODO 自动生成的构造函数存根
	}
	@Override
	public abstract AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter);
	@Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
		if(player.isCreative())
			return true;
		else
			return false;
	}
}
