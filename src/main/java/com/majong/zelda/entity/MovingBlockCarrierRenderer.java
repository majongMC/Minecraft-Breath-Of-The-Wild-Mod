package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class MovingBlockCarrierRenderer extends EntityRenderer<MovingBlockCarrierEntity>{
	protected MovingBlockCarrierRenderer(EntityRendererManager p_174008_) {
		super(p_174008_);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void render(MovingBlockCarrierEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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
