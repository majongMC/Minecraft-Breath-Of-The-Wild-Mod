package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

public class GuardianModel extends CreeperModel<GuardianEntity>{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "guardian"), "main");
	public GuardianModel(ModelPart p_170524_) {
		super(p_170524_);
		// TODO Auto-generated constructor stub
	}
	public static LayerDefinition createBodyLayer() {
		return CreeperModel.createBodyLayer(new CubeDeformation(0.0F));
	}
}
