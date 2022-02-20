package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GuardianRenderer extends MobRenderer<GuardianEntity, GuardianModel>{

	public GuardianRenderer(EntityRendererManager renderManagerIn, GuardianModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public GuardianRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GuardianModel(), 0.7F);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getTextureLocation(GuardianEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/guardian.png");
	}

}
