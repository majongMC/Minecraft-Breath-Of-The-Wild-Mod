package com.majong.zelda.entity.animation;

import com.majong.zelda.api.animation.KeyframeAnimations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class LynelAnimation {
	public static final AnimationDefinition BURST=AnimationDefinition.Builder.withLength(5F)
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6F, KeyframeAnimations.posVec(0.0F, 5F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
			.addAnimation("leg_lf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6F, KeyframeAnimations.posVec(0.0F, 5F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(42.5F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(42.5F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.degreeVec(59.8743F, 29.7958F, 30.8637F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(59.8743F, 29.7958F, 30.8637F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-58.7087F, 61.0427F, 41.8167F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-58.7087F, 61.0427F, 41.8167F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.6F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.degreeVec(-39.797F, 4.8123F, -13.6433F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-39.797F, 4.8123F, -13.6433F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-6.0004F, -84.4113F, -116.6087F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-6.0004F, -84.4113F, -116.6087F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.6F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.degreeVec(-38.8509F, -15.2903F, 19.8991F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-38.8509F, -15.2903F, 19.8991F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-45F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-45F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.2F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.6F, KeyframeAnimations.degreeVec(-20F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.degreeVec(12.5F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(12.5F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 2F, -3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6F, KeyframeAnimations.posVec(0.0F, 2F, -3F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.88F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
					))
			.build();
	public static final AnimationDefinition DEATH=AnimationDefinition.Builder.withLength(9F)
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(30F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.52F, KeyframeAnimations.degreeVec(30F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-71.7796F, 61.1694F, -26.9969F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-144.0307F, 38.7548F, -113.1612F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(-50.8975F, 53.8226F, -2.3939F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3F, KeyframeAnimations.degreeVec(-142.7863F, 40.757F, -111.2133F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-71.7796F, 61.1694F, -26.9969F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-73.4721F, -60.5555F, 23.5865F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-31.8999F, -26.9144F, -31.8561F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(-133.8448F, -49.2043F, 94.1615F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3F, KeyframeAnimations.degreeVec(-32.6146F, -29.0242F, -30.3444F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-73.4721F, -60.5555F, 23.5865F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.52F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7F, KeyframeAnimations.degreeVec(30F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(0F, 20F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, -20F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3F, KeyframeAnimations.degreeVec(0F, 20F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(20F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-40F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-40F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 9F, -5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.posVec(0.0F, 9F, -5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7F, KeyframeAnimations.posVec(0.0F, -2F, -2F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rf", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.68F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.2F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 13F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.posVec(0.0F, 13F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lf", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.68F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.2F, KeyframeAnimations.degreeVec(-70F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.degreeVec(-10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 13F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2F, KeyframeAnimations.posVec(0.0F, 13F, 5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition KNOCK1=AnimationDefinition.Builder.withLength(1.8F)
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(35F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(35F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rb", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.posVec(0.0F, 3F, -2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.posVec(0.0F, 3F, -2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rb", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lb", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.posVec(0.0F, 3F, -2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.posVec(0.0F, 3F, -2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lb", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.posVec(0.0F, 0F, 3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.posVec(0.0F, 0F, 3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.92F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(20F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.92F, KeyframeAnimations.degreeVec(36.9734F, 24.8131F, 24.551F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(27.7055F, 18.7487F, 19.7955F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.92F, KeyframeAnimations.degreeVec(-38.8509F, -15.2903F, 19.8991F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(-38.8509F, -15.2903F, 19.8991F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.72F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.92F, KeyframeAnimations.degreeVec(-39.797F, 4.8123F, -13.6433F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(-39.797F, 4.8123F, -13.6433F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition ATTACK1=AnimationDefinition.Builder.withLength(1.64F)
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(2.5F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(2.5F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(4.9366F, -1.7307F, 42.331F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.96F, KeyframeAnimations.degreeVec(1.2874F, -1.1415F, 97.6592F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(0.8193F, -1.5129F, 117.6612F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-182.6257F, 1.6214F, 65.3013F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(-86.9244F, 25.4739F, 54.8139F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(-83.9579F, 1.9262F, 8.6966F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-83.8022F, -12.9893F, 7.0945F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.96F, KeyframeAnimations.degreeVec(27.9449F, -74.0712F, -12.6813F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(14.0019F, -44.136F, -9.8511F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(14.0019F, -44.136F, -9.8511F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(27.9964F, -31.2414F, -0.1048F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(50.4964F, -31.2414F, -0.1048F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(102.9964F, -31.2414F, -0.1048F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(197.8446F, -12.3249F, 6.9364F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.61F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(52.5407F, 16.0072F, -51.7131F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(96.1017F, -17.8034F, 0.7005F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(87.6367F, -30.6952F, 42.7937F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(87.6367F, -30.6952F, 42.7937F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-72.8624F, -59.8341F, 125.9423F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.61F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-182.2085F, 3.1393F, -62.6812F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.56F, KeyframeAnimations.degreeVec(-166.3338F, -0.1735F, -120.1149F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68F, KeyframeAnimations.degreeVec(-158.4377F, 12.0152F, -181.9027F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8F, KeyframeAnimations.degreeVec(-144.5084F, 13.8103F, -186.6908F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.96F, KeyframeAnimations.degreeVec(-128.6704F, 40.7058F, -207.0924F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.12F, KeyframeAnimations.degreeVec(-135.3825F, 54.7892F, -222.7528F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.36F, KeyframeAnimations.degreeVec(-21.8848F, -26.0357F, -247.3849F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6F, KeyframeAnimations.degreeVec(-88.8429F, -84.8502F, -279.1836F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.61F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.LINEAR)
					))
			.build();
	public static final AnimationDefinition SHOOT=AnimationDefinition.Builder.withLength(1.52F)
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, 80F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0F, 0F, 80F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 50F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, 30F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0F, 0F, 30F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, 15F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0F, 0F, 15F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-100.9687F, 6.9539F, 33.0016F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-104.1779F, -39.5454F, 43.4834F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(-104.1779F, -39.5454F, 43.4834F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-177.0014F, -46.0325F, 135.3935F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-103.8649F, 14.7665F, -37.2462F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-98.5041F, -7.1105F, -31.9436F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(-98.5041F, -7.1105F, -31.9436F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0F, -4F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1F, KeyframeAnimations.posVec(0.0F, 0F, -4F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition SHOOT_UP =AnimationDefinition.Builder.withLength(1.52F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-47.5F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-60F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-60F, 0F, 0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("held_item", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 40F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-7.4366F, -0.9762F, 17.5634F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-7.4366F, -0.9762F, 17.5634F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-144.9618F, 28.8791F, -45.9554F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-149.1192F, 6.1267F, -39.9903F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-149.1192F, 6.1267F, -39.9903F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 15F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(0F, 0F, 77.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1F, KeyframeAnimations.degreeVec(0F, 0F, 77.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(14.7822F, -2.5759F, 87.1658F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-141.059F, 0.0657F, 29.5042F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-143.2354F, -13.8549F, 47.3567F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-143.2354F, -13.8549F, 47.3567F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4F, KeyframeAnimations.degreeVec(-27.5F, 0F, 20F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.08F, KeyframeAnimations.degreeVec(-27.5F, 0F, 20F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition COLLIDE =AnimationDefinition.Builder.withLength(1.52F)
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-100F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-100F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-37.8531F, -23.0411F, 6.6723F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-37.8531F, -23.0411F, 6.6723F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-37.8531F, 23.0411F, -6.6723F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-37.8531F, 23.0411F, -6.6723F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-100F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-100F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-50F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(-50F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(50F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2F, KeyframeAnimations.degreeVec(50F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
	public static final AnimationDefinition FIREBALL =AnimationDefinition.Builder.withLength(6F)
			.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.degreeVec(15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.degreeVec(-25F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.degreeVec(-25F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.degreeVec(-25F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rf", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_rf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lf", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("leg_lf", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.posVec(0.0F, 2.5F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(2.52F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.degreeVec(-15F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.08F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.degreeVec(10F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("up", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(2.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.posVec(0.0F, 0F, -3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.08F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.52F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.8F, KeyframeAnimations.posVec(0.0F, 0F, -3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.52F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.8F, KeyframeAnimations.posVec(0.0F, 0F, -3F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.posVec(0.0F, -2F, 2F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.posVec(0.0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.POSITION, 
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.52F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.posVec(0.0F, -0.4F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.posVec(0.0F, 0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(0F, 0F, -20F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.degreeVec(0F, 0F, -20F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-20.2881F, -39.9514F, -22.8565F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.degreeVec(-20.2881F, -39.9514F, -22.8565F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_forearm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(10F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.degreeVec(10F, 0F, 35F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2F, KeyframeAnimations.degreeVec(-60.6895F, 62.0575F, -26.9826F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.52F, KeyframeAnimations.degreeVec(-60.6895F, 62.0575F, -26.9826F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6F, KeyframeAnimations.degreeVec(0F, 0F, 0F), AnimationChannel.Interpolations.CATMULLROM)
					))
			.build();
}
