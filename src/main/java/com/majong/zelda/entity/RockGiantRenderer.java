package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RockGiantRenderer extends MobRenderer<RockGiantEntity,RockGiantModel>{

	public RockGiantRenderer(EntityRendererManager renderManagerIn, RockGiantModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public RockGiantRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RockGiantModel(), 0.7f);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getEntityTexture(RockGiantEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/rockgianttexture.png");
	}

}
