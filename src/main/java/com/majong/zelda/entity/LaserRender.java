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
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YN.rotationDegrees(entityIn.getDataManager().get(entityIn.YAW)));
		matrixStackIn.rotate(Vector3f.ZN.rotationDegrees(entityIn.getDataManager().get(entityIn.PITCH)));
		  IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.LaserModel.getRenderType(this.getEntityTexture(entityIn)));
		  this.LaserModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
	@Override
	public ResourceLocation getEntityTexture(LaserEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/laser.png");
	}

}

