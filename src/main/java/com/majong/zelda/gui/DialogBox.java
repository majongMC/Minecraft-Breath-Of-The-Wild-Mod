package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;

public class DialogBox extends Screen{
	private ResourceLocation DIALOG_BOX=new ResourceLocation(Utils.MOD_ID, "textures/gui/background.png");
	private String[] content;
	private int page=0;
	private int currentframe=0;
	Button pagedown;
	protected DialogBox(Component titleIn,String[] content) {
		super(titleIn);
		this.content=content;
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	protected void init() {
		this.pagedown = new Button((int)(this.width*0.1), (int)(this.height*0.6),(int)(this.width*0.8),(int)(this.height*0.3), Component.translatable("gui." + Utils.MOD_ID + ".next"), (button) -> {onButtonActivated(button);});
		  this.addRenderableWidget(pagedown);
		  super.init();
	}
	private void onButtonActivated(Button button) {
		if(this.hasnextpage())
			this.nextpage();
		else
			this.onClose();
	}
	@Override
	public void onClose() {
		Minecraft.getInstance().getSoundManager().stop();
		EntitySpottedEvent.SoundRemainTime=0;
		Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.FIGHT_ORIGINAL.get(), SoundSource.AMBIENT, 10f, 1f);
		EntitySpottedEvent.SoundRemainTime=2200;
		super.onClose();
	}
	@Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DIALOG_BOX);
		//this.minecraft.getTextureManager().bindForSetup(DIALOG_BOX);
		this.blit(PoseStack, (int)(this.width*0.1), (int)(this.height*0.6), 0, 0,(int)(this.width*0.8),(int)(this.height*0.3), 16, 16);
		int speed=5;
		if((int)((currentframe)/speed)<content[page].length())
			drawCenteredString(PoseStack, this.font,content[page].substring(0, (int)((currentframe)/speed)), (int)(this.width*0.5),(int)(this.height*0.7), 16777215);
		else
			drawCenteredString(PoseStack, this.font,content[page], (int)(this.width*0.5),(int)(this.height*0.7), 16777215);
		currentframe++;
			
	}
    private boolean hasnextpage() {
		return content.length-1>page;
	}
    private boolean haslastpage() {
		return page!=0;
	}
	private void nextpage() {
		currentframe=0;
		page++;
	}
    private void lastpage() {
    	currentframe=0;
		page--;
	}
}
