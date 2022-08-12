package com.majong.zelda.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeUtil {
	public static String getBiomeName(Biome biome,World world) {
		ResourceLocation biomeBaseKey = world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(biome);
		String key = Util.makeDescriptionId("biome", biomeBaseKey);
		return new TranslationTextComponent(key).getString();
	}
}
