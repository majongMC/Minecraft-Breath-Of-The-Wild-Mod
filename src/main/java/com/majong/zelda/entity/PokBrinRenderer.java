package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class PokBrinRenderer extends BipedRenderer<PokBrinEntity,PokBrinModel>{

	public PokBrinRenderer(EntityRendererManager renderManagerIn, PokBrinModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.02F)));
		// TODO �Զ����ɵĹ��캯�����
	}
	public PokBrinRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PokBrinModel(), 0.7f);
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.02F)));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getEntityTexture(PokBrinEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/pok_brin.png");
	}

}
