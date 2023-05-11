package com.majong.zelda.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;

public class EntityRendererSubscriber {
    public static void onRegisterLayers() {
    	EntityModelLayerRegistry.registerModelLayer(GuardianModel.LAYER_LOCATION, GuardianModel::createBodyLayer);
    	EntityModelLayerRegistry.registerModelLayer(BombModel.LAYER_LOCATION, BombModel::createBodyLayer);
    	EntityModelLayerRegistry.registerModelLayer(RockGiantModel.LAYER_LOCATION, RockGiantModel::createBodyLayer);
    	EntityModelLayerRegistry.registerModelLayer(BokoBrinModel.LAYER_LOCATION, BokoBrinModel::createBodyLayer);
    	EntityModelLayerRegistry.registerModelLayer(LynelModel.LAYER_LOCATION, LynelModel::createBodyLayer);
    }
    public static void onRegisterRenderer() {
    	EntityRendererRegistry.register(EntityLoader.LASER.get(), LaserRenderer::new);
    	EntityRendererRegistry.register(EntityLoader.GUARDIAN.get(), GuardianRenderer::new);
        EntityRendererRegistry.register(EntityLoader.WALKING_GUARDIAN.get(), GuardianRenderer::new);
        EntityRendererRegistry.register(EntityLoader.ANCIENT_ARROW.get(), TippableArrowRenderer::new);
        EntityRendererRegistry.register(EntityLoader.ELECTRICITY_ARROW.get(), TippableArrowRenderer::new);
        EntityRendererRegistry.register(EntityLoader.BOMB_ARROW.get(), TippableArrowRenderer::new);
        EntityRendererRegistry.register(EntityLoader.ICE_ARROW.get(), TippableArrowRenderer::new);
        EntityRendererRegistry.register(EntityLoader.FIRE_ARROW.get(), TippableArrowRenderer::new);
        EntityRendererRegistry.register(EntityLoader.MOLLY_BRIN.get(), MollyBrinRenderer::new);
        EntityRendererRegistry.register(EntityLoader.BOMB.get(), BombRenderer::new);
        EntityRendererRegistry.register(EntityLoader.MOVING_BLOCK_CARRIER.get(), MovingBlockCarrierRenderer::new);
        EntityRendererRegistry.register(EntityLoader.ROCK_GIANT.get(), RockGiantRenderer::new);
        EntityRendererRegistry.register(EntityLoader.BOKO_BRIN.get(), BokoBrinRenderer::new);
        EntityRendererRegistry.register(EntityLoader.YIGA_TEAM_MEMBER.get(), YigaTeamMemberRenderer::new);
        EntityRendererRegistry.register(EntityLoader.LYNEL.get(), LynelRenderer::new);
    }
}
