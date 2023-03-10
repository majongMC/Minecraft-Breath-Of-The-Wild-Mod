package com.majong.zelda.entity;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class YigaTeamMemberRenderer extends MobRenderer{
	private EntityModel<YigaTeamMemberEntity> VillagerModel,YGTModel;
	public YigaTeamMemberRenderer(EntityRendererProvider.Context renderManagerIn, EntityModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
		VillagerModel=new VillagerModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.VILLAGER));
		YGTModel=this.model;
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER_SLIM_INNER_ARMOR)), new HumanoidModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER_SLIM_OUTER_ARMOR))));
		this.addLayer(new ItemInHandLayer<>(this));
		// TODO �Զ����ɵĹ��캯�����
	}
	public YigaTeamMemberRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn,new YigaTeamMemberModel(renderManagerIn.bakeLayer(ModelLayers.PLAYER_SLIM),true) ,0.7F);
		VillagerModel=new VillagerModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.VILLAGER));
		YGTModel=this.model;
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER_SLIM_INNER_ARMOR)), new HumanoidModel<YigaTeamMemberEntity>(renderManagerIn.bakeLayer(ModelLayers.PLAYER_SLIM_OUTER_ARMOR))));
		this.addLayer(new ItemInHandLayer<>(this));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public void render(Mob entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		if(((YigaTeamMemberEntity)entityIn).isactivated()) {
			this.model=YGTModel;
		}
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
