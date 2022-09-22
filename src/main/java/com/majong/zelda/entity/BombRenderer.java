package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BombRenderer extends EntityRenderer<BombEntity>{
	private EntityModel<BombEntity> bombmodel;
	protected BombRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		// TODO �Զ����ɵĹ��캯�����
		bombmodel=new BombModel(renderManager.bakeLayer(BombModel.LAYER_LOCATION));
	}
	@Override
    public void render(BombEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.bombmodel.renderType(this.getTextureLocation(entityIn)));
		this.bombmodel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(BombEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/bomb.png");
	}
}
