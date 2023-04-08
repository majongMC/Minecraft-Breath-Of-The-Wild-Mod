package com.majong.zelda;

import com.majong.zelda.advancement.TriggerRegistery;
import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.loot.AddLootTableModifier;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.tileentity.TileEntityLoader;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Zelda {
	public Zelda() {
		IEventBus bus=FMLJavaModLoadingContext.get().getModEventBus();
		//bus.addListener(this::setup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ZeldaConfig.COMMON_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ZeldaConfigClient.CLIENT_CONFIG);
		EntityLoader.ENTITY_TYPES.register(bus);
		ItemLoader.ITEMS.register(bus);
		BlockLoader.BLOCKS.register(bus);
		TileEntityLoader.TILE_ENTITIES.register(bus);
		SoundLoader.SOUNDS.register(bus);
		AddLootTableModifier.LOOT_MODIFIERS.register(bus);
		TriggerRegistery.register();
		//ModStructures.register(bus);
		//BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
	}
	/*private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
            ModStructures.setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();

        });
	}*/
}
