package com.majong.zelda.item;

import com.majong.zelda.sound.SoundLoader;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class RedEnvelope extends BasicItem{
	@Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!worldIn.isClientSide) {
			playerIn.addItem(new ItemStack(Items.GOLD_NUGGET,(int)(Math.random()*16)));
		}
		else {
			worldIn.playSound(playerIn, playerIn.blockPosition(), SoundLoader.RED_ENVELOPE.get(), SoundCategory.AMBIENT, 10f, 1f);
		}
		return ActionResult.pass(playerIn.getItemInHand(handIn).split(playerIn.getItemInHand(handIn).getCount()-1));
	}
}
