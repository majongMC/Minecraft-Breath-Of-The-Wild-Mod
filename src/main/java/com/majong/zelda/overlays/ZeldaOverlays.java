package com.majong.zelda.overlays;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.item.ItemLoader;
import com.majong.zelda.item.ShikaStone;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZeldaOverlays extends GuiComponent{
	private final int w;
    private final int h;
    private PoseStack PoseStack;
	private ResourceLocation texture=new ResourceLocation(Utils.MOD_ID, "textures/gui/hud.png");
	public ZeldaOverlays(PoseStack PoseStack) {
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.PoseStack = PoseStack;
	}
	public void render(AbstractClientPlayer player) {
		DataManager.preventnull(player);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, texture);
		//this.minecraft.getTextureManager().bindForSetup(texture);
		ZeldaPlayerData playerdata=DataManager.data.get(player);
		if(playerdata.unlocked[0]) {
			blit(PoseStack, (int)(0.05*w), (int)(0.2*h), 0, 0, 16, 16, 64, 64);
			if(playerdata.skill[0]<=0)
				blit(PoseStack, (int)(0.05*w), (int)(0.2*h+16*(1.0-playerdata.cd[0]/((double)ZeldaConfig.WATER.get()))), 0, 16, 16, (int)(16*(playerdata.cd[0]/((double)ZeldaConfig.WATER.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[1]) {
			blit(PoseStack, (int)(0.05*w), (int)(0.3*h), 16, 0, 16, 16, 64, 64);
			if(playerdata.skill[1]<=0)
				blit(PoseStack, (int)(0.05*w), (int)(0.3*h+16*(1.0-playerdata.cd[1]/((double)ZeldaConfig.WIND.get()))), 0, 16, 16, (int)(16*(playerdata.cd[1]/((double)ZeldaConfig.WIND.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[2]) {
			blit(PoseStack, (int)(0.05*w), (int)(0.4*h), 32, 0, 16, 16, 64, 64);
			if(playerdata.skill[2]<=0)
				blit(PoseStack, (int)(0.05*w), (int)(0.4*h+16*(1.0-playerdata.cd[2]/((double)ZeldaConfig.FIRE.get()))), 0, 16, 16, (int)(16*(playerdata.cd[2]/((double)ZeldaConfig.FIRE.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[3]) {
			blit(PoseStack, (int)(0.05*w), (int)(0.5*h), 48, 0, 16, 16, 64, 64);
			if(playerdata.skill[3]<=0)
				blit(PoseStack, (int)(0.05*w), (int)(0.5*h+16*(1.0-playerdata.cd[3]/((double)ZeldaConfig.THUNDER.get()))), 0, 16, 16, (int)(16*(playerdata.cd[3]/((double)ZeldaConfig.THUNDER.get()))+1), 64, 64);
		}
		if(playerdata.unlocked[0])
			drawCenteredString(PoseStack,Minecraft.getInstance().font,"x"+playerdata.skill[0],(int)(0.05*w)+24,(int)(0.2*h)+12,16777215);
		if(playerdata.unlocked[1])
			drawCenteredString(PoseStack,Minecraft.getInstance().font,"x"+playerdata.skill[1],(int)(0.05*w)+24,(int)(0.3*h)+12,16777215);
		if(playerdata.unlocked[2])
			drawCenteredString(PoseStack,Minecraft.getInstance().font,"x"+playerdata.skill[2],(int)(0.05*w)+24,(int)(0.4*h)+12,16777215);
		if(playerdata.unlocked[3])
			drawCenteredString(PoseStack,Minecraft.getInstance().font,"x"+playerdata.skill[3],(int)(0.05*w)+24,(int)(0.5*h)+12,16777215);
		if(player.getMainHandItem().getItem()==ItemLoader.SHIKA_STONE.get()) {
			drawCenteredString(PoseStack,Minecraft.getInstance().font,ShikaStone.name,(int)(0.9*w),(int)(0.85*h),16777215);
			if(ZeldaConfigClient.DISPLAY_ANGLE.get())
			{
				double display=ShikaStone.delta;
				if(display>180)
				display=360-display;
				fill(PoseStack,(int)(this.w*0.9-32), (int)(this.h*0.9),(int)(this.w*0.9-32)+64, (int)(this.h*0.9)+2, -2139062144);
				fill(PoseStack,(int)(this.w*0.9-32), (int)(this.h*0.9),(int)(this.w*0.9-32+64*(180-display)/180), (int)(this.h*0.9)+2, -14503604);
			}else
				drawCenteredString(PoseStack,Minecraft.getInstance().font,""+ShikaStone.delta,(int)(0.9*w),(int)(0.9*h),16777215);
		}
	}
}
