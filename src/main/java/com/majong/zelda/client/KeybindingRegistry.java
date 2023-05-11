package com.majong.zelda.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class KeybindingRegistry {
    public static void onClientSetup() {
		KeyBindingHelper.registerKeyBinding(KeyBoardInput.SKILL_KEY);
		KeyBindingHelper.registerKeyBinding(KeyBoardInput.DETONATE_KEY);
    }
}
