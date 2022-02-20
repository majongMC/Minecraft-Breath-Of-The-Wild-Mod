package com.majong.zelda.item;

import java.util.List;

import javax.annotation.Nullable;

import com.majong.zelda.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ZeldaFood extends Item{
	private static final Food ZELDA_FOOD = (new Food.Builder())
			.saturationMod(0)
			.nutrition(0)
			.alwaysEat()
            .build();
	public ZeldaFood() {
		super(new Properties().food(ZELDA_FOOD).tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO 自动生成的构造函数存根
	}
	@Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, worldIn, tooltip, flag);
		if(stack.getTag()!=null) {
		int hunger=stack.getTag().getInt("hunger");
		float heal=stack.getTag().getFloat("heal");
		tooltip.add(new TranslationTextComponent("回复饱食度:"+hunger));
		tooltip.add(new TranslationTextComponent("回复生命值:"+heal));
		}
	}
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(stack.getTag()!=null) {
		int hunger=stack.getTag().getInt("hunger");
		float heal=stack.getTag().getFloat("heal");
		if(entityLiving instanceof PlayerEntity) {
			PlayerEntity player=(PlayerEntity) entityLiving;
			player.heal(heal);
			player.getFoodData().eat(hunger,1);
		}
		}
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}
