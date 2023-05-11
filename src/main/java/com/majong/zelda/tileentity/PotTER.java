package com.majong.zelda.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class PotTER implements BlockEntityRenderer<PotTileEntity>{
	private long starttime=0;
	public PotTER(BlockEntityRendererProvider.Context p_173607_) {
		
	}
	@Override
	public void render(PotTileEntity tile, float partialTicks, PoseStack posestackIn, MultiBufferSource bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		long time=tile.getLevel().getGameTime();
		long process=time-starttime;
		float y[]= {0,0,0,0,0};
		if(tile.using&&process>140)
			starttime=time;
		if(process<120) {
			for(int i=0;i<5;i++) {
				y[i]=calculatey(process+partialTicks+4*i);
			}
		}
		if(process<=6) {
			y[1]=0;
			y[2]=0;
		}
		this.renderItemInPot(posestackIn, 0.5F, 0.5F+y[0], 0.5F, tile.getStack(0), bufferIn, combinedLightIn, combinedOverlayIn);
		this.renderItemInPot(posestackIn, 0.3F, 0.5F+y[1], 0.3F, tile.getStack(1), bufferIn, combinedLightIn, combinedOverlayIn);
		this.renderItemInPot(posestackIn, 0.7F, 0.5F+y[2], 0.7F, tile.getStack(2), bufferIn, combinedLightIn, combinedOverlayIn);
		this.renderItemInPot(posestackIn, 0.3F, 0.5F+y[3], 0.7F, tile.getStack(3), bufferIn, combinedLightIn, combinedOverlayIn);
		this.renderItemInPot(posestackIn, 0.7F, 0.5F+y[4], 0.3F, tile.getStack(4), bufferIn, combinedLightIn, combinedOverlayIn);
	}
	private float calculatey(float t) {
		if(t>120)
			return 0;
		while(t>20)
			t=t-20;
		float x=0.4F*t-0.04F*t*t;
		if(x<0)
			x=0;
		return x;
	}
	private void renderItemInPot(PoseStack posestackIn,float x,float y,float z,ItemStack item,MultiBufferSource bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		if(item.isEmpty())
			return;
		posestackIn.pushPose();
		posestackIn.translate(x, y, z);
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedmodel = itemRenderer.getModel(item, null, null, 0);
        itemRenderer.render(item, ItemDisplayContext.GROUND, true, posestackIn, bufferIn, combinedLightIn, combinedOverlayIn, bakedmodel);
		posestackIn.popPose();
	}

}
