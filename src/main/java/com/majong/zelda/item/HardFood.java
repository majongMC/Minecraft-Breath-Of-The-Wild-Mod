package com.majong.zelda.item;

import com.majong.zelda.Utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class HardFood extends Item{
	public HardFood() {
		super(new Properties().food(ZELDA_FOOD).tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO �Զ����ɵĹ��캯�����
	}
	private static final Food ZELDA_FOOD = (new Food.Builder())
			.saturationMod(0)
			.nutrition(1)
			.alwaysEat()
            .build();
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		entityLiving.hurt(DamageSource.STARVE, 1F);
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}
