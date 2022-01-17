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
		this.w = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.h = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
	}
	public void render(double percentage,double delay,ITextComponent name) {
    	RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    	drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer, name,(int)(0.5*w),(int)(0.05*h),16777215);
    	this.BARR=new ResourceLocation(Utils.MOD_ID, "textures/gui/bar.png");
    	this.minecraft.getTextureManager().bindTexture(this.BARR);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 4,(int)(this.w*0.3),2, 16, 16);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 8,(int)(this.w*0.3*delay),2, 16, 16);
    	blit(matrixStack, (int)(this.w*0.35), (int)(this.h*0.1), 0, 0,(int)(this.w*0.3*percentage),2,16,16);
    }
}
