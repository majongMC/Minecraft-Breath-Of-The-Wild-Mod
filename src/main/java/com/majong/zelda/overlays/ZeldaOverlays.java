package com.majong.zelda.overlays;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.item.ShikaStone;
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
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
	}
	public void render(ClientPlayerEntity player) {
		DataManager.preventnull(player);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(texture);
		ZeldaPlayerData playerdata=DataManager.data.get(player);
		if(playerdata.unlocked[0]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.2*h), 0, 0, 16, 16, 64, 64);
			if(playerdata.skill[0]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.2*h+16*(1.0-playerdata.cd[0]/((double)ZeldaConfig.WATER.get()))), 0, 16, 16, (int)(16*(playerdata.cd[0]/((double)ZeldaConfig.WATER.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[1]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.3*h), 16, 0, 16, 16, 64, 64);
			if(playerdata.skill[1]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.3*h+16*(1.0-playerdata.cd[1]/((double)ZeldaConfig.WIND.get()))), 0, 16, 16, (int)(16*(playerdata.cd[1]/((double)ZeldaConfig.WIND.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[2]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.4*h), 32, 0, 16, 16, 64, 64);
			if(playerdata.skill[2]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.4*h+16*(1.0-playerdata.cd[2]/((double)ZeldaConfig.FIRE.get()))), 0, 16, 16, (int)(16*(playerdata.cd[2]/((double)ZeldaConfig.FIRE.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[3]) {
			blit(matrixStack, (int)(0.05*w), (int)(0.5*h), 48, 0, 16, 16, 64, 64);
			if(playerdata.skill[3]==0)
				blit(matrixStack, (int)(0.05*w), (int)(0.5*h+16*(1.0-playerdata.cd[3]/((double)ZeldaConfig.THUNDER.get()))), 0, 16, 16, (int)(16*(playerdata.cd[3]/((double)ZeldaConfig.THUNDER.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[0])
			drawCenteredString(matrixStack,Minecraft.getInstance().font,"x"+playerdata.skill[0],(int)(0.05*w)+24,(int)(0.2*h)+12,16777215);
		if(playerdata.unlocked[1])
			drawCenteredString(matrixStack,Minecraft.getInstance().font,"x"+playerdata.skill[1],(int)(0.05*w)+24,(int)(0.3*h)+12,16777215);
		if(playerdata.unlocked[2])
			drawCenteredString(matrixStack,Minecraft.getInstance().font,"x"+playerdata.skill[2],(int)(0.05*w)+24,(int)(0.4*h)+12,16777215);
		if(playerdata.unlocked[3])
			drawCenteredString(matrixStack,Minecraft.getInstance().font,"x"+playerdata.skill[3],(int)(0.05*w)+24,(int)(0.5*h)+12,16777215);
		if(player.getMainHandItem().getItem()==ItemLoader.SHIKA_STONE.get()) {
			drawCenteredString(matrixStack,Minecraft.getInstance().font,ShikaStone.name,(int)(0.9*w),(int)(0.85*h),16777215);
			drawCenteredString(matrixStack,Minecraft.getInstance().font,""+ShikaStone.delta,(int)(0.9*w),(int)(0.9*h),16777215);
		}
	}
}
