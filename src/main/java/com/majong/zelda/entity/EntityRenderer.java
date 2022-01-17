package com.majong.zelda.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value =Dist.CLIENT)
public class EntityRenderer {
	@SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.LASER.get(), (EntityRendererManager manager) -> {
            return new LaserRender(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.GUARDIAN.get(), (EntityRendererManager manager) -> {
            return new GuardianRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.WALKING_GUARDIAN.get(), (EntityRendererManager manager) -> {
            return new GuardianRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.ANCIENT_ARROW.get(), (EntityRendererManager manager) -> {
            return new TippedArrowRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.ELECTRICITY_ARROW.get(), (EntityRendererManager manager) -> {
            return new TippedArrowRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.BOMB_ARROW.get(), (EntityRendererManager manager) -> {
            return new TippedArrowRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.ICE_ARROW.get(), (EntityRendererManager manager) -> {
            return new TippedArrowRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.FIRE_ARROW.get(), (EntityRendererManager manager) -> {
            return new TippedArrowRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.MOLLY_BRIN.get(), (EntityRendererManager manager) -> {
            return new MollyBrinRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.BOMB.get(), (EntityRendererManager manager) -> {
            return new BombRenderer(manager);
	});
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.ROCK_GIANT.get(), (EntityRendererManager manager) -> {
            return new RockGiantRenderer(manager);
	});
}
}
