package com.majong.zelda;


import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.ClassExist;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Utils {
	public static final String MOD_ID="zelda";
	public static final boolean ICE_AND_FIRE_LOADED=ClassExist.isClassExist("com.github.alexthe666.iceandfire.IceAndFire");
	public static final boolean DRACONIC_EVOLUTION_LOADED=ClassExist.isClassExist("com.brandon3055.draconicevolution.DraconicEvolution");
	public static final CreativeModeTab ZELDA_CREATIVE_TAB = new CreativeModeTab("Zelda") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemLoader.SHIKA_STONE.get());
		}
    };
}
