package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LaserRenderer extends EntityRenderer<LaserEntity>{
	private EntityModel<LaserEntity> lasermodel;
	protected LaserRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		// TODO �Զ����ɵĹ��캯�����
		lasermodel=new LaserModel(renderManager.bakeLayer(LaserModel.LAYER_LOCATION));
	}
	@Override
    public void render(LaserEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(entityIn.getEntityData().get(LaserEntity.YAW)));
		matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(entityIn.getEntityData().get(LaserEntity.PITCH)));
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.lasermodel.renderType(this.getTextureLocation(entityIn)));
		this.lasermodel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(LaserEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/laser.png");
	}

}

