package com.majong.zelda.api.animation;

import com.mojang.math.Vector3f;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class AnimatePlayer {
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
	public static void animate(EntityModel<?> model,ModelPart root,AnimationState p_233382_, AnimationDefinition p_233383_, float p_233384_) {
	      animate(model,root,p_233382_, p_233383_, p_233384_, 1.0F);
	   }

	  public static void animate(EntityModel<?> model,ModelPart root,AnimationState p_233386_, AnimationDefinition p_233387_, float p_233388_, float p_233389_) {
	      p_233386_.updateTime(p_233388_, p_233389_);
	      p_233386_.ifStarted((p_233392_) -> {
	         KeyframeAnimations.animate(model,root,p_233387_, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
	      });
	   }
}
