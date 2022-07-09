package com.majong.zelda.world.structure;

import com.majong.zelda.Utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructures {
    public static final TagKey<Structure> ZELDA_TEMPLE =create("zelda_temple");
    public static final TagKey<Structure> TEMPLES =create("temples");
    private static TagKey<Structure> create(String p_215896_) {
        return TagKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Utils.MOD_ID,p_215896_));
     }
}