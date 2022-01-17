package com.majong.zelda;

import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.tileentity.TileEntityLoader;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Zelda {
	public Zelda() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ZeldaConfig.COMMON_CONFIG);
		ItemLoader.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BlockLoader.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		TileEntityLoader.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		EntityLoader.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		SoundLoader.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
