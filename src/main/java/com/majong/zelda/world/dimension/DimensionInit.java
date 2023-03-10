package com.majong.zelda.world.dimension;

import com.majong.zelda.Utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class DimensionInit {
	public static final ResourceKey<Level> TEMPLE_DIMENSION = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Utils.MOD_ID,"temple"));
}
