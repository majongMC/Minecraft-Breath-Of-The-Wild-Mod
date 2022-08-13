package com.majong.zelda.world.biomemodifiers;

import com.majong.zelda.Utils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.RegistryObject;

public record GuardianSpawn(HolderSet<Biome> biomes, SpawnerData spawners) implements BiomeModifier {
	static RegistryObject<Codec<GuardianSpawn>> GUARDIAN_CODEC = Utils.BIOME_MODIFIER_SERIALIZERS.register("guardian_spawn", () ->
    RecordCodecBuilder.create(builder -> builder.group(
        // declare fields
        Biome.LIST_CODEC.fieldOf("biomes").forGetter(GuardianSpawn::biomes),
        SpawnerData.CODEC.fieldOf("spawners").forGetter(GuardianSpawn::spawners)
      ).apply(builder, GuardianSpawn::new)));
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		// TODO Auto-generated method stub
		if (phase == Phase.ADD && biomes.contains(biome))
	    {
	      builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, spawners);
	    }
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		// TODO Auto-generated method stub
		return GUARDIAN_CODEC.get();
	}
}