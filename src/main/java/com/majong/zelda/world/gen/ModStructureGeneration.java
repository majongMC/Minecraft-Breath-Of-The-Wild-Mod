package com.majong.zelda.world.gen;

import java.util.Set;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModStructureGeneration {
	public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        //这里是MC中的地形，你可以让你的建筑生成在想要的地形中
        //例如生成在平原：types.contains(BiomeDictionary.Type.PLAINS)
        if(!types.contains(BiomeDictionary.Type.NETHER)&&!types.contains(BiomeDictionary.Type.END)) {
            //你可以添加多个自己想要生成的建筑
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_ZELDA_TEMPLE);
        }
        //else if... 可以添加多个想要生成的地形中的建筑
    }
}
