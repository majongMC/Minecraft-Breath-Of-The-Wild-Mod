package com.majong.zelda.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class Linkage {
	private static final String HINOX="iceandfire:cyclops";
	public static boolean isHinox(Entity entity) {
		entity.getType();
		return EntityType.getKey(entity.getType()).toString().equals(HINOX);
	}
}
