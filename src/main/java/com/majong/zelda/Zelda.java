package com.majong.zelda;

import com.majong.zelda.advancement.TriggerRegistery;
import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.AncientArrowEntity;
import com.majong.zelda.entity.BombArrowEntity;
import com.majong.zelda.entity.ElectricityArrowEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.FireArrowEntity;
import com.majong.zelda.entity.IceArrowEntity;
import com.majong.zelda.event.EventRegistry;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.loot.AddLootTableModifier;
import com.majong.zelda.network.Networking;
import com.majong.zelda.sound.SoundLoader;
import com.majong.zelda.tileentity.TileEntityLoader;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import majongmc.hllib.common.event.EventBus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fml.config.ModConfig;

public class Zelda implements ModInitializer{
	@Override
	public void onInitialize() {
		EventBus bus=EventBus.getModEventBus();
		//bus.prependListener(this::setup);
		ForgeConfigRegistry.INSTANCE.register(Utils.MOD_ID,ModConfig.Type.COMMON, ZeldaConfig.COMMON_CONFIG);
		ForgeConfigRegistry.INSTANCE.register(Utils.MOD_ID,ModConfig.Type.CLIENT, ZeldaConfigClient.CLIENT_CONFIG);
		EntityLoader.ENTITY_TYPES.register(bus);
		EntityLoader.onAttributeCreate();
		BlockLoader.BLOCKS.register(bus);
		ItemLoader.ITEMS.register(bus);
		TileEntityLoader.TILE_ENTITIES.register(bus);
		SoundLoader.SOUNDS.register(bus);
		EventRegistry.register(bus);
		AddLootTableModifier.registeraddition();
		TriggerRegistery.register();
        this.prependCreative();
        Networking.registerServerHandler();
        this.registerBehavior();
        this.registerMobSpawn();
		//ModStructures.register(bus);
		//BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
	}
	private static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder(new ResourceLocation(Utils.MOD_ID, "creative_tab"))
			.icon(() -> new ItemStack(ItemLoader.SHIKA_STONE.get()))
			.build();
	private void prependCreative() {
		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
        	content.prepend(ItemLoader.SHIKA_STONE.get());
        	content.prepend(ItemLoader.GUIDE_BOOK.get());
        	content.prepend(ItemLoader.ANCIENT_GEAR.get());
        	content.prepend(ItemLoader.ANCIENT_CORE.get());
        	content.prepend(ItemLoader.BIG_ANCIENT_CORE.get());
        	content.prepend(ItemLoader.ANCIENT_SPRING.get());
        	content.prepend(ItemLoader.ANCIENT_SHAFT.get());
        	content.prepend(ItemLoader.ANCIENT_SCREW.get());
        	content.prepend(ItemLoader.ANCIENT_SHIELD.get());
        	content.prepend(ItemLoader.ANCIENT_ARROW.get());
        	content.prepend(ItemLoader.ELECTRICITY_ARROW.get());
        	content.prepend(ItemLoader.BOMB_ARROW.get());
        	content.prepend(ItemLoader.ICE_ARROW.get());
        	content.prepend(ItemLoader.FIRE_ARROW.get());
        	content.prepend(ItemLoader.FOOD.get());
        	content.prepend(ItemLoader.HARD_FOOD.get());
        	content.prepend(ItemLoader.HORN.get());
        	content.prepend(ItemLoader.SPIRIT_ORB.get());
        	content.prepend(ItemLoader.HEART_CONTAINER.get());
        	content.prepend(ItemLoader.ANCIENT_HORN.get());
        	content.prepend(ItemLoader.CHOPPING_WIND_BLADE.get());
        	content.prepend(ItemLoader.BEAST_GOD_SWORD.get());
        	content.prepend(ItemLoader.BEAST_GOD_BOW.get());
        	content.prepend(BlockLoader.ITEM_POT.get());
        	content.prepend(BlockLoader.ITEM_TEMPLE_ENTRY.get());
        	content.prepend(BlockLoader.ITEM_TEMPLE_START.get());
        	content.prepend(BlockLoader.ITEM_TEMPLE_FINISH.get());
        	content.prepend(ItemLoader.GUARDIAN_SPAWN_EGG.get());
        	content.prepend(ItemLoader.WALKING_GUARDIAN_SPAWN_EGG.get());
        	content.prepend(ItemLoader.MOLLY_BRIN_SPAWN_EGG.get());
        	content.prepend(ItemLoader.BOKO_BRIN_SPAWN_EGG.get());
        	content.prepend(ItemLoader.ROCK_GIANT_SPAWN_EGG.get());
        	content.prepend(ItemLoader.YIGA_TEAM_MEMBER_SPAWN_EGG.get());
        	content.prepend(ItemLoader.LYNEL_SPAWN_EGG.get());
        });
    }
	private void registerMobSpawn() {
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), EntityLoader.GUARDIAN.get().getCategory(), EntityLoader.GUARDIAN.get(), (int) (50*ZeldaConfig.GUARDIAN.get()), 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), EntityLoader.WALKING_GUARDIAN.get().getCategory(), EntityLoader.WALKING_GUARDIAN.get(), (int) (25*ZeldaConfig.WALKING_GUARDIAN.get()), 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), EntityLoader.MOLLY_BRIN.get().getCategory(), EntityLoader.MOLLY_BRIN.get(), (int) (80*ZeldaConfig.MOLLYBRIN.get()), 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), EntityLoader.BOKO_BRIN.get().getCategory(), EntityLoader.BOKO_BRIN.get(), (int) (64*ZeldaConfig.BOKOBRIN.get()), 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), EntityLoader.BOKO_BRIN.get().getCategory(), EntityLoader.BOKO_BRIN.get(), (int) (10*ZeldaConfig.BOKOBRIN.get()), 1, 1);
	}
	private void registerBehavior() {
		DispenserBlock.registerBehavior(ItemLoader.ANCIENT_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new AncientArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.ELECTRICITY_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new ElectricityArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.BOMB_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new BombArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.ICE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new IceArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
		DispenserBlock.registerBehavior(ItemLoader.FIRE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
	         protected Projectile getProjectile(Level p_123407_, Position p_123408_, ItemStack p_123409_) {
	            Arrow arrow = new FireArrowEntity(p_123407_, p_123408_.x(), p_123408_.y(), p_123408_.z());
	            arrow.pickup = AbstractArrow.Pickup.ALLOWED;
	            return arrow;
	         }
	      });
	}
}
