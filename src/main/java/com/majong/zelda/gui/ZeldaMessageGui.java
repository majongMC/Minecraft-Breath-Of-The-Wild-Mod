package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackWithUUID;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ZeldaMessageGui extends Screen{
	private final int w;
    private final int h;
    private int heal,hunger,type;
	private final ResourceLocation ICONS=new ResourceLocation("minecraft", "textures/gui/icons.png");
	private final ResourceLocation FOOD2=new ResourceLocation(Utils.MOD_ID, "textures/items/hard_food.png");
	private final ResourceLocation FOOD1=new ResourceLocation("minecraft", "textures/item/suspicious_stew.png");
	private final ResourceLocation SPIRIT_ORB=new ResourceLocation(Utils.MOD_ID, "textures/gui/spirit_orb.png");
	private final ResourceLocation BACK=new ResourceLocation(Utils.MOD_ID, "textures/gui/background.png");
	public ZeldaMessageGui(int type,int heal,int hunger) {
		super(Component.translatable(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.heal=heal;
        this.hunger=hunger;
        this.type=type;
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, BACK);
		//this.minecraft.getTextureManager().bind(BACK);
		blit(PoseStack, (int)(0.3*w), (int)(0.3*h), 0, 0, (int)(0.4*w),(int)(0.4*h),16,16);
		//this.minecraft.getTextureManager().bind(ICONS);
		RenderSystem.setShaderTexture(0, ICONS);
		int heart,halfheart;
		heart=heal/2;
		halfheart=heal%2;
		int x=(int) (0.52*w),y=(int) (0.45*h);
		for(int i=0;i<heart;i++) {
			blit(PoseStack, x, y, 52, 0, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfheart;i++) {
			blit(PoseStack, x, y, 62, 0, 9, 9, 256, 256);
			x+=10;
		}
		int hunger,halfhunger;
		hunger=this.hunger/2;
		halfhunger=this.hunger%2;
		x=(int) (0.52*w);
		y=(int) (0.5*h);
		for(int i=0;i<hunger;i++) {
			blit(PoseStack, x, y, 52, 27, 9, 9, 256, 256);
			x+=10;
		}
		for(int i=0;i<halfhunger;i++) {
			blit(PoseStack, x, y, 62, 27, 9, 9, 256, 256);
			x+=10;
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
	}
	@Override
	public void onClose() {
		super.onClose();
		if(this.type==2)
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(4));
	}
}
