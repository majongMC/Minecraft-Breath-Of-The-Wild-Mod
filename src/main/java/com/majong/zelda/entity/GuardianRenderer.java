package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GuardianRenderer extends MobRenderer<GuardianEntity, GuardianModel>{

	public GuardianRenderer(EntityRendererManager renderManagerIn, GuardianModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		// TODO 自动生成的构造函数存根
	}
	public GuardianRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GuardianModel(), 0.7F);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public ResourceLocation getTextureLocation(GuardianEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/guardian.png");
	}

}
