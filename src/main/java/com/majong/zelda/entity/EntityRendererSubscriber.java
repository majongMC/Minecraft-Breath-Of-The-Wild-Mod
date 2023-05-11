package com.majong.zelda.entity;

import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value =Dist.CLIENT)
public class EntityRendererSubscriber {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GuardianModel.LAYER_LOCATION, GuardianModel::createBodyLayer);
        event.registerLayerDefinition(BombModel.LAYER_LOCATION, BombModel::createBodyLayer);
        event.registerLayerDefinition(RockGiantModel.LAYER_LOCATION, RockGiantModel::createBodyLayer);
        event.registerLayerDefinition(BokoBrinModel.LAYER_LOCATION, BokoBrinModel::createBodyLayer);
        event.registerLayerDefinition(LynelModel.LAYER_LOCATION, LynelModel::createBodyLayer);
    }
	@SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityLoader.LASER.get(), LaserRenderer::new);
        event.registerEntityRenderer(EntityLoader.GUARDIAN.get(), GuardianRenderer::new);
        event.registerEntityRenderer(EntityLoader.WALKING_GUARDIAN.get(), GuardianRenderer::new);
        event.registerEntityRenderer(EntityLoader.ANCIENT_ARROW.get(), TippableArrowRenderer::new);
        event.registerEntityRenderer(EntityLoader.ELECTRICITY_ARROW.get(), TippableArrowRenderer::new);
        event.registerEntityRenderer(EntityLoader.BOMB_ARROW.get(), TippableArrowRenderer::new);
        event.registerEntityRenderer(EntityLoader.ICE_ARROW.get(), TippableArrowRenderer::new);
        event.registerEntityRenderer(EntityLoader.FIRE_ARROW.get(), TippableArrowRenderer::new);
        event.registerEntityRenderer(EntityLoader.MOLLY_BRIN.get(), MollyBrinRenderer::new);
        event.registerEntityRenderer(EntityLoader.BOMB.get(), BombRenderer::new);
        event.registerEntityRenderer(EntityLoader.MOVING_BLOCK_CARRIER.get(), MovingBlockCarrierRenderer::new);
        event.registerEntityRenderer(EntityLoader.ROCK_GIANT.get(), RockGiantRenderer::new);
        event.registerEntityRenderer(EntityLoader.BOKO_BRIN.get(), BokoBrinRenderer::new);
        event.registerEntityRenderer(EntityLoader.YIGA_TEAM_MEMBER.get(), YigaTeamMemberRenderer::new);
        event.registerEntityRenderer(EntityLoader.LYNEL.get(), LynelRenderer::new);
    }
}
