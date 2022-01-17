package com.majong.zelda.overlays;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class ZeldaOverlays extends AbstractGui{
	private final int w;
    private final int h;
    private MatrixStack matrixStack;
    private final Minecraft minecraft;
	private ResourceLocation texture=new ResourceLocation(Utils.MOD_ID, "textures/gui/hud.png");
	public ZeldaOverlays(MatrixStack matrixStack) {
		this.w = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.h = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
	}
	public void render(ClientPlayerEntity player) {
		DataManager.preventnull(player);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(texture);
		if(DataManager.data.get(player).unlocked[0]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.2*h), 0, 0, 16, 16, 64, 64);
			if(DataManager.data.get(player).skill[0]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.2*h+16*(1.0-DataManager.data.get(player).cd[0]/((double)ZeldaConfig.WATER.get()))), 0, 16, 16, (int)(16*(DataManager.data.get(player).cd[0]/((double)ZeldaConfig.WATER.get()))+1), 64, 64);
		}
		if(DataManager.data.get(player).unlocked[1]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.3*h), 16, 0, 16, 16, 64, 64);
			if(DataManager.data.get(player).skill[1]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.3*h+16*(1.0-DataManager.data.get(player).cd[1]/((double)ZeldaConfig.WIND.get()))), 0, 16, 16, (int)(16*(DataManager.data.get(player).cd[1]/((double)ZeldaConfig.WIND.get()))+1), 64, 64);
		}
		if(DataManager.data.get(player).unlocked[2]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.4*h), 32, 0, 16, 16, 64, 64);
			if(DataManager.data.get(player).skill[2]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.4*h+16*(1.0-DataManager.data.get(player).cd[2]/((double)ZeldaConfig.FIRE.get()))), 0, 16, 16, (int)(16*(DataManager.data.get(player).cd[2]/((double)ZeldaConfig.FIRE.get()))+1), 64, 64);
		}
		if(DataManager.data.get(player).unlocked[3]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.5*h), 48, 0, 16, 16, 64, 64);
			if(DataManager.data.get(player).skill[3]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.5*h+16*(1.0-DataManager.data.get(player).cd[3]/((double)ZeldaConfig.THUNDER.get()))), 0, 16, 16, (int)(16*(DataManager.data.get(player).cd[3]/((double)ZeldaConfig.THUNDER.get()))+1), 64, 64);
		}
		if(DataManager.data.get(player).unlocked[0])
			drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"x"+DataManager.data.get(player).skill[0],(int)(0.05*w)+24,(int)(0.2*h)+12,16777215);
		if(DataManager.data.get(player).unlocked[1])
			drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"x"+DataManager.data.get(player).skill[1],(int)(0.05*w)+24,(int)(0.3*h)+12,16777215);
		if(DataManager.data.get(player).unlocked[2])
			drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"x"+DataManager.data.get(player).skill[2],(int)(0.05*w)+24,(int)(0.4*h)+12,16777215);
		if(DataManager.data.get(player).unlocked[3])
			drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"x"+DataManager.data.get(player).skill[3],(int)(0.05*w)+24,(int)(0.5*h)+12,16777215);
	}
}
