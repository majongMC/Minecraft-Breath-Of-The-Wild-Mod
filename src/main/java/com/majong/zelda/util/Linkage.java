package com.majong.zelda.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class Linkage {
	private static final String HINOX="iceandfire:cyclops";
	public static boolean isHinox(Entity entity) {
		entity.getType();
		return EntityType.getKey(entity.getType()).toString().equals(HINOX);
	}
}
