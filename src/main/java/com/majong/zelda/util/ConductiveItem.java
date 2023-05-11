package com.majong.zelda.util;

import java.util.Iterator;

import com.majong.zelda.tag.ItemTag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ConductiveItem {
	public static boolean HeldConductiveItem(Player player) {
		return isConductive(player.getMainHandItem().getItem())||isConductive(player.getOffhandItem().getItem())||isArmorConductive(player);
	}
	public static boolean isConductive(Item item) {
		return item.builtInRegistryHolder().tags().anyMatch((TagKey<Item> t)->t.equals(ItemTag.CONDUCTIVE));
	}
	private static boolean isArmorConductive(Player player) {
		Iterator<ItemStack> it=player.getArmorSlots().iterator();
		while(it.hasNext()) {
			if(isConductive(it.next().getItem())) 
				return true;
		}
		return false;
	}
}
