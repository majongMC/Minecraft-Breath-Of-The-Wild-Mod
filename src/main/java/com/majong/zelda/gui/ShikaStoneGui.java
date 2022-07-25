package com.majong.zelda.gui;

import java.util.UUID;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackToServer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ShikaStoneGui extends Screen{
	private final int w;
    private final int h;
    private Button bomb1,bomb2,magnet,Static,ice,camera;
    //private ItemStack shikastone;
    private final ResourceLocation ICONS=new ResourceLocation(Utils.MOD_ID, "textures/gui/shikastonegui.png");
	public ShikaStoneGui() {
		super(new TranslationTextComponent(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        //this.shikastone=shikastone;
		// TODO 自动生成的构造函数存根
	}
	@Override
	protected void init() {
		this.bomb1 = new Button((int)(0.5*w-96), (int)(0.3*h), 32, 32, new TranslationTextComponent("炸弹1"), (button) -> {placebomb(true);});
		this.bomb2 = new Button((int)(0.5*w-64), (int)(0.3*h), 32, 32, new TranslationTextComponent("炸弹2"), (button) -> {placebomb(true);});
		this.addButton(bomb1);
		this.addButton(bomb2);
		this.magnet= new Button((int)(0.5*w-32), (int)(0.3*h), 32, 32, new TranslationTextComponent("磁力"), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(5));this.onClose();});
		this.addButton(magnet);
		this.Static= new Button((int)(0.5*w), (int)(0.3*h), 32, 32, new TranslationTextComponent("静止器"), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(6));this.onClose();});
		this.addButton(Static);
		this.ice= new Button((int)(0.5*w+32), (int)(0.3*h), 32, 32, new TranslationTextComponent("制冰器"), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(7));this.onClose();});
		this.addButton(ice);
		this.camera= new Button((int)(0.5*w+64), (int)(0.3*h), 32, 32, new TranslationTextComponent("照相机"), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(8));this.onClose();});
		this.addButton(camera);
		super.init();
	}
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderBackground(matrixStack);
		this.minecraft.getTextureManager().bind(ICONS);
		blit(matrixStack, (int)(0.5*w-96), (int)(0.3*h), 0, 0, 192, 32, 192, 32);
		//blit(matrixStack, (int)(0.45*w-16), (int)(0.3*h), 128, 0, 32, 32, 32, 192);
		//blit(matrixStack, (int)(0.5*w-16), (int)(0.3*h), 256, 0, 32, 32, 32, 192);
		//blit(matrixStack, (int)(0.55*w-16), (int)(0.3*h), 384, 0, 32, 32, 32, 192);
		//blit(matrixStack, (int)(0.6*w-16), (int)(0.3*h), 512, 0, 32, 32, 32, 192);
		//blit(matrixStack, (int)(0.65*w-16), (int)(0.3*h), 640, 0, 32, 32, 32, 192);
		//super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	private void placebomb(boolean round) {
		this.onClose();
		if(!ZeldaConfig.BOMB.get()) {
			Minecraft.getInstance().player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
		if(round)
			Networking.PACKTOSERVER.sendToServer(new PackToServer(1));
		else
			Networking.PACKTOSERVER.sendToServer(new PackToServer(2));
	}
}
