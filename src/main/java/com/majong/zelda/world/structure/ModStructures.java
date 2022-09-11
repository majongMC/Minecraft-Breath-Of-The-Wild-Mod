package com.majong.zelda.world.structure;

import com.majong.zelda.Utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class ModStructures {
    public static final TagKey<ConfiguredStructureFeature<?, ?>> ZELDA_TEMPLE =create("zelda_temple");
    public static final TagKey<ConfiguredStructureFeature<?, ?>> TEMPLES =create("temples");
    private static TagKey<ConfiguredStructureFeature<?, ?>> create(String p_215896_) {
        return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(Utils.MOD_ID,p_215896_));
     }
}