package com.majong.zelda.entity;

import com.majong.zelda.entity.animation.YigaTeamMemberAnimation;

import majongmc.hllib.client.animation.AnimatePlayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
@Environment(value=EnvType.CLIENT)
public class YigaTeamMemberModel extends PlayerModel<YigaTeamMemberEntity>{
	private ModelPart root;
	public YigaTeamMemberModel(ModelPart p_170821_, boolean p_170822_) {
		super(p_170821_, p_170822_);
		this.root=p_170821_;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setupAnim(YigaTeamMemberEntity p_103395_, float p_103396_, float p_103397_, float p_103398_, float p_103399_, float p_103400_) {
		init();
		super.setupAnim(p_103395_, p_103396_, p_103397_, p_103398_, p_103399_, p_103400_);
		if(p_103395_.isPlayingAnim())
			disablewalkinganim();
		AnimatePlayer.animate(root, p_103395_.attackstate, YigaTeamMemberAnimation.ATTACK, p_103398_);
	}
	private void init() {
		this.head.zRot=0;
		this.head.x=0;
		this.head.y=0;
		this.head.z=0;
		this.body.x=0;
		this.body.y=0;
		this.body.z=0;
		this.body.zRot=0;
	}
	private void disablewalkinganim() {
		this.leftLeg.xRot=0;
		this.rightLeg.xRot=0;
		this.rightArm.xRot=0;
		this.leftArm.xRot=0;
	}
}
