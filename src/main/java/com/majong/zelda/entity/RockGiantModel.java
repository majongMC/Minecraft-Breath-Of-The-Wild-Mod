package com.majong.zelda.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RockGiantModel extends EntityModel<RockGiantEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer hand;

	public RockGiantModel() {
		textureWidth = 256;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-24.0F, -48.0F, -24.0F, 48.0F, 48.0F, 48.0F, 0.0F, false);

		hand = new ModelRenderer(this);
		hand.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(hand);
		hand.setTextureOffset(0, 0).addBox(27.0F, -36.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
		hand.setTextureOffset(0, 0).addBox(-50.0F, -36.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(RockGiantEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		hand.rotateAngleY=(float) (Math.PI*(netHeadYaw+6*((ageInTicks-(int)(ageInTicks))*entity.currenty+(1-ageInTicks+(int)(ageInTicks))*entity.lasty))/180);
	}
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}