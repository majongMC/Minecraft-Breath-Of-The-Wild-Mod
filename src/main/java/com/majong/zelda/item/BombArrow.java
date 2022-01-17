package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.BombArrowEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BombArrow extends ArrowItem{

	public BombArrow() {
		super(new Properties().group(Utils.ZELDA_CREATIVE_TAB));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		ArrowEntity arrowentity = new BombArrowEntity(worldIn, shooter);
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
