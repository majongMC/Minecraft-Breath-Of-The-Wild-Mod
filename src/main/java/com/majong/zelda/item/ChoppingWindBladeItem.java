package com.majong.zelda.item;

import com.majong.zelda.Utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ChoppingWindBladeItem extends SwordItem{

	public ChoppingWindBladeItem() {
		super(Tiers.IRON, 3, -2.4F, (new Item.Properties()).tab(Utils.ZELDA_CREATIVE_TAB));
		// TODO Auto-generated constructor stub
	}

}
