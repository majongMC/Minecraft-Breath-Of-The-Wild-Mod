package com.majong.zelda.util;

import net.fabricmc.loader.api.FabricLoader;

public class ModCheck {
	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}
