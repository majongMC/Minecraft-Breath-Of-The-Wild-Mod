package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackWithUUID;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ShikaStoneGui extends Screen{
	private final int w;
    private final int h;
    private Button bomb1,bomb2,magnet,Static,ice,camera;
    //private ItemStack shikastone;
    private final ResourceLocation ICONS=new ResourceLocation(Utils.MOD_ID, "textures/gui/shikastonegui.png");
	public ShikaStoneGui() {
		super(Component.translatable(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        //this.shikastone=shikastone;
		// TODO �Զ����ɵĹ��캯�����
	}
	@Override
	protected void init() {
		this.bomb1 = new Button((int)(0.5*w-96), (int)(0.3*h), 32, 32, Component.translatable("ը��1"), (button) -> {placebomb(true);});
		this.bomb2 = new Button((int)(0.5*w-64), (int)(0.3*h), 32, 32, Component.translatable("ը��2"), (button) -> {placebomb(true);});
		this.addRenderableWidget(bomb1);
		this.addRenderableWidget(bomb2);
		this.magnet= new Button((int)(0.5*w-32), (int)(0.3*h), 32, 32, Component.translatable("����"), (button) -> {Networking.PACKWITHUUID.sendToServer(new PackWithUUID(5));this.onClose();});
		this.addRenderableWidget(magnet);
		this.Static= new Button((int)(0.5*w), (int)(0.3*h), 32, 32,Component.translatable("��ֹ��"), (button) -> {Networking.PACKWITHUUID.sendToServer(new PackWithUUID(6));this.onClose();});
		this.addRenderableWidget(Static);
		this.ice= new Button((int)(0.5*w+32), (int)(0.3*h), 32, 32, Component.translatable("�Ʊ���"), (button) -> {Networking.PACKWITHUUID.sendToServer(new PackWithUUID(7));this.onClose();});
		this.addRenderableWidget(ice);
		this.camera= new Button((int)(0.5*w+64), (int)(0.3*h), 32, 32, Component.translatable("�����"), (button) -> {Networking.PACKWITHUUID.sendToServer(new PackWithUUID(8));this.onClose();});
		this.addRenderableWidget(camera);
		super.init();
	}
	@Override
	public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderBackground(PoseStack);
		RenderSystem.setShaderTexture(0, ICONS);
		//this.minecraft.getTextureManager().bind(ICONS);
		blit(PoseStack, (int)(0.5*w-96), (int)(0.3*h), 0, 0, 192, 32, 192, 32);
		//blit(PoseStack, (int)(0.45*w-16), (int)(0.3*h), 128, 0, 32, 32, 32, 192);
		//blit(PoseStack, (int)(0.5*w-16), (int)(0.3*h), 256, 0, 32, 32, 32, 192);
		//blit(PoseStack, (int)(0.55*w-16), (int)(0.3*h), 384, 0, 32, 32, 32, 192);
		//blit(PoseStack, (int)(0.6*w-16), (int)(0.3*h), 512, 0, 32, 32, 32, 192);
		//blit(PoseStack, (int)(0.65*w-16), (int)(0.3*h), 640, 0, 32, 32, 32, 192);
		//super.render(PoseStack, mouseX, mouseY, partialTicks);
	}
	private void placebomb(boolean round) {
		this.onClose();
		if(!ZeldaConfig.BOMB.get()) {
			Minecraft.getInstance().player.sendSystemMessage(Component.translatable("msg.bombprohibited"));
			return;
		}
		if(round)
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(1));
		else
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(2));
	}
}
