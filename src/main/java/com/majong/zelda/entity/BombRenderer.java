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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BombRenderer extends EntityRenderer<BombEntity>{
	private EntityModel<BombEntity> BombModel;
	protected BombRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO 自动生成的构造函数存根
		BombModel=new BombModel();
	}
	@Override
    public void render(BombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.BombModel.renderType(this.getTextureLocation(entityIn)));
		this.BombModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(BombEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/bomb.png");
	}
}
