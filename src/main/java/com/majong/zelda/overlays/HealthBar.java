package com.majong.zelda.overlays;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class HealthBar extends GuiComponent{
	private final int w;
    private final int h;
    //private final Minecraft minecraft;
    private PoseStack PoseStack;
    private ResourceLocation BARR=new ResourceLocation(Utils.MOD_ID, "textures/gui/bar.png");
	public HealthBar(PoseStack PoseStack) {
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        //this.minecraft = Minecraft.getInstance();
        this.PoseStack = PoseStack;
	}
	public void render(double percentage,double delay,Component name) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    	drawCenteredString(PoseStack,Minecraft.getInstance().font, name,(int)(0.5*w),(int)(0.05*h),16777215);
    	this.BARR=new ResourceLocation(Utils.MOD_ID, "textures/gui/bar.png");
		RenderSystem.setShaderTexture(0, this.BARR);
    	//this.minecraft.getTextureManager().bindForSetup(this.BARR);
    	blit(PoseStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 4,(int)(this.w*0.3),2, 16, 16);
    	blit(PoseStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 8,(int)(this.w*0.3*delay),2, 16, 16);
    	blit(PoseStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 0,(int)(this.w*0.3*percentage),2,16,16);
    }
}
