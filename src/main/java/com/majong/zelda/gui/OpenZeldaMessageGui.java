package com.majong.zelda.gui;

import net.minecraft.client.Minecraft;

public class OpenZeldaMessageGui {
	public OpenZeldaMessageGui(int type,int heal,int hunger) {
		Minecraft.getInstance().setScreen(new ZeldaMessageGui(type,heal,hunger));
	}
}
