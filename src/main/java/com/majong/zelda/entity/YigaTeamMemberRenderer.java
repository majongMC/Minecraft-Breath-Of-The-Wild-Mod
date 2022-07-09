package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class YigaTeamMemberRenderer extends MobRenderer{
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utils.MOD_ID, "yiga_team_member"), "main");
	//public static final ModelLayerLocation VILLAGER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "villager"), "main");
	private EntityModel<YigaTeamMemberEntity> VillagerModel,PlayerModel;
	public YigaTeamMemberRenderer(EntityRendererProvider.Context renderManagerIn, GuardianModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		VillagerModel=this.model;
		PlayerModel=new PlayerModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER),true);
		// TODO �Զ����ɵĹ��캯�����
	}
	public YigaTeamMemberRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new VillagerModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.VILLAGER)),0.7F);
		VillagerModel=this.model;
		PlayerModel=new PlayerModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER),true);
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public void render(Mob entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		if(((YigaTeamMemberEntity)entityIn).isactivated())
			this.model=PlayerModel;
		else
			this.model=VillagerModel;
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	@Override
	public ResourceLocation getTextureLocation(Entity p_110775_1_) {
		// TODO �Զ����ɵķ������
		if(((YigaTeamMemberEntity)p_110775_1_).isactivated())
			return new ResourceLocation(Utils.MOD_ID, "textures/entity/yigateam.png");
		else
			return new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
	}
}
