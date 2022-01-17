package com.majong.zelda.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LaserModel extends EntityModel<LaserEntity>{
	private final ModelRenderer body;
	public LaserModel() {
		this.textureHeight=64;
		this.textureWidth=64;
		body = new ModelRenderer(this);
		body.setRotationPoint(0,0,0);
		body.setTextureOffset(0, 0);
		body.addBox(-15, -1, -1, 30, 2, 2);
	}
	@Override
	public void setRotationAngles(LaserEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO 自动生成的方法存根
		body.rotateAngleX = limbSwing;
        body.rotateAngleY = netHeadYaw;
        body.rotateAngleZ = headPitch;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		// TODO 自动生成的方法存根
		body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

}

