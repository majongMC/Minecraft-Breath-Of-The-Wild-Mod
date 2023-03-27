package com.majong.zelda.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;

public class HealthBar extends GuiComponent{
	private final int w;
    private final int h;
    private PoseStack PoseStack;
	public HealthBar(PoseStack PoseStack) {
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.PoseStack = PoseStack;
	}
	public void render(double percentage,double delay,Component name,String at) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		PoseStack.scale(2.0F, 2.0F, 2.0F);
    	drawCenteredString(PoseStack,Minecraft.getInstance().font, name,(int)(0.25*w),(int)(0.025*h),16777215);
    	PoseStack.scale(0.5F, 0.5F, 0.5F);
    	drawCenteredString(PoseStack,Minecraft.getInstance().font, at,(int)(0.5*w),(int)(0.01*h),16777215);
    	fill(PoseStack,(int)(this.w*0.35), (int)(this.h*0.1),(int)(this.w*0.65), (int)(this.h*0.1)+2, -2139062144);
    	fill(PoseStack,(int)(this.w*0.35), (int)(this.h*0.1),(int)(this.w*(0.35+0.3*delay)), (int)(this.h*0.1)+2, -1);
    	fill(PoseStack,(int)(this.w*0.35), (int)(this.h*0.1),(int)(this.w*(0.35+0.3*percentage)), (int)(this.h*0.1)+2, -1237980);
    }
}
