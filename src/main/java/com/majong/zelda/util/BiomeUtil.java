package com.majong.zelda.util;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class BiomeUtil {
	public static String getBiomeName(Biome biome,Level world) {
		ResourceLocation biomeBaseKey = world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(biome);
		String key = Util.makeDescriptionId("biome", biomeBaseKey);
		return new TranslatableComponent(key).getString();
	}
}
