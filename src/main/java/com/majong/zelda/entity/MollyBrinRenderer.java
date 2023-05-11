package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
@Environment(value=EnvType.CLIENT)
public class MollyBrinRenderer extends SkeletonRenderer{

	public MollyBrinRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton entity) {
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/molly_brin.png");
	}
}
