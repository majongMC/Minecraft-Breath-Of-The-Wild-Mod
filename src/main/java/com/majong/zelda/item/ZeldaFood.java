package com.majong.zelda.item;

import java.util.List;

import javax.annotation.Nullable;

import com.majong.zelda.Utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ZeldaFood extends Item{
	private static final FoodProperties ZELDA_FOOD = (new FoodProperties.Builder())
			.saturationMod(0)
			.nutrition(0)
			.alwaysEat()
            .build();
	public ZeldaFood() {
		super(new Properties().food(ZELDA_FOOD).tab(Utils.ZELDA_CREATIVE_TAB).stacksTo(1));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, worldIn, tooltip, flag);
		if(stack.getTag()!=null) {
		int hunger=stack.getTag().getInt("hunger");
		float heal=stack.getTag().getFloat("heal");
		tooltip.add(Component.translatable("回复饱食度："+hunger));
		tooltip.add(Component.translatable("回复生命值："+heal));
		}
	}
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		if(stack.getTag()!=null) {
		int hunger=stack.getTag().getInt("hunger");
		float heal=stack.getTag().getFloat("heal");
		if(entityLiving instanceof Player) {
			Player player=(Player) entityLiving;
			player.heal(heal);
			player.getFoodData().eat(hunger,1);
		}
		}
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}
