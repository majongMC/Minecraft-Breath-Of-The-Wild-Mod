package com.majong.zelda;


import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.ClassExist;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Utils {
	public static final String MOD_ID="zelda";
	public static final boolean DRACONIC_EVOLUTION_LOADED=ClassExist.isClassExist("com.brandon3055.draconicevolution.DraconicEvolution");
	public static final ItemGroup ZELDA_CREATIVE_TAB = new ItemGroup("Zelda") {
		@Override
		public ItemStack makeIcon() {
			// TODO 自动生成的方法存根
			return new ItemStack(ItemLoader.SHIKA_STONE.get());
		}
    };
}
