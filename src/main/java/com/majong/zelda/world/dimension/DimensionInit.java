package com.majong.zelda.world.dimension;

import com.majong.zelda.Utils;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DimensionInit {
	public static final RegistryKey<World> TEMPLE_DIMENSION = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Utils.MOD_ID,"temple"));
}
