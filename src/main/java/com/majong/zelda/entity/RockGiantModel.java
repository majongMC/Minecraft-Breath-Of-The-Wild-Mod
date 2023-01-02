package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.animation.RockGiantAnimation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class RockGiantModel extends HierarchicalModel<RockGiantEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "rock_giant"), "main");
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart hand;
	private final ModelPart leftfoot;
	private final ModelPart rightfoot;
	public RockGiantModel(ModelPart root) {
		this.root=root;
		this.bone = root.getChild("bone");
		this.hand = root.getChild("hand");
		this.leftfoot = root.getChild("leftfoot");
		this.rightfoot = root.getChild("rightfoot");
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -62.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hand = partdefinition.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(0, 0).addBox(27.0F, -50.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-50.0F, -50.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftfoot = partdefinition.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rightfoot = partdefinition.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(0, 0).addBox(6.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}
	@Override
	public void setupAnim(RockGiantEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		hand.yRot=(float) (Math.PI*netHeadYaw/180);
		bone.yRot=(float) (Math.PI*netHeadYaw/180);
		bone.xRot=0;
		hand.xRot=0;
		this.animateWalk(limbSwing, limbSwingAmount);
		this.animate(entity.knockaAnimationState,RockGiantAnimation.ROCKGIANT_KNOCK_A,ageInTicks);
		this.animate(entity.knockbAnimationState,RockGiantAnimation.ROCKGIANT_KNOCK_B,ageInTicks);
		this.animate(entity.attackaAnimationState,RockGiantAnimation.ROCKGIANT_ATTACK_A,ageInTicks);
		this.animate(entity.attackbAnimationState,RockGiantAnimation.ROCKGIANT_ATTACK_B,ageInTicks);
	}
	private void animateWalk(float limbSwing, float limbSwingAmount) {
		float swing=0.3F*limbSwingAmount*Mth.sin(limbSwing);
		this.leftfoot.xRot=swing;
		this.rightfoot.xRot=-swing;
		this.bone.zRot=swing;
		this.hand.zRot=2*swing;
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftfoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightfoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart root() {
		return this.root;
	}
}