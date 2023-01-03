package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LynelRenderer extends MobRenderer<Lynel,LynelModel>{

	public LynelRenderer(Context renderManagerIn) {
		super(renderManagerIn, new LynelModel(renderManagerIn.bakeLayer(LynelModel.LAYER_LOCATION)), 1f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getTextureLocation(Lynel p_114482_) {
		// TODO Auto-generated method stub
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/lynel.png");
	}

}
