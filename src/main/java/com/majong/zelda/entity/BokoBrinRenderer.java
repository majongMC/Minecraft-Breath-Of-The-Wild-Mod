package com.majong.zelda.entity;

import com.majong.zelda.Utils;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BokoBrinRenderer extends HumanoidMobRenderer<BokoBrinEntity,BokoBrinModel>{
	public static final ModelLayerLocation INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("minecraft", "piglin"), "inner_armor");
	public static final ModelLayerLocation OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("minecraft", "piglin"), "outer_armor");
	public BokoBrinRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new BokoBrinModel(renderManagerIn.bakeLayer(BokoBrinModel.LAYER_LOCATION)), 0.7f);
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<BokoBrinEntity>(renderManagerIn.bakeLayer(INNER_ARMOR)), new HumanoidModel<BokoBrinEntity>(renderManagerIn.bakeLayer(OUTER_ARMOR)),renderManagerIn.getModelManager()));
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	public ResourceLocation getTextureLocation(BokoBrinEntity entity) {
		// TODO �Զ����ɵķ������
		return new ResourceLocation(Utils.MOD_ID, "textures/entity/boko_brin.png");
	}

}
