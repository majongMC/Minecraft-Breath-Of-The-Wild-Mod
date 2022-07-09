package com.majong.zelda.client;

import org.lwjgl.glfw.GLFW;

import com.majong.zelda.Utils;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.PackWithUUID;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInput {
	public static final KeyMapping SKILL_KEY = new KeyMapping("key.skill",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.category." + Utils.MOD_ID);
	public static final KeyMapping DETONATE_KEY = new KeyMapping("key.detonate",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.category." + Utils.MOD_ID);
	@SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
		if(SKILL_KEY.consumeClick()&&Minecraft.getInstance().player != null) {
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(0));
		}
		if(DETONATE_KEY.consumeClick()&&Minecraft.getInstance().player != null) {
			Networking.PACKWITHUUID.sendToServer(new PackWithUUID(3));
		}
	}
}
