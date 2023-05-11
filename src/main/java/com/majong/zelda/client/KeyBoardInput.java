package com.majong.zelda.client;

import org.lwjgl.glfw.GLFW;

import com.majong.zelda.Utils;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackToServer;
import com.mojang.blaze3d.platform.InputConstants;

import majongmc.hllib.client.event.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class KeyBoardInput {
	public static final KeyMapping SKILL_KEY = new KeyMapping("key.zelda.skill",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.category." + Utils.MOD_ID);
	public static final KeyMapping DETONATE_KEY = new KeyMapping("key.zelda.detonate",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.category." + Utils.MOD_ID);
	@Environment(value=EnvType.CLIENT)
    public static void onKeyboardInput(ClientTickEvent event) {
		if(SKILL_KEY.consumeClick()&&Minecraft.getInstance().player != null) {
			Networking.PACKTOSERVER.sendToServer(new PackToServer(0));
		}
		if(DETONATE_KEY.consumeClick()&&Minecraft.getInstance().player != null) {
			Networking.PACKTOSERVER.sendToServer(new PackToServer(3));
		}
	}
}
