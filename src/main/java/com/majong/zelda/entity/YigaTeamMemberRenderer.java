package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class YigaTeamMemberRenderer extends MobRenderer{
	private EntityModel<YigaTeamMemberEntity> VillagerModel,PlayerModel;
	public YigaTeamMemberRenderer(EntityRendererManager renderManagerIn, GuardianModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		VillagerModel=this.model;
		PlayerModel=new PlayerModel<>(1, true);
		// TODO 自动生成的构造函数存根
	}
	public YigaTeamMemberRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new VillagerModel<>(0.0F), 0.7F);
		VillagerModel=this.model;
		PlayerModel=new PlayerModel<>(1, true);
		// TODO 自动生成的构造函数存根
	}
	@Override
    public void render(MobEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(((YigaTeamMemberEntity)entityIn).isactivated())
			this.model=PlayerModel;
		else
			this.model=VillagerModel;
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	@Override
	public ResourceLocation getTextureLocation(Entity p_110775_1_) {
		// TODO 自动生成的方法存根
		if(((YigaTeamMemberEntity)p_110775_1_).isactivated())
			return new ResourceLocation(Utils.MOD_ID, "textures/entity/yigateam.png");
		else
			return new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
	}
}
