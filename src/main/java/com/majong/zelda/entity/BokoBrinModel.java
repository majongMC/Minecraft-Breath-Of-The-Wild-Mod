package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.majong.zelda.api.animation.AnimatePlayer;
import com.majong.zelda.entity.animation.BokoBrinAnimation;

import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BokoBrinModel extends PiglinModel<BokoBrinEntity>{
	private ModelPart root;
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "boko_brin"), "main");
	public BokoBrinModel(ModelPart p_170810_) {
		super(p_170810_);
		this.root=p_170810_;
		// TODO Auto-generated constructor stub
	}
	public void setupAnim(BokoBrinEntity p_103366_, float p_103367_, float p_103368_, float p_103369_, float p_103370_, float p_103371_) {
		super.setupAnim(p_103366_, p_103367_, p_103368_, p_103369_, p_103370_, p_103371_);
		AnimatePlayer.animate(this, root, p_103366_.attackstate, BokoBrinAnimation.ATTACK, p_103369_);
		AnimatePlayer.animate(this, root, p_103366_.blewhornstate, BokoBrinAnimation.BLEW_HORN, p_103369_);
	}
	public static LayerDefinition createBodyLayer() {
		return LayerDefinition.create(PiglinModel.createMesh(new CubeDeformation(0.0F)),64,64);
	}
}
