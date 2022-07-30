package com.majong.zelda.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.majong.zelda.Utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ConductiveItem {
	public static final Collection<Item> CONDUCTIVE_ITEM=new ArrayList<>();
	public static final ResourceLocation CONDUCTIVE=new ResourceLocation(Utils.MOD_ID,"conductive");
	public static boolean HeldConductiveItem(PlayerEntity player) {
		return isConductive(player.getMainHandItem().getItem())||isConductive(player.getOffhandItem().getItem())||isArmorConductive(player);
	}
	public static boolean isConductive(Item item) {
		Iterator<Item> it=CONDUCTIVE_ITEM.iterator();
		while(it.hasNext())
		{
			if(item==it.next()) 
				return true;
		}
		return item.getTags().contains(CONDUCTIVE);
	}
	private static boolean isArmorConductive(PlayerEntity player) {
		Iterator<ItemStack> it=player.getArmorSlots().iterator();
		while(it.hasNext()) {
			if(isConductive(it.next().getItem())) 
				return true;
		}
		return false;
	}
}
