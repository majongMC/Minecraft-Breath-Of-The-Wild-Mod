package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class MollyBrinRenderer extends SkeletonRenderer{

	public MollyBrinRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public ResourceLocation getTextureLocation(AbstractSkeletonEntity entity) {
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/molly_brin.png");
	}
}
