package com.majong.zelda.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class Linkage {
	private static final String HINOX="iceandfire:cyclops";
	private static final String IGNIS="cataclysm:ignis";
	public static boolean isHinox(Entity entity) {
		return EntityType.getKey(entity.getType()).toString().equals(HINOX);
	}
	public static boolean isIgnis(Entity entity) {
		if(entity==null)
			return false;
		return EntityType.getKey(entity.getType()).toString().equals(IGNIS);
	}
}
