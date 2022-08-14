package com.majong.zelda;


import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.util.ClassExist;
import com.mojang.serialization.Codec;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Utils {
	public static final String MOD_ID="zelda";
	public static final boolean DRACONIC_EVOLUTION_LOADED=ClassExist.isClassExist("com.brandon3055.draconicevolution.DraconicEvolution");
	public static final CreativeModeTab ZELDA_CREATIVE_TAB = new CreativeModeTab("Zelda") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemLoader.SHIKA_STONE.get());
		}
    };
    public static DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
		    DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Utils.MOD_ID);
}
