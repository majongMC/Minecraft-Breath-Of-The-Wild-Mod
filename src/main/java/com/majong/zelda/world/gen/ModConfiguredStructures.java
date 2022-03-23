package com.majong.zelda.world.gen;

import com.majong.zelda.Utils;
import com.majong.zelda.world.structure.ModStructures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {
	/**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
     //你可以添加多个
    public static StructureFeature<?, ?> CONFIGURED_ZELDA_TEMPLE = ModStructures.ZELDA_TEMPLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TEMPLES = ModStructures.TEMPLES.get().configured(IFeatureConfig.NONE);

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        //可以继续添加多个
        Registry.register(registry, new ResourceLocation(Utils.MOD_ID, "configured_zelda_temple"), CONFIGURED_ZELDA_TEMPLE);
        Registry.register(registry, new ResourceLocation(Utils.MOD_ID, "configured_temples"), CONFIGURED_TEMPLES);
         /*
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
         //将上方的你的所有的建筑都加进来(put函数)
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.ZELDA_TEMPLE.get(), CONFIGURED_ZELDA_TEMPLE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.TEMPLES.get(), CONFIGURED_TEMPLES);
    }
}
