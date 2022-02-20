package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class FoodMessageGui extends Screen{
	private final int w;
    private final int h;
    private int heal,hunger,type;
	private final ResourceLocation ICONS=new ResourceLocation("minecraft", "textures/gui/icons.png");
	private final ResourceLocation FOOD2=new ResourceLocation(Utils.MOD_ID, "textures/items/hard_food.png");
	private final ResourceLocation FOOD1=new ResourceLocation("minecraft", "textures/item/suspicious_stew.png");
	private final ResourceLocation BACK=new ResourceLocation(Utils.MOD_ID, "textures/gui/background.png");
	public FoodMessageGui(int type,int heal,int hunger) {
		super(new TranslationTextComponent(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.heal=heal;
        this.hunger=hunger;
        this.type=type;
		// TODO 自动生成的构造函数存根
	}
	@Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(BACK);
		blit(matrixStack, (int)(0.3*w), (int)(0.3*h), 0, 0, (int)(0.4*w),(int)(0.4*h),16,16);
		this.minecraft.getTextureManager().bind(ICONS);
		int heart,halfheart;
		heart=heal/2;
		halfheart=heal%2;
		int x=(int) (0.52*w),y=(int) (0.45*h);
		for(int i=0;i<heart;i++) {
			blit(matrixStack, x, y, 52, 0, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfheart;i++) {
			blit(matrixStack, x, y, 62, 0, 9, 9, 256, 256);
			x+=10;
		}
		int hunger,halfhunger;
		hunger=this.hunger/2;
		halfhunger=this.hunger%2;
		x=(int) (0.52*w);
		y=(int) (0.5*h);
		for(int i=0;i<hunger;i++) {
			blit(matrixStack, x, y, 52, 27, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfhunger;i++) {
			blit(matrixStack, x, y, 62, 27, 9, 9, 256, 256);
			x+=10;
		}
		if(type==0)
			this.minecraft.getTextureManager().bind(FOOD1);
		else
			this.minecraft.getTextureManager().bind(FOOD2);
		int a=(int) (0.4*h);
		blit(matrixStack, (int)(0.3*w), (int)(0.3*h), 0, 0, a, a, a, a);
		if(type==0)
			drawCenteredString(matrixStack,this.font,new TranslationTextComponent("item.zelda.food"),(int)(0.55*w),(int)(0.4*h),16777215);
		else
			drawCenteredString(matrixStack,this.font,new TranslationTextComponent("item.zelda.hard_food"),(int)(0.55*w),(int)(0.4*h),16777215);
	}
}
