package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class LaserRender extends EntityRenderer<LaserEntity>{
	private EntityModel<LaserEntity> LaserModel;
	protected LaserRender(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO 自动生成的构造函数存根
		LaserModel=new LaserModel();
	}
	@Override
    public void render(LaserEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(entityIn.getEntityData().get(LaserEntity.YAW)));
		matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(entityIn.getEntityData().get(LaserEntity.PITCH)));
		  IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.LaserModel.renderType(this.getTextureLocation(entityIn)));
		  this.LaserModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(LaserEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/laser.png");
	}

}

