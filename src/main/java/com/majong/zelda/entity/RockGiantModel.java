package com.majong.zelda.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RockGiantModel extends EntityModel<RockGiantEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer hand;

	public RockGiantModel() {
		texWidth = 256;
		texHeight = 128;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 0).addBox(-24.0F, -48.0F, -24.0F, 48.0F, 48.0F, 48.0F, 0.0F, false);

		hand = new ModelRenderer(this);
		hand.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(hand);
		hand.texOffs(0, 0).addBox(27.0F, -36.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
		hand.texOffs(0, 0).addBox(-50.0F, -36.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(RockGiantEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		hand.yRot=(float) (Math.PI*(netHeadYaw+6*((ageInTicks-(int)(ageInTicks))*entity.currenty+(1-ageInTicks+(int)(ageInTicks))*entity.lasty))/180);
	}
	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}