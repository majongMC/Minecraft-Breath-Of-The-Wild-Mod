package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import majongmc.hllib.common.registry.DeferredRegister;
import majongmc.hllib.common.registry.RegistryObject;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class EntityLoader {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Utils.MOD_ID);
	 public static final RegistryObject<EntityType<LaserEntity>> LASER = ENTITY_TYPES.register("laser", () -> EntityType.Builder.of(LaserEntity::new, MobCategory.MISC).sized(1F, 1F).build("laser"));
	 public static final RegistryObject<EntityType<GuardianEntity>> GUARDIAN = ENTITY_TYPES.register("guardian", () -> FabricEntityTypeBuilder.Mob.createMob().spawnGroup(MobCategory.MONSTER).entityFactory(GuardianEntity::new).dimensions(EntityDimensions.fixed(1,2)).spawnRestriction(Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules).build());
	 public static final RegistryObject<EntityType<WalkingGuardianEntity>> WALKING_GUARDIAN = ENTITY_TYPES.register("walking_guardian", () -> FabricEntityTypeBuilder.Mob.createMob().spawnGroup(MobCategory.MONSTER).entityFactory(WalkingGuardianEntity::new).dimensions(EntityDimensions.fixed(1,2)).spawnRestriction(Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules).build());
	 public static final RegistryObject<EntityType<AncientArrowEntity>> ANCIENT_ARROW = ENTITY_TYPES.register("ancient_arrow", () -> EntityType.Builder.<AncientArrowEntity>of(AncientArrowEntity::new, MobCategory.MONSTER).sized(0.5F, 0.5F).clientTrackingRange(4).build("ancient_arrow"));
	 public static final RegistryObject<EntityType<ElectricityArrowEntity>> ELECTRICITY_ARROW = ENTITY_TYPES.register("electricity_arrow", () -> EntityType.Builder.<ElectricityArrowEntity>of(ElectricityArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("electricity_arrow"));
	 public static final RegistryObject<EntityType<BombArrowEntity>> BOMB_ARROW = ENTITY_TYPES.register("bomb_arrow", () -> EntityType.Builder.<BombArrowEntity>of(BombArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("bomb_arrow"));
	 public static final RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> EntityType.Builder.<IceArrowEntity>of(IceArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("ice_arrow"));
	 public static final RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("fire_arrow"));
	 public static final RegistryObject<EntityType<MollyBrinEntity>> MOLLY_BRIN = ENTITY_TYPES.register("molly_brin", () -> FabricEntityTypeBuilder.Mob.createMob().spawnGroup(MobCategory.MONSTER).entityFactory(MollyBrinEntity::new).dimensions(EntityDimensions.fixed(0.75F,2)).spawnRestriction(Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules).build());
	 public static final RegistryObject<EntityType<BombEntity>> BOMB = ENTITY_TYPES.register("bomb", () -> EntityType.Builder.of(BombEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build("bomb"));
	 public static final RegistryObject<EntityType<MovingBlockCarrierEntity>> MOVING_BLOCK_CARRIER = ENTITY_TYPES.register("moving_block_carrier", () -> EntityType.Builder.of(MovingBlockCarrierEntity::new, MobCategory.MISC).sized(0.9F, 0.1F).build("moving_block_carrier"));
	 public static final RegistryObject<EntityType<RockGiantEntity>> ROCK_GIANT = ENTITY_TYPES.register("rock_giant", () -> EntityType.Builder.of(RockGiantEntity::new, MobCategory.MONSTER).sized(3, 3).build("rock_giant"));
	 public static final RegistryObject<EntityType<BokoBrinEntity>> BOKO_BRIN = ENTITY_TYPES.register("boko_brin", () -> FabricEntityTypeBuilder.Mob.createMob().spawnGroup(MobCategory.MONSTER).entityFactory(BokoBrinEntity::new).dimensions(EntityDimensions.fixed(0.75F,2)).spawnRestriction(Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules).build());
	 public static final RegistryObject<EntityType<YigaTeamMemberEntity>> YIGA_TEAM_MEMBER = ENTITY_TYPES.register("yiga_team_member", () -> EntityType.Builder.of(YigaTeamMemberEntity::new, MobCategory.MONSTER).sized(0.75F, 2F).build("yiga_team_member"));
	 public static final RegistryObject<EntityType<Lynel>> LYNEL = ENTITY_TYPES.register("lynel", () -> EntityType.Builder.of(Lynel::new, MobCategory.MONSTER).sized(1F, 3F).build("lynel"));
	    public static void onAttributeCreate() {
		 FabricDefaultAttributeRegistry.register(GUARDIAN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.FOLLOW_RANGE, 64D).build());
		 FabricDefaultAttributeRegistry.register(WALKING_GUARDIAN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 120D).add(Attributes.ATTACK_DAMAGE,0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 64D).build());
		 FabricDefaultAttributeRegistry.register(MOLLY_BRIN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).build());
		 FabricDefaultAttributeRegistry.register(BOMB.get(), BombEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1D).build());
		 FabricDefaultAttributeRegistry.register(MOVING_BLOCK_CARRIER.get(), MovingBlockCarrierEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1024D).build());
		 FabricDefaultAttributeRegistry.register(ROCK_GIANT.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 200D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_KNOCKBACK,5D).add(Attributes.KNOCKBACK_RESISTANCE,1D).add(Attributes.FOLLOW_RANGE, 48D).build());
		 FabricDefaultAttributeRegistry.register(BOKO_BRIN.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,3D).add(Attributes.MOVEMENT_SPEED, 0.24D).add(Attributes.ATTACK_KNOCKBACK,0D).build());
		 FabricDefaultAttributeRegistry.register(YIGA_TEAM_MEMBER.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.ATTACK_DAMAGE,5D).add(Attributes.ATTACK_SPEED,2D).add(Attributes.MOVEMENT_SPEED, 0.24D).build());
		 FabricDefaultAttributeRegistry.register(LYNEL.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 500D).add(Attributes.ATTACK_DAMAGE,20D).add(Attributes.ATTACK_SPEED,4D).add(Attributes.MOVEMENT_SPEED, 0.38D).add(Attributes.FOLLOW_RANGE, 64D).build());
	    }
	 }

