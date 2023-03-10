package com.majong.zelda.entity.animation;

import com.majong.zelda.api.animation.KeyframeAnimations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BokoBrinAnimation {
	public static final AnimationDefinition BLEW_HORN=AnimationDefinition.Builder.withLength(2.4F)
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.29F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.29F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 22.5F, -15F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, -20F, 15F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 22.5F, -15F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, -20F, 15F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0.0F, 0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, 0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-115.0462F, 43.3422F, -13.7071F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(-115.659F, 52.3158F, -20.2158F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(-93.6773F, 58.0166F, 25.5107F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4F, KeyframeAnimations.degreeVec(-115.659F, 52.3158F, -20.2158F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(-93.6773F, 58.0166F, 25.5107F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.2F, KeyframeAnimations.degreeVec(-115.0462F, 43.3422F, -13.7071F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(3F,0F,0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.posVec(2F, 0F, -1F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.posVec(5.25F, -2F, -0.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4F, KeyframeAnimations.posVec(2F, 0F, -1F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.posVec(5.25F, -2F, -0.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.2F, KeyframeAnimations.posVec(3F,0F,0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition ATTACK=AnimationDefinition.Builder.withLength(1.2F)
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-5F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-5F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(9.1903F, -34.7989F, 4.8303F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-8.3097F, -34.7989F, 4.8303F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.degreeVec(7.5662F, -5.102F, 9.4289F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-15F, 37.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-15F, 37.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(2.3933F, -52.7953F, 22.2912F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-6.9501F, -35.8423F, 4.4654F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(9.8935F, 18.7071F, 3.2022F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-34.0724F, -8.5373F, -12.3796F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-34.0724F, -8.5373F, -12.3796F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(23.4276F, -8.5373F, -12.3796F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-19.0724F, -8.5373F, -12.3796F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(13.4276F, -8.5373F, -12.3796F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(23.3171F, -14.3704F, 97.3135F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(23.3171F, -14.3704F, 97.3135F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(-138.601F, -8.2521F, 121.0965F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-142.7619F, 21.1787F, 86.4274F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(15.5327F, -26.1062F, 37.6108F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0F,0F,0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.posVec(0F, 0F, -6F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.posVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.posVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-26.1971F, -10.5479F, -14.044F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-26.1971F, -10.5479F, -14.044F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(39.025F, -9.5766F, 11.5995F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(39.025F, -9.5766F, 11.5995F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
}
