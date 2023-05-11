package com.majong.zelda.entity.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

@Environment(value=EnvType.CLIENT)
public class RockGiantAnimation {
	public static final AnimationDefinition ROCKGIANT_ATTACK_A=AnimationDefinition.Builder.withLength(2F)
			.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, -30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, 70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(0.0F, 70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, -10F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, 30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(0.0F, 70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition ROCKGIANT_ATTACK_B=AnimationDefinition.Builder.withLength(2F)
			.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, -70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, -70F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 10F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, -30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, -30F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition ROCKGIANT_KNOCK_A=AnimationDefinition.Builder.withLength(2F)
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-11.25F, 4F, 8F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-15F, 5F, 10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(0F, -3.75F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(45F, -30F, -10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(45F, -30F, -10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(45F, -30F, -10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-8F, 8F, 4F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-10F, 10F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(3.75F, 0F, -3.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(45F, -30F, -30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(45F, -30F, -30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(45F, -30F, -30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition ROCKGIANT_KNOCK_B=AnimationDefinition.Builder.withLength(2F)
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-11.25F, -4F, -8F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-15F, -5F, -10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(0F, 3.75F, -5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(45F, 30F, 10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(45F, 30F, 10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(45F, 30F, 10F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("hand", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-8F, -8F, -4F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-10F, -10F, -5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(3.75F, 0F, 3.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(45F, 30F, 30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(45F, 30F, 30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(45F, 30F, 30F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
}
