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

        //������MC�еĵ��Σ����������Ľ�����������Ҫ�ĵ�����
        //����������ƽԭ��types.contains(BiomeDictionary.Type.PLAINS)
        if(!types.contains(BiomeDictionary.Type.NETHER)&&!types.contains(BiomeDictionary.Type.END)) {
            //�������Ӷ���Լ���Ҫ���ɵĽ���
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_ZELDA_TEMPLE);
        }
        //else if... ������Ӷ����Ҫ���ɵĵ����еĽ���
    }
}
