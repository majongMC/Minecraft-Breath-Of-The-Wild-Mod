package com.majong.zelda.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConductiveItem {
	public static final Collection<Item> CONDUCTIVE_ITEM=new ArrayList<>();
	public static boolean HeldConductiveItem(PlayerEntity player) {
		return isConductive(player.getHeldItemMainhand().getItem())||isConductive(player.getHeldItemOffhand().getItem())||isArmorConductive(player);
	}
	public static boolean isConductive(Item item) {
		Iterator<Item> it=CONDUCTIVE_ITEM.iterator();
		while(it.hasNext())
		{
			if(item==it.next()) 
				return true;
		}
		return false;
	}
	private static boolean isArmorConductive(PlayerEntity player) {
		Iterator<ItemStack> it=player.getArmorInventoryList().iterator();
		while(it.hasNext()) {
			if(isConductive(it.next().getItem())) 
				return true;
		}
		return false;
	}
}
