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
		// TODO 自动生成的构造函数存根
	}
	public PokBrinRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PokBrinModel(), 0.7f);
		this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.02F)));
		// TODO 自动生成的构造函数存根
	}
	@Override
	public ResourceLocation getEntityTexture(PokBrinEntity entity) {
		// TODO 自动生成的方法存根
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/pok_brin.png");
	}

}
