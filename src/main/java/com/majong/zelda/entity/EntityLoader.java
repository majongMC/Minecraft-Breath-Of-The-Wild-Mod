package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityLoader {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);
	 public static final RegistryObject<EntityType<LaserEntity>> LASER = ENTITY_TYPES.register("laser", () -> EntityType.Builder.of(LaserEntity::new, MobCategory.MISC).sized(1, 1).build("laser"));
	 public static final RegistryObject<EntityType<GuardianEntity>> GUARDIAN = ENTITY_TYPES.register("guardian", () -> EntityType.Builder.of(GuardianEntity::new, MobCategory.MONSTER).sized(1, 2).build("guardian"));
	 public static final RegistryObject<EntityType<WalkingGuardianEntity>> WALKING_GUARDIAN = ENTITY_TYPES.register("walking_guardian", () -> EntityType.Builder.of(WalkingGuardianEntity::new, MobCategory.MONSTER).sized(1, 2).build("walking_guardian"));
	 public static final RegistryObject<EntityType<AncientArrowEntity>> ANCIENT_ARROW = ENTITY_TYPES.register("ancient_arrow", () -> EntityType.Builder.<AncientArrowEntity>of(AncientArrowEntity::new, MobCategory.MONSTER).sized(0.5F, 0.5F).clientTrackingRange(4).build("ancient_arrow"));
	 public static final RegistryObject<EntityType<ElectricityArrowEntity>> ELECTRICITY_ARROW = ENTITY_TYPES.register("electricity_arrow", () -> EntityType.Builder.<ElectricityArrowEntity>of(ElectricityArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("electricity_arrow"));
	 public static final RegistryObject<EntityType<BombArrowEntity>> BOMB_ARROW = ENTITY_TYPES.register("bomb_arrow", () -> EntityType.Builder.<BombArrowEntity>of(BombArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("bomb_arrow"));
	 public static final RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> EntityType.Builder.<IceArrowEntity>of(IceArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("ice_arrow"));
	 public static final RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("fire_arrow"));
	 public static final RegistryObject<EntityType<MollyBrinEntity>> MOLLY_BRIN = ENTITY_TYPES.register("molly_brin", () -> EntityType.Builder.of(MollyBrinEntity::new, MobCategory.MONSTER).sized(0.75F, 2F).build("molly_brin"));
	 public static final RegistryObject<EntityType<BombEntity>> BOMB = ENTITY_TYPES.register("bomb", () -> EntityType.Builder.of(BombEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build("bomb"));
	 public static final RegistryObject<EntityType<RockGiantEntity>> ROCK_GIANT = ENTITY_TYPES.register("rock_giant", () -> EntityType.Builder.of(RockGiantEntity::new, MobCategory.MONSTER).sized(3, 3).build("rock_giant"));
	 public static final RegistryObject<EntityType<BokoBrinEntity>> BOKO_BRIN = ENTITY_TYPES.register("boko_brin", () -> EntityType.Builder.of(BokoBrinEntity::new, MobCategory.MONSTER).sized(0.75F, 2F).build("boko_brin"));
	 public static final RegistryObject<EntityType<YigaTeamMemberEntity>> YIGA_TEAM_MEMBER = ENTITY_TYPES.register("yiga_team_member", () -> EntityType.Builder.of(YigaTeamMemberEntity::new, MobCategory.MONSTER).sized(0.75F, 2F).build("yiga_team_member"));
	 @SubscribeEvent
	    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
		 event.put(GUARDIAN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
		 event.put(WALKING_GUARDIAN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 120D).add(Attributes.ATTACK_DAMAGE,0D).add(Attributes.MOVEMENT_SPEED, 0.25D).build());
		 event.put(MOLLY_BRIN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
		 event.put(BOMB.get(), BombEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1D).build());
		 event.put(ROCK_GIANT.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 200D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
		 event.put(BOKO_BRIN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.24D).build());
		 event.put(YIGA_TEAM_MEMBER.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.24D).build());
	    }
	 }

