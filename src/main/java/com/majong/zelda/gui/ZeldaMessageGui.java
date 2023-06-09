package com.majong.zelda.gui;

import org.joml.Vector3f;

import com.majong.zelda.Utils;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackToServer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.render.Circle;
import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.render.RenderShapeInGUI;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ZeldaMessageGui extends Screen{
    private int heal,hunger,type;
    private float currentframe=0;
	private final ResourceLocation ICONS=new ResourceLocation("minecraft", "textures/gui/icons.png");
	private final ResourceLocation FOOD2=new ResourceLocation(Utils.MOD_ID, "textures/items/hard_food.png");
	private final ResourceLocation FOOD1=new ResourceLocation("minecraft", "textures/item/suspicious_stew.png");
	private final ResourceLocation SPIRIT_ORB=new ResourceLocation(Utils.MOD_ID, "textures/gui/spirit_orb.png");
	private final Color SIDE=new Color(255,255,0,0);
	public ZeldaMessageGui(int type,int heal,int hunger) {
		super(Component.translatable(""));
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
		fill(PoseStack,(int)(0.3*width), (int)(0.3*height),(int)(0.7*width),(int)(0.7*height), -2144193998);
		fill(PoseStack,(int)(0.3*width), (int)(0.3*height),(int)(0.7*width),(int)(0.3*height)+1, -1);
		fill(PoseStack,(int)(0.3*width), (int)(0.7*height),(int)(0.7*width),(int)(0.7*height)-1, -1);
		fill(PoseStack,(int)(0.3*width), (int)(0.3*height),(int)(0.3*width)+1,(int)(0.7*height), -1);
		fill(PoseStack,(int)(0.7*width), (int)(0.3*height),(int)(0.7*width)-1,(int)(0.7*height), -1);
		RenderShapeInGUI.renderRadiation(PoseStack, new Circle(new Vector3f(0.4F*width,0.5F*height,0F),RenderShapeInGUI.GUINORMAL,0.2F*height), Color.YELLOW, SIDE, 42, 2, Mth.PI*currentframe/240F);
		RenderSystem.setShaderTexture(0, ICONS);
		int x=(int) (0.52*width),y=(int) (0.45*height);
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
		x=(int) (0.52*width);
		y=(int) (0.5*height);
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
		int a=(int) (0.4*height);
		blit(PoseStack, (int)(0.4*width)-(int) (0.2*height), (int)(0.3*height), 0, 0, a, a, a, a);
		switch(type) {
		case 0:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.food"),(int)(0.55*width),(int)(0.4*height),16777215);break;
		case 1:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.hard_food"),(int)(0.55*width),(int)(0.4*height),16777215);break;
		case 2:drawCenteredString(PoseStack,this.font,Component.translatable("item.zelda.spirit_orb"),(int)(0.55*width),(int)(0.4*height),16777215);break;
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
