package com.majong.zelda.gui;

import java.util.UUID;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.PackWithUUID;
import com.majong.zelda.network.Networking;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class ShikaStoneGui extends Screen{
	private final int w;
    private final int h;
    private Button bomb1,bomb2;
    //private final ResourceLocation ICONS=new ResourceLocation(Utils.MOD_ID, "textures/gui/shikastone.png");
	public ShikaStoneGui() {
		super(new TranslationTextComponent(""));
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
		// TODO 自动生成的构造函数存根
	}
	@Override
	protected void init() {
		this.bomb1 = new Button((int)(0.4*w-16), (int)(0.3*h), 32, 32, new TranslationTextComponent("炸弹1"), (button) -> {placebomb(true);});
		this.bomb2 = new Button((int)(0.45*w-16), (int)(0.3*h), 32, 32, new TranslationTextComponent("炸弹2"), (button) -> {placebomb(true);});
		this.addButton(bomb1);
		this.addButton(bomb2);
		super.init();
	}
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderBackground(matrixStack);
		this.bomb1.render(matrixStack, mouseX, mouseY, partialTicks);
		this.bomb2.render(matrixStack, mouseX, mouseY, partialTicks);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	private void placebomb(boolean round) {
		this.onClose();
		if(!ZeldaConfig.BOMB.get()) {
			Minecraft.getInstance().player.sendMessage(new TranslationTextComponent("msg.bombprohibited"), UUID.randomUUID());
			return;
		}
		if(round)
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(1));
		else
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(2));
	}
}
