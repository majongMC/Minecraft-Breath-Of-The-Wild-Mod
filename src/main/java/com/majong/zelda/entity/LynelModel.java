package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.animation.LynelAnimation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import majongmc.hllib.client.animation.AnimatePlayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
@Environment(value=EnvType.CLIENT)
public class LynelModel extends EntityModel<Lynel> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "lynel"), "main");
	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart leg_lf;
	private final PartPose leg_lfDefault;
	private final ModelPart leg_rf;
	private final PartPose leg_rfDefault;
	private final ModelPart leg_lb;
	private final PartPose leg_lbDefault;
	private final ModelPart leg_rb;
	private final PartPose leg_rbDefault;
	private final ModelPart up;
	private final PartPose upDefault;
	private final ModelPart tail;
	private final PartPose tailDefault;
	private final ModelPart neck;
	private final PartPose neckDefault;
	private final ModelPart right_arm;
	private final PartPose right_armDefault;
	private final ModelPart right_forearm;
	private final PartPose right_forearmDefault;
	private final ModelPart held_item;
	private final PartPose held_itemDefault;
	private final ModelPart beast_god_sword;
	private final ModelPart beast_god_bow0;
	private final ModelPart beast_god_bow1;
	private final ModelPart left_arm;
	private final PartPose left_armDefault;
	private final ModelPart left_forearm;
	private final PartPose left_forearmDefault;
	private final ModelPart head;
	private final PartPose headDefault;
	private final ModelPart jaw;
	private final PartPose jawDefault;

	public LynelModel(ModelPart root) {
		this.root=root;
		this.main = root.getChild("main");
		this.leg_lf=main.getChild("leg_lf");
		this.leg_lfDefault=this.leg_lf.storePose();
		this.leg_rf=main.getChild("leg_rf");
		this.leg_rfDefault=this.leg_rf.storePose();
		this.leg_lb=main.getChild("leg_lb");
		this.leg_lbDefault=this.leg_lb.storePose();
		this.leg_rb=main.getChild("leg_rb");
		this.leg_rbDefault=this.leg_rb.storePose();
		this.up=main.getChild("up");
		this.upDefault=up.storePose();
		this.tail=up.getChild("tail");
		this.tailDefault=this.tail.storePose();
		this.neck=up.getChild("neck");
		this.neckDefault=this.neck.storePose();
		this.right_arm=neck.getChild("right_arm");
		this.right_armDefault=this.right_arm.storePose();
		this.right_forearm=right_arm.getChild("right_forearm");
		this.right_forearmDefault=this.right_forearm.storePose();
		this.held_item=right_forearm.getChild("held_item");
		this.held_itemDefault=this.held_item.storePose();
		this.beast_god_sword=held_item.getChild("beast_god_sword");
		this.beast_god_bow0=held_item.getChild("beast_god_bow0");
		this.beast_god_bow1=held_item.getChild("beast_god_bow1");
		this.left_arm=neck.getChild("left_arm");
		this.left_armDefault=this.left_arm.storePose();
		this.left_forearm=left_arm.getChild("left_forearm");
		this.left_forearmDefault=this.left_forearm.storePose();
		this.head=neck.getChild("head");
		this.headDefault=this.head.storePose();
		this.jaw=head.getChild("jaw");
		this.jawDefault=this.jaw.storePose();
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg_lf = main.addOrReplaceChild("leg_lf", CubeListBuilder.create().texOffs(96, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 119).addBox(-4.0F, 6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-1.8F)), PartPose.offset(6.0F, -11.0F, -6.0F));

		PartDefinition leg_rf = main.addOrReplaceChild("leg_rf", CubeListBuilder.create().texOffs(0, 80).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 119).addBox(-4.0F, 6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-1.8F)), PartPose.offset(-6.0F, -11.0F, -6.0F));

		PartDefinition leg_lb = main.addOrReplaceChild("leg_lb", CubeListBuilder.create().texOffs(10, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 119).addBox(-4.0F, 6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-1.8F)), PartPose.offset(6.0F, -11.0F, 16.0F));

		PartDefinition leg_rb = main.addOrReplaceChild("leg_rb", CubeListBuilder.create().texOffs(14, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 119).addBox(-4.0F, 6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-1.8F)), PartPose.offset(-6.0F, -11.0F, 16.0F));

		PartDefinition up = main.addOrReplaceChild("up", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition body = up.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 80).addBox(-8.0F, -23.0F, -13.0F, 16.0F, 12.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = up.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -21.0F, 13.0F));

		PartDefinition tail_layer_r1 = tail.addOrReplaceChild("tail_layer_r1", CubeListBuilder.create().texOffs(1, 140).addBox(-1.5F, 7.0F, -1.3F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(0, 40).addBox(-1.5F, -13.0F, 21.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, -13.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition neck = up.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(58, 80).addBox(-6.0F, -15.0F, -5.0F, 12.0F, 16.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 182).addBox(-6.0F, -15.0F, -6.0F, 12.0F, 8.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -22.0F, -8.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 158).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
		.texOffs(0, 201).addBox(-10.0F, -13.0F, -11.25F, 20.0F, 19.0F, 14.0F, new CubeDeformation(-7.0F)), PartPose.offset(0.0F, -15.0F, -2.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(208, 0).addBox(-7.0F, -7.0F, -9.2F, 14.0F, 12.0F, 10.0F, new CubeDeformation(-5.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition horn_a = head.addOrReplaceChild("horn_a", CubeListBuilder.create().texOffs(235, 127).addBox(-1.05F, -2.0F, -3.8F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offsetAndRotation(-1.75F, -4.75F, -4.0F, -0.0094F, 0.0426F, -0.2184F));

		PartDefinition cube_r1 = horn_a.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(238, 209).addBox(-1.05F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.9F)), PartPose.offsetAndRotation(0.0F, -2.3F, -2.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r2 = horn_a.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(242, 188).addBox(-1.05F, -1.05F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.85F)), PartPose.offsetAndRotation(0.0F, -2.1F, -2.4F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r3 = horn_a.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(238, 171).addBox(-1.05F, -1.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(0.0F, -1.8F, -2.6F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r4 = horn_a.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(236, 155).addBox(-1.05F, -1.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, -1.4F, -2.8F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r5 = horn_a.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(235, 93).addBox(-1.0188F, -1.0299F, -1.0806F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.5F, -0.7887F, 0.0F, 0.0F));

		PartDefinition cube_r6 = horn_a.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(228, 71).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition horn_b = head.addOrReplaceChild("horn_b", CubeListBuilder.create().texOffs(235, 127).addBox(-1.05F, -2.0F, -3.8F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offsetAndRotation(1.75F, -4.75F, -4.0F, -0.0094F, -0.0426F, 0.2184F));

		PartDefinition cube_r7 = horn_b.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(238, 209).addBox(-1.05F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.9F)), PartPose.offsetAndRotation(0.0F, -2.3F, -2.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r8 = horn_b.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(242, 188).addBox(-1.05F, -1.05F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.85F)), PartPose.offsetAndRotation(0.0F, -2.1F, -2.4F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r9 = horn_b.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(238, 171).addBox(-1.05F, -1.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(0.0F, -1.8F, -2.6F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r10 = horn_b.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(236, 155).addBox(-1.05F, -1.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, -1.4F, -2.8F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r11 = horn_b.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(235, 93).addBox(-1.0188F, -1.0299F, -1.0806F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.5F, -0.7887F, 0.0F, 0.0F));

		PartDefinition cube_r12 = horn_b.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(228, 71).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_arm = neck.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(96, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -12.0F, -1.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition left_forearm = left_arm.addOrReplaceChild("left_forearm", CubeListBuilder.create().texOffs(12, 12).addBox(-1.734F, -1.6428F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition right_arm = neck.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -12.0F, -1.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition right_forearm = right_arm.addOrReplaceChild("right_forearm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.734F, -1.6428F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 8.4F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition held_item = right_forearm.addOrReplaceChild("held_item", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition beast_god_sword = held_item.addOrReplaceChild("beast_god_sword", CubeListBuilder.create().texOffs(28, 12).addBox(-0.1F, -13.2434F, -0.6895F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.2F))
		.texOffs(12, 0).addBox(-0.6F, 2.7566F, -1.1895F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F))
		.texOffs(0, 16).addBox(-1.1F, -20.2434F, -1.6895F, 3.0F, 6.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(91, 80).addBox(-2.1F, -24.9934F, -2.6895F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.75F))
		.texOffs(16, 80).addBox(2.4F, -27.2434F, -4.1895F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.5F))
		.texOffs(0, 64).addBox(-3.6F, -27.2434F, -4.1895F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.5F))
		.texOffs(0, 58).addBox(2.4F, -27.2434F, 1.8105F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.5F))
		.texOffs(0, 25).addBox(-3.6F, -27.2434F, 1.8105F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r13 = beast_god_sword.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 0).addBox(6.5F, -12.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.4F, -6.2434F, -0.1895F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r14 = beast_god_sword.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(24, 12).addBox(-0.5F, -12.0F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.4F, -6.2434F, -0.1895F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r15 = beast_god_sword.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(8, 25).addBox(-7.5F, -12.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.4F, -6.2434F, -0.1895F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r16 = beast_god_sword.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, -12.0F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.4F, -6.2434F, -0.1895F, -0.7854F, 0.0F, 0.0F));

		PartDefinition beast_god_bow0 = held_item.addOrReplaceChild("beast_god_bow0", CubeListBuilder.create().texOffs(0, 40).addBox(-10.3431F, -4.0F, -21.2426F, 32.0F, 8.0F, 32.0F, new CubeDeformation(-3.5F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.7854F, -1.5708F));

		PartDefinition beast_god_bow1 = held_item.addOrReplaceChild("beast_god_bow1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.3431F, -4.0F, -21.2426F, 32.0F, 8.0F, 32.0F, new CubeDeformation(-3.5F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.7854F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Lynel lynel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		reset();
		head.yRot=Mth.PI*netHeadYaw/180;
		displayhelditem(lynel);
		animateWalk(limbSwing, limbSwingAmount);
		AnimatePlayer.animate(root, lynel.burstAnimationState, LynelAnimation.BURST, ageInTicks);
		AnimatePlayer.animate(root, lynel.attackAnimationState, LynelAnimation.ATTACK1, ageInTicks);
		AnimatePlayer.animate(root, lynel.knock1AnimationState, LynelAnimation.KNOCK1, ageInTicks);
		AnimatePlayer.animate(root, lynel.deathAnimationState, LynelAnimation.DEATH, ageInTicks);
		AnimatePlayer.animate(root, lynel.shootAnimationState, LynelAnimation.SHOOT, ageInTicks);
		AnimatePlayer.animate(root, lynel.shootupAnimationState, LynelAnimation.SHOOT_UP, ageInTicks);
		AnimatePlayer.animate(root, lynel.collideAnimationState, LynelAnimation.COLLIDE, ageInTicks);
		AnimatePlayer.animate(root, lynel.fireballAnimationState, LynelAnimation.FIREBALL, ageInTicks);
	}
	private void reset() {
		up.loadPose(upDefault);
		held_item.loadPose(held_itemDefault);
		right_arm.loadPose(right_armDefault);
		right_forearm.loadPose(right_forearmDefault);
		left_arm.loadPose(left_armDefault);
		left_forearm.loadPose(left_forearmDefault);
		jaw.loadPose(jawDefault);
		head.loadPose(headDefault);
		neck.loadPose(neckDefault);
		leg_rf.loadPose(leg_rfDefault);
		leg_lf.loadPose(leg_lfDefault);
		leg_lb.loadPose(leg_lbDefault);
		leg_rb.loadPose(leg_rbDefault);
		tail.loadPose(tailDefault);
	}
	private void displayhelditem(Lynel lynel) {
		switch(lynel.held_item) {
		case 0:beast_god_sword.visible=false;beast_god_bow0.visible=false;beast_god_bow1.visible=false;break;
		case 1:beast_god_sword.visible=true;beast_god_bow0.visible=false;beast_god_bow1.visible=false;break;
		case 2:beast_god_sword.visible=false;beast_god_bow0.visible=true;beast_god_bow1.visible=false;break;
		case 3:beast_god_sword.visible=false;beast_god_bow0.visible=false;beast_god_bow1.visible=true;break;
		}
	}
	private void animateWalk(float limbSwing, float limbSwingAmount) {
		float swing=0.3F*limbSwingAmount*Mth.sin(limbSwing);
		leg_lf.xRot=swing;
		leg_rb.xRot=swing;
		leg_lb.xRot=-swing;
		leg_rf.xRot=-swing;
		tail.xRot=0.3F*limbSwingAmount;
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}