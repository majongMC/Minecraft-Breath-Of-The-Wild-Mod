package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;

public class DialogBox extends Screen{
	private String[] content;
	private int page=0;
	private int currentframe=0;
	Button pagedown;
	protected DialogBox(Component titleIn,String[] content) {
		super(titleIn);
		this.content=content;
	}
	@Override
	protected void init() {
		this.pagedown = new Button((int)(this.width*0.1), (int)(this.height*0.6),(int)(this.width*0.8),(int)(this.height*0.3), new TranslatableComponent("gui." + Utils.MOD_ID + ".next"), (button) -> {onButtonActivated(button);});
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
		if(!ZeldaConfig.NPCONLY.get()&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {
		Minecraft.getInstance().getSoundManager().stop();
		EntitySpottedEvent.SoundRemainTime=0;
		Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.FIGHT_ORIGINAL.get(), SoundSource.AMBIENT, 10f, 1f);
		EntitySpottedEvent.SoundRemainTime=2200;
		}
		super.onClose();
	}
	@Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		fill(PoseStack,(int)(0.1*this.width), (int)(0.6*this.height),(int)(0.9*this.width),(int)(0.9*this.height), -2144193998);
		fill(PoseStack,(int)(0.1*this.width), (int)(0.6*this.height),(int)(0.9*this.width),(int)(0.6*this.height)+1, -1);
		fill(PoseStack,(int)(0.1*this.width), (int)(0.9*this.height),(int)(0.9*this.width),(int)(0.9*this.height)-1, -1);
		fill(PoseStack,(int)(0.1*this.width), (int)(0.6*this.height),(int)(0.1*this.width)+1,(int)(0.9*this.height), -1);
		fill(PoseStack,(int)(0.9*this.width), (int)(0.6*this.height),(int)(0.9*this.width)-1,(int)(0.9*this.height), -1);
		double speed=5*ClientUtils.fpsratio();
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
