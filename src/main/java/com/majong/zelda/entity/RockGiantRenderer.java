package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
@Environment(value=EnvType.CLIENT)
public class RockGiantRenderer extends MobRenderer<RockGiantEntity,RockGiantModel>{

	public RockGiantRenderer(EntityRendererProvider.Context renderManagerIn, RockGiantModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public RockGiantRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new RockGiantModel(renderManagerIn.bakeLayer(RockGiantModel.LAYER_LOCATION)), 0.7f);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getTextureLocation(RockGiantEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/rockgianttexture.png");
	}

}
