package com.majong.zelda.tag;

import com.majong.zelda.Utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTypeTag {
	public static final TagKey<EntityType<?>> HAS_HEALTH_BAR =create("has_healthbar");
    public static final TagKey<EntityType<?>> ANCIENT_RESTRAINTED =create("ancient_restrainted");
    public static final TagKey<EntityType<?>> FIRE_RESTRAINTED =create("fire_restrainted");
    public static final TagKey<EntityType<?>> ELECTRICITY_INVULNERABLE =create("electricity_invulnerable");
    public static final TagKey<EntityType<?>> ICE_RESTRAINTED =create("ice_restrainted");
    private static TagKey<EntityType<?>> create(String p_215896_) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(Utils.MOD_ID,p_215896_));
     }
}
