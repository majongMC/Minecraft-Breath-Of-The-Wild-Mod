package com.majong.zelda.block;

import com.majong.zelda.Utils;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockLoader {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);
	public static final RegistryObject<Block> POT = BLOCKS.register("pot", PotBlock::new);
	public static final RegistryObject<Item> ITEM_POT = ItemLoader.ITEMS.register("pot",() -> new BlockItem(POT.get(), new Item.Properties().group(Utils.ZELDA_CREATIVE_TAB)));
}
