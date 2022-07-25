package com.majong.zelda.gui;

import net.minecraft.client.Minecraft;

public class OpenShikaStoneGui {
	public OpenShikaStoneGui() {
		Minecraft.getInstance().setScreen(new ShikaStoneGui());
	}
}
