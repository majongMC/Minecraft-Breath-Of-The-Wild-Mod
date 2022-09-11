package com.majong.zelda.util.animation;

import com.mojang.math.Vector3f;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public record AnimationChannel(AnimationChannel.Target target, Keyframe... keyframes) {
   @OnlyIn(Dist.CLIENT)
   public interface Interpolation {
      Vector3f apply(Vector3f p_232223_, float p_232224_, Keyframe[] p_232225_, int p_232226_, int p_232227_, float p_232228_);
   }

   @OnlyIn(Dist.CLIENT)
   public static class Interpolations {
      public static final AnimationChannel.Interpolation LINEAR = (p_232241_, p_232242_, p_232243_, p_232244_, p_232245_, p_232246_) -> {
         Vector3f vector3f = p_232243_[p_232244_].target();
         Vector3f vector3f1 = p_232243_[p_232245_].target();
         p_232241_.set(Mth.lerp(p_232242_, vector3f.x(), vector3f1.x()) * p_232246_, Mth.lerp(p_232242_, vector3f.y(), vector3f1.y()) * p_232246_, Mth.lerp(p_232242_, vector3f.z(), vector3f1.z()) * p_232246_);
         return p_232241_;
      };
      public static final AnimationChannel.Interpolation CATMULLROM = (p_232234_, p_232235_, p_232236_, p_232237_, p_232238_, p_232239_) -> {
         Vector3f vector3f = p_232236_[Math.max(0, p_232237_ - 1)].target();
         Vector3f vector3f1 = p_232236_[p_232237_].target();
         Vector3f vector3f2 = p_232236_[p_232238_].target();
         Vector3f vector3f3 = p_232236_[Math.min(p_232236_.length - 1, p_232238_ + 1)].target();
         p_232234_.set(MthAdditions.catmullrom(p_232235_, vector3f.x(), vector3f1.x(), vector3f2.x(), vector3f3.x()) * p_232239_, MthAdditions.catmullrom(p_232235_, vector3f.y(), vector3f1.y(), vector3f2.y(), vector3f3.y()) * p_232239_, MthAdditions.catmullrom(p_232235_, vector3f.z(), vector3f1.z(), vector3f2.z(), vector3f3.z()) * p_232239_);
         return p_232234_;
      };
   }

   @OnlyIn(Dist.CLIENT)
   public interface Target {
      void apply(ModelPart p_232248_, Vector3f p_232249_);
   }

   @OnlyIn(Dist.CLIENT)
   public static class Targets {
      public static final AnimationChannel.Target POSITION = ModelPartAdditions::offsetPos;
      public static final AnimationChannel.Target ROTATION = ModelPartAdditions::offsetRotation;
      public static final AnimationChannel.Target SCALE = ModelPartAdditions::offsetScale;
   }
}
