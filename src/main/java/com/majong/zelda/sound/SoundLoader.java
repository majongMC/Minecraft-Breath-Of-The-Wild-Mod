package com.majong.zelda.sound;

import com.majong.zelda.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundLoader {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);
	public static final RegistryObject<SoundEvent> GUARDIAN = SOUNDS.register("guardian", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "guardian")));
	public static final RegistryObject<SoundEvent> WALKING_GUARDIAN = SOUNDS.register("walking_guardian", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "walking_guardian")));
	public static final RegistryObject<SoundEvent> FIGHT = SOUNDS.register("fight", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "fight")));
	public static final RegistryObject<SoundEvent> DEAD = SOUNDS.register("dead", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "dead")));
	public static final RegistryObject<SoundEvent> OBTAIN = SOUNDS.register("obtain", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "obtain")));
	public static final RegistryObject<SoundEvent> COOKING = SOUNDS.register("cooking", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "cooking")));
	public static final RegistryObject<SoundEvent> COOKING_FAILED = SOUNDS.register("cooking_failed", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "cooking_failed")));
	public static final RegistryObject<SoundEvent> WAKE_UP = SOUNDS.register("wake_up", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "wake_up")));
	public static final RegistryObject<SoundEvent> SINOX = SOUNDS.register("sinox", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "sinox")));
	public static final RegistryObject<SoundEvent> ROCK_GIANT = SOUNDS.register("rock_giant", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "rock_giant")));
	public static final RegistryObject<SoundEvent> HORN = SOUNDS.register("horn", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "horn")));
	public static final RegistryObject<SoundEvent> RED_ENVELOPE = SOUNDS.register("red_envelope", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "red_envelope")));
}
