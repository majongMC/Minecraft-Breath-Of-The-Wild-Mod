package com.majong.zelda.item;

import com.majong.zelda.sound.SoundLoader;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class RedEnvelope extends BasicItem{
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(!worldIn.isClientSide) {
			playerIn.addItem(new ItemStack(Items.GOLD_NUGGET,(int)(Math.random()*16)));
		}
		else {
			worldIn.playSound(playerIn, playerIn.blockPosition(), SoundLoader.RED_ENVELOPE.get(), SoundSource.AMBIENT, 10f, 1f);
		}
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn).split(playerIn.getItemInHand(handIn).getCount()-1));
	}
}
