package com.majong.zelda.overlays;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BloodBar extends AbstractGui{
	private final int w;
    private final int h;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;
    private ResourceLocation BARR=new ResourceLocation(Utils.MOD_ID, "textures/gui/bar.png");
	public BloodBar(MatrixStack matrixStack) {
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
	}
	public void render(double percentage,double delay,ITextComponent name,String at) {
    	RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    	matrixStack.scale(2.0F, 2.0F, 2.0F);
    	drawCenteredString(matrixStack,Minecraft.getInstance().font, name,(int)(0.25*w),(int)(0.025*h),16777215);
    	matrixStack.scale(0.5F, 0.5F, 0.5F);
    	drawCenteredString(matrixStack,Minecraft.getInstance().font, at,(int)(0.5*w),(int)(0.01*h),16777215);
    	this.BARR=new ResourceLocation(Utils.MOD_ID, "textures/gui/bar.png");
    	this.minecraft.getTextureManager().bind(this.BARR);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 4,(int)(this.w*0.3),2, 16, 16);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 8,(int)(this.w*0.3*delay),2, 16, 16);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 0,(int)(this.w*0.3*percentage),2,16,16);
    }
}
