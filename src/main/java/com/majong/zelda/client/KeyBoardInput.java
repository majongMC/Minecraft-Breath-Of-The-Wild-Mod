package com.majong.zelda.client;

import org.lwjgl.glfw.GLFW;

import com.majong.zelda.Utils;
import com.majong.zelda.network.KeyPack;
import com.majong.zelda.network.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInput {
	public static final KeyBinding SKILL_KEY = new KeyBinding("key.skill",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.category." + Utils.MOD_ID);
	public static final KeyBinding DETONATE_KEY = new KeyBinding("key.detonate",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.category." + Utils.MOD_ID);
	@SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
		if(SKILL_KEY.isPressed()&&Minecraft.getInstance().player != null) {
			Networking.KEYPACK.sendToServer(new KeyPack(0));
		}
		if(DETONATE_KEY.isPressed()&&Minecraft.getInstance().player != null) {
			Networking.KEYPACK.sendToServer(new KeyPack(3));
		}
	}
}
