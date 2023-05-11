package com.majong.zelda.block;

import com.majong.zelda.Utils;
import com.majong.zelda.item.ItemLoader;

import majongmc.hllib.common.registry.DeferredRegister;
import majongmc.hllib.common.registry.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlockLoader {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, Utils.MOD_ID);
	public static final RegistryObject<Block> POT = BLOCKS.register("pot", PotBlock::new);
	public static final RegistryObject<Item> ITEM_POT = ItemLoader.ITEMS.register("pot",() -> new BlockItem(POT.get(), new Item.Properties()));
	public static final RegistryObject<Block> TEMPLE_ENTRY = BLOCKS.register("temple_entry", TempleEntryBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_ENTRY = ItemLoader.ITEMS.register("temple_entry",() -> new BlockItem(TEMPLE_ENTRY.get(), new Item.Properties()));
	public static final RegistryObject<Block> TEMPLE_START = BLOCKS.register("temple_start", TempleStartBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_START = ItemLoader.ITEMS.register("temple_start",() -> new BlockItem(TEMPLE_START.get(), new Item.Properties()));
	public static final RegistryObject<Block> TEMPLE_FINISH = BLOCKS.register("temple_finish", TempleFinishBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_FINISH = ItemLoader.ITEMS.register("temple_finish",() -> new BlockItem(TEMPLE_FINISH.get(), new Item.Properties()));
}
