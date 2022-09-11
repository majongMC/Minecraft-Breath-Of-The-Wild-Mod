package com.majong.zelda.util.animation;

public class MthAdditions {
	public static float catmullrom(float p_216245_, float p_216246_, float p_216247_, float p_216248_, float p_216249_) {
	      return 0.5F * (2.0F * p_216247_ + (p_216248_ - p_216246_) * p_216245_ + (2.0F * p_216246_ - 5.0F * p_216247_ + 4.0F * p_216248_ - p_216249_) * p_216245_ * p_216245_ + (3.0F * p_216247_ - p_216246_ - 3.0F * p_216248_ + p_216249_) * p_216245_ * p_216245_ * p_216245_);
	   }
}
