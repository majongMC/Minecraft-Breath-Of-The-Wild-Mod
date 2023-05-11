package com.majong.zelda.client;

import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.entity.EntityRendererSubscriber;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.network.Networking;
import com.majong.zelda.tileentity.PotTER;
import com.majong.zelda.tileentity.TileEntityLoader;

import majongmc.hllib.client.effects.CameraShakeApi;
import majongmc.hllib.common.event.EventBus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ZeldaClient implements ClientModInitializer{
	@Override
	public void onInitializeClient() {
		EventBus bus=EventBus.getModEventBus();
		ClientEventRegistry.register(bus);
		EntityRendererSubscriber.onRegisterLayers();
		EntityRendererSubscriber.onRegisterRenderer();
		CameraShakeApi.setExtent(ZeldaConfigClient.CAMERA_SHAKE.get().floatValue());
		propertyOverrideRegistry();
		BlockEntityRenderers.register(TileEntityLoader.POT_TILE_ENTITY.get(), PotTER::new);
		Networking.registerClientHandler();
		KeybindingRegistry.onClientSetup();
	}
	public void propertyOverrideRegistry() {
        	ItemProperties.register(ItemLoader.BEAST_GOD_BOW.get(), new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
                return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
            });
        	ItemProperties.register(ItemLoader.BEAST_GOD_BOW.get(), new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
        		if (p_174637_ == null) {
                    return 0.0F;
                 } else {
                    return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 20.0F;
                 }
            });
        	ItemProperties.register(ItemLoader.ANCIENT_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
                return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
             });
    }
}
