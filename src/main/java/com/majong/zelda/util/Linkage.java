package com.majong.zelda.util;

import com.github.alexthe666.iceandfire.entity.EntityCyclops;

import net.minecraft.entity.Entity;

public class Linkage {
	public static boolean isHinox(Entity entity) {
		return entity instanceof EntityCyclops;
	}
}
