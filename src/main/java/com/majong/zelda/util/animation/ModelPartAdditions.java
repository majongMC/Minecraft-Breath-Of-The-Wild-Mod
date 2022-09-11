package com.majong.zelda.util.animation;

import java.util.NoSuchElementException;

import com.mojang.math.Vector3f;

import net.minecraft.client.model.geom.ModelPart;
public class ModelPartAdditions {
	public static void offsetPos(ModelPart model,Vector3f p_233565_) {
	      model.x += p_233565_.x();
	      model.y += p_233565_.y();
	      model.z += p_233565_.z();
	   }
	public static void offsetRotation(ModelPart model,Vector3f p_233568_) {
		  model.xRot += p_233568_.x();
		  model.yRot += p_233568_.y();
		  model.zRot += p_233568_.z();
	   }

	   public static void offsetScale(ModelPart model,Vector3f p_233571_) {
		   /*model.xScale += p_233571_.x();
		   model.yScale += p_233571_.y();
		   model.zScale += p_233571_.z();*/
	   }
	   public static boolean hasChild(ModelPart model,String p_233563_) {
		      try {
		    	  model.getChild(p_233563_);
		      }catch(NoSuchElementException e) {return false;}
		      return true;
		   }
}
