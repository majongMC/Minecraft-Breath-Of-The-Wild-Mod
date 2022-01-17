package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RockGiantRenderer extends MobRenderer<RockGiantEntity,RockGiantModel>{

	public RockGiantRenderer(EntityRendererManager renderManagerIn, RockGiantModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		// TODO 自动生成的构造函数存根
	}
	public RockGiantRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RockGiantModel(), 0.7f);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public ResourceLocation getEntityTexture(RockGiantEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/rockgianttexture.png");
	}

}
