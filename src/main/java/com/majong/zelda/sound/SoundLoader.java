package com.majong.zelda.sound;

import com.majong.zelda.Utils;

import majongmc.hllib.common.registry.DeferredRegister;
import majongmc.hllib.common.registry.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundLoader {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Utils.MOD_ID);
	public static final RegistryObject<SoundEvent> GUARDIAN = SOUNDS.register("guardian", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "guardian")));
	public static final RegistryObject<SoundEvent> WALKING_GUARDIAN = SOUNDS.register("walking_guardian", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "walking_guardian")));
	public static final RegistryObject<SoundEvent> FIGHT = SOUNDS.register("fight", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "fight")));
	public static final RegistryObject<SoundEvent> FIGHT_ORIGINAL = SOUNDS.register("fight_original", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "fight_original")));
	public static final RegistryObject<SoundEvent> DEAD = SOUNDS.register("dead", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "dead")));
	public static final RegistryObject<SoundEvent> OBTAIN = SOUNDS.register("obtain", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "obtain")));
	public static final RegistryObject<SoundEvent> COOKING = SOUNDS.register("cooking", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "cooking")));
	public static final RegistryObject<SoundEvent> COOKING_FAILED = SOUNDS.register("cooking_failed", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "cooking_failed")));
	public static final RegistryObject<SoundEvent> WAKE_UP = SOUNDS.register("wake_up", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "wake_up")));
	public static final RegistryObject<SoundEvent> HINOX = SOUNDS.register("hinox", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "hinox")));
	public static final RegistryObject<SoundEvent> ROCK_GIANT = SOUNDS.register("rock_giant", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "rock_giant")));
	public static final RegistryObject<SoundEvent> ROCK_GIANT_DESTROY = SOUNDS.register("rock_giant_destroy", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "rock_giant_destroy")));
	public static final RegistryObject<SoundEvent> HORN = SOUNDS.register("horn", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "horn")));
	public static final RegistryObject<SoundEvent> RED_ENVELOPE = SOUNDS.register("red_envelope", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "red_envelope")));
	public static final RegistryObject<SoundEvent> RADAR = SOUNDS.register("radar", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "radar")));
	public static final RegistryObject<SoundEvent> MIPHA = SOUNDS.register("mipha", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "mipha")));
	public static final RegistryObject<SoundEvent> TEMPLE = SOUNDS.register("temple", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "temple")));
	public static final RegistryObject<SoundEvent> LYNEL_ROAR = SOUNDS.register("lynel_roar", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "lynel_roar")));
	public static final RegistryObject<SoundEvent> LYNEL_DEATH = SOUNDS.register("lynel_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "lynel_death")));
	public static final RegistryObject<SoundEvent> CHICKEN_GOD = SOUNDS.register("chicken_god", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "chicken_god")));
	public static final RegistryObject<SoundEvent> BLADE_HIT = SOUNDS.register("blade_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "blade_hit")));
	public static final RegistryObject<SoundEvent> WHOOSH_SHARP = SOUNDS.register("whoosh_sharp", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "whoosh_sharp")));
	public static final RegistryObject<SoundEvent> REFLECT = SOUNDS.register("reflect", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "reflect")));
	public static final RegistryObject<SoundEvent> WHOOSH = SOUNDS.register("whoosh", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, "whoosh")));
}
