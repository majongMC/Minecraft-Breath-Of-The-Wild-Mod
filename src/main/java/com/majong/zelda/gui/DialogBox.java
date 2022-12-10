package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DialogBox extends Screen{
	private ResourceLocation DIALOG_BOX=new ResourceLocation(Utils.MOD_ID, "textures/gui/background.png");
	private String[] content;
	private int page=0;
	private int currentframe=0;
	Button pagedown;
	protected DialogBox(ITextComponent titleIn,String[] content) {
		super(titleIn);
		this.content=content;
		// TODO 自动生成的构造函数存根
	}
	@Override
	protected void init() {
		this.pagedown = new Button((int)(this.width*0.1), (int)(this.height*0.6),(int)(this.width*0.8),(int)(this.height*0.3), new TranslationTextComponent("gui." + Utils.MOD_ID + ".next"), (button) -> {onButtonActivated(button);});
		  this.addButton(pagedown);
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
		Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.FIGHT_ORIGINAL.get(), SoundCategory.AMBIENT, 10f, 1f);
		EntitySpottedEvent.SoundRemainTime=2200;
		}
		super.onClose();
	}
	@Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(DIALOG_BOX);
		this.blit(matrixStack, (int)(this.width*0.1), (int)(this.height*0.6), 0, 0,(int)(this.width*0.8),(int)(this.height*0.3), (int)(this.width*0.8), (int)(this.height*0.3));
		double speed=5*ClientUtils.fpsratio();
		if((int)((currentframe)/speed)<content[page].length())
			drawCenteredString(matrixStack, this.font,content[page].substring(0, (int)((currentframe)/speed)), (int)(this.width*0.5),(int)(this.height*0.7), 16777215);
		else
			drawCenteredString(matrixStack, this.font,content[page], (int)(this.width*0.5),(int)(this.height*0.7), 16777215);
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
