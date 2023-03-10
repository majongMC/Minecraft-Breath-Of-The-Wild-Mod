package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class MovingBlockCarrierRenderer extends EntityRenderer<MovingBlockCarrierEntity>{

	protected MovingBlockCarrierRenderer(Context p_174008_) {
		super(p_174008_);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void render(MovingBlockCarrierEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(MovingBlockCarrierEntity p_114482_) {
		// TODO Auto-generated method stub
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/laser.png");
	}

}
