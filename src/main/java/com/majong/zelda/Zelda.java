package com.majong.zelda;

import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.item.ItemLoader;
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
		EntityLoader.ENTITY_TYPES.register(bus);
		//try{Thread.sleep(30000);}catch(Exception e) {}
		ItemLoader.ITEMS.register(bus);
		BlockLoader.BLOCKS.register(bus);
		TileEntityLoader.TILE_ENTITIES.register(bus);
		SoundLoader.SOUNDS.register(bus);
		//ModStructures.register(bus);
		//BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
		//MixinEnvironment.getDefaultEnvironment()
        //.addConfiguration("zelda.mixin.json");
		//Mixins.addConfiguration("assets/zelda/mixin.json");
	}
	/*private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
            ModStructures.setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();

        });
	}*/
}
