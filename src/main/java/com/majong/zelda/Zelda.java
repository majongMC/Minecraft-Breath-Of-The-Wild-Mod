package com.majong.zelda;

import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.tileentity.TileEntityLoader;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
		Utils.BIOME_MODIFIER_SERIALIZERS.register(bus);
		bus.addListener(this::registerTab);
        bus.addListener(this::addCreative);
		//ModStructures.register(bus);
		//BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
	}
	private static CreativeModeTab creativeTab;
	private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == creativeTab) {
        	event.accept(ItemLoader.SHIKA_STONE.get());
        	event.accept(ItemLoader.ANCIENT_GEAR.get());
        	event.accept(ItemLoader.ANCIENT_CORE.get());
        	event.accept(ItemLoader.BIG_ANCIENT_CORE.get());
        	event.accept(ItemLoader.ANCIENT_SPRING.get());
        	event.accept(ItemLoader.ANCIENT_SHAFT.get());
        	event.accept(ItemLoader.ANCIENT_SCREW.get());
        	event.accept(ItemLoader.ANCIENT_SHIELD.get());
        	event.accept(ItemLoader.ANCIENT_ARROW.get());
        	event.accept(ItemLoader.ELECTRICITY_ARROW.get());
        	event.accept(ItemLoader.BOMB_ARROW.get());
        	event.accept(ItemLoader.ICE_ARROW.get());
        	event.accept(ItemLoader.FIRE_ARROW.get());
        	event.accept(ItemLoader.FOOD.get());
        	event.accept(ItemLoader.HARD_FOOD.get());
        	event.accept(ItemLoader.HORN.get());
        	event.accept(ItemLoader.SPIRIT_ORB.get());
        	event.accept(ItemLoader.ANCIENT_HORN.get());
        	event.accept(ItemLoader.CHOPPING_WIND_BLADE.get());
        	event.accept(ItemLoader.BEAST_GOD_SWORD.get());
        	event.accept(ItemLoader.BEAST_GOD_BOW.get());
        	event.accept(BlockLoader.ITEM_POT.get());
        	event.accept(BlockLoader.ITEM_TEMPLE_ENTRY.get());
        	event.accept(BlockLoader.ITEM_TEMPLE_START.get());
        	event.accept(BlockLoader.ITEM_TEMPLE_FINISH.get());
        	event.accept(ItemLoader.GUARDIAN_SPAWN_EGG.get());
        	event.accept(ItemLoader.WALKING_GUARDIAN_SPAWN_EGG.get());
        	event.accept(ItemLoader.MOLLY_BRIN_SPAWN_EGG.get());
        	event.accept(ItemLoader.BOKO_BRIN_SPAWN_EGG.get());
        	event.accept(ItemLoader.ROCK_GIANT_SPAWN_EGG.get());
        	event.accept(ItemLoader.YIGA_TEAM_MEMBER_SPAWN_EGG.get());
        	event.accept(ItemLoader.LYNEL_SPAWN_EGG.get());
        }
    }
    private void registerTab(CreativeModeTabEvent.Register event) {
        creativeTab = event.registerCreativeModeTab(new ResourceLocation(Utils.MOD_ID, "creative_tab"), builder ->
            builder.title(Component.translatable("itemGroup.Zelda"))
                .icon(() -> new ItemStack(ItemLoader.SHIKA_STONE.get()))
        );
    }
}
