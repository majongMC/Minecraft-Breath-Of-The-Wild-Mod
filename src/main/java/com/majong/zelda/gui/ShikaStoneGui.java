package com.majong.zelda.gui;

import com.majong.zelda.Utils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackToServer;
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
		this.bomb1 = Button.builder(Component.empty(), (button) -> {placebomb(true);}).bounds((int)(0.5*w-96), (int)(0.3*h), 32, 32).build();
		this.bomb2 = Button.builder(Component.empty(), (button) -> {placebomb(true);}).bounds((int)(0.5*w-64), (int)(0.3*h), 32, 32).build();
		this.addRenderableWidget(bomb1);
		this.addRenderableWidget(bomb2);
		this.magnet= Button.builder(Component.empty(), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(5));this.onClose();}).bounds((int)(0.5*w-32), (int)(0.3*h), 32, 32).build();
		this.addRenderableWidget(magnet);
		this.Static= Button.builder(Component.empty(), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(6));this.onClose();}).bounds((int)(0.5*w), (int)(0.3*h), 32, 32).build();
		this.addRenderableWidget(Static);
		this.ice= Button.builder(Component.empty(), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(7));this.onClose();}).bounds((int)(0.5*w+32), (int)(0.3*h), 32, 32).build();
		this.addRenderableWidget(ice);
		this.camera= Button.builder(Component.empty(), (button) -> {Networking.PACKTOSERVER.sendToServer(new PackToServer(8));this.onClose();}).bounds((int)(0.5*w+64), (int)(0.3*h), 32, 32).build();
		this.addRenderableWidget(camera);
		super.init();
	}
	@Override
	public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderBackground(PoseStack);
		RenderSystem.setShaderTexture(0, ICONS);
		blit(PoseStack, (int)(0.5*w-96), (int)(0.3*h), 0, 0, 192, 32, 192, 32);
		//test
	}
	private void placebomb(boolean round) {
		this.onClose();
		if(!ZeldaConfig.BOMB.get()) {
			Minecraft.getInstance().player.sendSystemMessage(Component.translatable("msg.bombprohibited"));
			return;
		}
		if(round)
			Networking.PACKTOSERVER.sendToServer(new PackToServer(1));
		else
			Networking.PACKTOSERVER.sendToServer(new PackToServer(2));
	}
}
