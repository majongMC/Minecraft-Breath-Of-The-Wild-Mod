package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityLoader {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);
	 public static final RegistryObject<EntityType<LaserEntity>> LASER = ENTITY_TYPES.register("laser", () -> EntityType.Builder.of(LaserEntity::new, EntityClassification.MISC).sized(1, 1).build("laser"));
	 public static final RegistryObject<EntityType<GuardianEntity>> GUARDIAN = ENTITY_TYPES.register("guardian", () -> EntityType.Builder.of(GuardianEntity::new, EntityClassification.MONSTER).sized(1, 2).build("guardian"));
	 public static final RegistryObject<EntityType<WalkingGuardianEntity>> WALKING_GUARDIAN = ENTITY_TYPES.register("walking_guardian", () -> EntityType.Builder.of(WalkingGuardianEntity::new, EntityClassification.MONSTER).sized(1, 2).build("walking_guardian"));
	 public static final RegistryObject<EntityType<AncientArrowEntity>> ANCIENT_ARROW = ENTITY_TYPES.register("ancient_arrow", () -> EntityType.Builder.<AncientArrowEntity>of(AncientArrowEntity::new, EntityClassification.MONSTER).sized(0.5F, 0.5F).clientTrackingRange(4).build("ancient_arrow"));
	 public static final RegistryObject<EntityType<ElectricityArrowEntity>> ELECTRICITY_ARROW = ENTITY_TYPES.register("electricity_arrow", () -> EntityType.Builder.<ElectricityArrowEntity>of(ElectricityArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("electricity_arrow"));
	 public static final RegistryObject<EntityType<BombArrowEntity>> BOMB_ARROW = ENTITY_TYPES.register("bomb_arrow", () -> EntityType.Builder.<BombArrowEntity>of(BombArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("bomb_arrow"));
	 public static final RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> EntityType.Builder.<IceArrowEntity>of(IceArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("ice_arrow"));
	 public static final RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("fire_arrow"));
	 public static final RegistryObject<EntityType<MollyBrinEntity>> MOLLY_BRIN = ENTITY_TYPES.register("molly_brin", () -> EntityType.Builder.of(MollyBrinEntity::new, EntityClassification.MONSTER).sized(0.75F, 2F).build("molly_brin"));
	 public static final RegistryObject<EntityType<BombEntity>> BOMB = ENTITY_TYPES.register("bomb", () -> EntityType.Builder.of(BombEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).build("bomb"));
	 public static final RegistryObject<EntityType<RockGiantEntity>> ROCK_GIANT = ENTITY_TYPES.register("rock_giant", () -> EntityType.Builder.of(RockGiantEntity::new, EntityClassification.MONSTER).sized(3, 3).build("rock_giant"));
	 public static final RegistryObject<EntityType<PokBrinEntity>> POK_BRIN = ENTITY_TYPES.register("pok_brin", () -> EntityType.Builder.of(PokBrinEntity::new, EntityClassification.MONSTER).sized(0.75F, 2F).build("pok_brin"));
	 @SubscribeEvent
	    public static void setupAttributes(FMLCommonSetupEvent event) {
		 event.enqueueWork(() -> {
			 GlobalEntityTypeAttributes.put(GUARDIAN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 40D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
			 GlobalEntityTypeAttributes.put(WALKING_GUARDIAN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 120D).add(Attributes.ATTACK_DAMAGE,0D).add(Attributes.MOVEMENT_SPEED, 0.25D).build());
			 GlobalEntityTypeAttributes.put(MOLLY_BRIN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
			 GlobalEntityTypeAttributes.put(BOMB.get(), BombEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1D).build());
			 GlobalEntityTypeAttributes.put(ROCK_GIANT.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 200D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
			 GlobalEntityTypeAttributes.put(POK_BRIN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.24D).build());
	        });
	    }
	 }
