package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.util.CustomItemTier;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class BeastGodSword extends AxeItem{

	public BeastGodSword() {
		super(CustomItemTier.BEASTGOD, 16, -3.2F, (new Item.Properties()).tab(Utils.ZELDA_CREATIVE_TAB));
		// TODO Auto-generated constructor stub
	}

}
