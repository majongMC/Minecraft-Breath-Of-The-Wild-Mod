package com.majong.zelda.item;

import com.majong.zelda.Utils;

import net.minecraft.world.item.ShieldItem;

public class AncientShield extends ShieldItem{

	public AncientShield() {
		super(new Properties().tab(Utils.ZELDA_CREATIVE_TAB).durability(320));
	}
}
