package com.majong.zelda.tag;

import com.majong.zelda.Utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTag {
	public static final TagKey<Item> CONDUCTIVE =create("conductive");
    private static TagKey<Item> create(String p_215896_) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(Utils.MOD_ID,p_215896_));
     }
}
