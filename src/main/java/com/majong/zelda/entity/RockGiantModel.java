package com.majong.zelda.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class RockGiantModel extends EntityModel<RockGiantEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer hand;
	private final ModelRenderer leftfoot;
	private final ModelRenderer rightfoot;
	public RockGiantModel() {
		texWidth = 256;
		texHeight = 128;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 0).addBox(-24.0F, -62.0F, -24.0F, 48.0F, 48.0F, 48.0F, 0.0F, false);

		hand = new ModelRenderer(this);
		hand.setPos(0.0F, 24.0F, 0.0F);
		hand.texOffs(0, 0).addBox(27.0F, -50.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
		hand.texOffs(0, 0).addBox(-50.0F, -50.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);

		leftfoot = new ModelRenderer(this);
		leftfoot.setPos(0.0F, 24.0F, 0.0F);
		leftfoot.texOffs(0, 0).addBox(-22.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		rightfoot = new ModelRenderer(this);
		rightfoot.setPos(0.0F, 24.0F, 0.0F);
		rightfoot.texOffs(0, 0).addBox(6.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(RockGiantEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		hand.yRot=(float) (Math.PI*(netHeadYaw+6*((ageInTicks-(int)(ageInTicks))*entity.currenty+(1-ageInTicks+(int)(ageInTicks))*entity.lasty))/180);
		this.animateWalk(limbSwing, limbSwingAmount);
	}
	private void animateWalk(float limbSwing, float limbSwingAmount) {
		float swing=0.3F*limbSwingAmount*MathHelper.sin(limbSwing);
		this.leftfoot.xRot=swing;
		this.rightfoot.xRot=-swing;
		this.bone.zRot=swing;
		this.hand.zRot=2*swing;
	}
	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		hand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}