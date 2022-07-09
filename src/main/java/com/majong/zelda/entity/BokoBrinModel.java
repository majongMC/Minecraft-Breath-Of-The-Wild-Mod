package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

public class BokoBrinModel extends PiglinModel<BokoBrinEntity>{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "boko_brin"), "main");
	public BokoBrinModel(ModelPart p_170810_) {
		super(p_170810_);
		// TODO Auto-generated constructor stub
	}
	public static LayerDefinition createBodyLayer() {
		return LayerDefinition.create(PiglinModel.createMesh(new CubeDeformation(0.0F)),64,64);
	}
}
