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
		super(new Properties().food(ZELDA_FOOD).group(Utils.ZELDA_CREATIVE_TAB).maxStackSize(1));
		// TODO 自动生成的构造函数存根
	}
	private static final Food ZELDA_FOOD = (new Food.Builder())
			.saturation(0)
			.hunger(1)
			.setAlwaysEdible()
            .build();
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		entityLiving.attackEntityFrom(DamageSource.STARVE, 1F);
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
