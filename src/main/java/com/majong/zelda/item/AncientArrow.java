package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.AncientArrowEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AncientArrow extends ArrowItem{

	public AncientArrow() {
		super(new Properties().group(Utils.ZELDA_CREATIVE_TAB));
		// TODO 自动生成的构造函数存根
	}
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		ArrowEntity arrowentity = new AncientArrowEntity(worldIn, shooter);
	      arrowentity.setPotionEffect(stack);
	      return arrowentity;
	}
	@Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
		if(player.isCreative())
			return true;
		else
			return false;
	}
}
