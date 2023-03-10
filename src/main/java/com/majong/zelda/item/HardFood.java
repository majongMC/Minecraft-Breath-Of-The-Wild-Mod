package com.majong.zelda.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HardFood extends Item{
	public HardFood() {
		super(new Properties().food(ZELDA_FOOD).stacksTo(1));
		// TODO �Զ����ɵĹ��캯�����
	}
	private static final FoodProperties ZELDA_FOOD = (new FoodProperties.Builder())
			.saturationMod(0)
			.nutrition(1)
			.alwaysEat()
            .build();
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		entityLiving.hurt(DamageSource.STARVE, 1F);
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}
