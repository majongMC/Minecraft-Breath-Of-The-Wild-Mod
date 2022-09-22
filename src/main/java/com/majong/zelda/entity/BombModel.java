package com.majong.zelda.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BombModel extends EntityModel<BombEntity>{
	private final ModelRenderer body;
	public BombModel() {
		this.texHeight=32;
		this.texWidth=32;
		body = new ModelRenderer(this);
		body.setPos(0,0,0);
		body.texOffs(0, 0);
		body.addBox(-4, 0, -4, 8, 8, 8);
	}
	@Override
	public void setupAnim(BombEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO 自动生成的方法存根
		body.xRot = limbSwing;
        body.yRot = netHeadYaw;
        body.zRot = headPitch;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		// TODO 自动生成的方法存根
		body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}
}
