package com.majong.zelda.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
@Environment(value=EnvType.CLIENT)
public class LaserRenderer extends EntityRenderer<LaserEntity>{
	protected LaserRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}
	@Override
    public void render(LaserEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.translate(0, 0.5, 0);
		float afterpoint=partialTicks-(int)partialTicks;
		Vec3 speed=entityIn.getDeltaMovement().scale(afterpoint);
		VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.lightning());
		Matrix4f m4f=matrixStackIn.last().pose();
		entityIn.effect.render(m4f, vertexconsumer, new Vector3f((float)(entityIn.getX()+speed.x),(float)(entityIn.getY()+speed.y),(float)(entityIn.getZ()+speed.z)));
		matrixStackIn.popPose();
	}
	@Override
	public ResourceLocation getTextureLocation(LaserEntity entity) {
		return TextureAtlas.LOCATION_BLOCKS;
	}
	public boolean shouldRender(LaserEntity p_114169_, Frustum p_114170_, double p_114171_, double p_114172_, double p_114173_) {
		return true;
	}
}

