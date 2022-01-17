package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.gui.ShikaStoneGui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShikaStone extends Item{

	public ShikaStone() {
		super(new Properties().group(Utils.ZELDA_CREATIVE_TAB).maxStackSize(1));
		// TODO 自动生成的构造函数存根
	}
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(worldIn.isRemote)
			Minecraft.getInstance().displayGuiScreen(new ShikaStoneGui());
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}
}
