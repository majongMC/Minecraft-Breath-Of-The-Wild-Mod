package com.majong.zelda.tag;

import com.majong.zelda.Utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTag {
	public static final TagKey<Block> CANT_MOVE =create("cant_move");
    private static TagKey<Block> create(String p_215896_) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Utils.MOD_ID,p_215896_));
     }
}
