package com.majong.zelda;


import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.ModCheck;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Utils {
	public static final String MOD_ID="zelda";
	public static final boolean DRACONIC_EVOLUTION_LOADED=ModCheck.isModLoaded("draconicevolution");
	public static final CreativeModeTab ZELDA_CREATIVE_TAB = new CreativeModeTab("Zelda") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemLoader.SHIKA_STONE.get());
		}
    };
}
