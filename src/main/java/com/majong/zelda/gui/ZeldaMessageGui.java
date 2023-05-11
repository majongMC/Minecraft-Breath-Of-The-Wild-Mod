package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackToServer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ZeldaMessageGui extends Screen{
	private final int w;
    private final int h;
    private int heal,hunger,type;
    private float currentframe=0;
	private final ResourceLocation ICONS=new ResourceLocation("minecraft", "textures/gui/icons.png");
	private final ResourceLocation FOOD2=new ResourceLocation(Utils.MOD_ID, "textures/items/hard_food.png");
	private final ResourceLocation FOOD1=new ResourceLocation("minecraft", "textures/item/suspicious_stew.png");
	private final ResourceLocation SPIRIT_ORB=new ResourceLocation(Utils.MOD_ID, "textures/gui/spirit_orb.png");
	public ZeldaMessageGui(int type,int heal,int hunger) {
		super(Component.translatable(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.heal=heal;
        this.hunger=hunger;
        this.type=type;
	}
	@Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		if(currentframe<60) {
			RenderSystem.setShaderColor(1.0F*currentframe/60, 1.0F*currentframe/60, 1.0F*currentframe/60, 1.0F*currentframe/60);
			float deltay=0.3F*(60-currentframe)*Mth.sin(currentframe*Mth.PI/30);
			PoseStack.translate(0, deltay, 0);
		}else
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		fill(PoseStack,(int)(0.3*w), (int)(0.3*h),(int)(0.7*w),(int)(0.7*h), -2144193998);
		fill(PoseStack,(int)(0.3*w), (int)(0.3*h),(int)(0.7*w),(int)(0.3*h)+1, -1);
		fill(PoseStack,(int)(0.3*w), (int)(0.7*h),(int)(0.7*w),(int)(0.7*h)-1, -1);
		fill(PoseStack,(int)(0.3*w), (int)(0.3*h),(int)(0.3*w)+1,(int)(0.7*h), -1);
		fill(PoseStack,(int)(0.7*w), (int)(0.3*h),(int)(0.7*w)-1,(int)(0.7*h), -1);
		RenderSystem.setShaderTexture(0, ICONS);
		int x=(int) (0.52*w),y=(int) (0.45*h);
		if(heal<=10) {
		int heart,halfheart;
		heart=heal/2;
		halfheart=heal%2;
		for(int i=0;i<heart;i++) {
			blit(PoseStack, x, y, 52, 0, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfheart;i++) {
			blit(PoseStack, x, y, 62, 0, 9, 9, 256, 256);
			x+=10;
		}
		}else {
			blit(PoseStack, x, y, 52, 0, 9, 9, 256, 256);
			drawCenteredString(PoseStack,this.font,Component.translatable("x"+heal/2+""+(heal%2!=0?".5":"")),x+20,y,16777215);
			RenderSystem.setShaderTexture(0, ICONS);
		}
		x=(int) (0.52*w);
		y=(int) (0.5*h);
		if(hunger<=10) {
		int hunger,halfhunger;
		hunger=this.hunger/2;
		halfhunger=this.hunger%2;
		for(int i=0;i<hunger;i++) {
			blit(PoseStack, x, y, 52, 27, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfhunger;i++) {
			blit(PoseStack, x, y, 62, 27, 9, 9, 256, 256);
			x+=10;
		}
		}else {
			blit(PoseStack, x, y, 52, 27, 9, 9, 256, 256);
			drawCenteredString(PoseStack,this.font,Component.translatable("x"+hunger/2+""+(hunger%2!=0?".5":"")),x+20,y,16777215);
		}
		switch(type) {
		case 0:RenderSystem.setShaderTexture(0, FOOD1);;break;
		case 1:RenderSystem.setShaderTexture(0, FOOD2);;break;
		case 2:RenderSystem.setShaderTexture(0, SPIRIT_ORB);;break;
		}
		int a=(int) (0.4*h);
		blit(PoseStack, (int)(0.3*w), (int)(0.3*h), 0, 0, a, a, a, a);
		switch(type) {
		case 0:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.food"),(int)(0.55*w),(int)(0.4*h),16777215);break;
		case 1:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.hard_food"),(int)(0.55*w),(int)(0.4*h),16777215);break;
		case 2:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.spirit_orb"),(int)(0.55*w),(int)(0.4*h),16777215);break;
		}
		currentframe=(float) (currentframe+1/ClientUtils.fpsratio());
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
	@Override
	public void onClose() {
		super.onClose();
		if(this.type==2)
			Networking.PACKTOSERVER.sendToServer(new PackToServer(4));
	}
}
