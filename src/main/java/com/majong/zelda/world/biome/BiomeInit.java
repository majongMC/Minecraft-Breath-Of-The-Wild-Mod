package com.majong.zelda.world.biome;

import java.util.function.Supplier;

import com.majong.zelda.Utils;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Utils.MOD_ID);

    // ���е��Զ�������Ⱥϵ
    static {
        createBiome("temple", BiomeMaker::theVoidBiome);
    }

    // ���Դ������Զ��������Ⱥϵ
    public static RegistryKey<Biome> TRMPLE_BIOME = registerBiome("temple");

    public static RegistryKey<Biome> registerBiome(String biomeName) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Utils.MOD_ID, biomeName));
    }

    public static RegistryObject<Biome> createBiome(String biomeName, Supplier<Biome> biome) {
        return BIOMES.register(biomeName, biome);
    }

    public static void registerBiomes() {
        //�����е�����Ⱥϵ����register                                          ����������Ⱥϵ����, ���ɸ���(0~100)
    	BiomeDictionary.addTypes(TRMPLE_BIOME,BiomeDictionary.Type.COLD,BiomeDictionary.Type.HOT,BiomeDictionary.Type.VOID);
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TRMPLE_BIOME, 0));
    }
}
