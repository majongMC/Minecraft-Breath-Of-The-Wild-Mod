package com.majong.zelda.util;

import java.util.Iterator;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

public class ModCheck {
	public static boolean isModLoaded(String modid) {
		Iterator<ModInfo> it=FMLLoader.getLoadingModList().getMods().iterator();
		while(it.hasNext()) {
			ModInfo info=it.next();
			if(info.getModId().equals(modid))
				return true;
		}
		return false;
	}
}
