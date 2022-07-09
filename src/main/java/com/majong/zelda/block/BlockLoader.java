package com.majong.zelda.block;

import com.majong.zelda.Utils;
import com.majong.zelda.item.ItemLoader;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockLoader {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);
	public static final RegistryObject<Block> POT = BLOCKS.register("pot", PotBlock::new);
	public static final RegistryObject<Item> ITEM_POT = ItemLoader.ITEMS.register("pot",() -> new BlockItem(POT.get(), new Item.Properties().tab(Utils.ZELDA_CREATIVE_TAB)));
	public static final RegistryObject<Block> TEMPLE_ENTRY = BLOCKS.register("temple_entry", TempleEntryBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_ENTRY = ItemLoader.ITEMS.register("temple_entry",() -> new BlockItem(TEMPLE_ENTRY.get(), new Item.Properties().tab(Utils.ZELDA_CREATIVE_TAB)));
	public static final RegistryObject<Block> TEMPLE_START = BLOCKS.register("temple_start", TempleStartBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_START = ItemLoader.ITEMS.register("static_temple_start",() -> new BlockItem(TEMPLE_START.get(), new Item.Properties().tab(Utils.ZELDA_CREATIVE_TAB)));
	public static final RegistryObject<Block> STATIC_TEMPLE_START = BLOCKS.register("static_temple_start", StaticTempleStartBlock::new);
	public static final RegistryObject<Item> ITEM_STATIC_TEMPLE_START = ItemLoader.ITEMS.register("temple_start",() -> new BlockItem(STATIC_TEMPLE_START.get(), new Item.Properties().tab(Utils.ZELDA_CREATIVE_TAB)));
	public static final RegistryObject<Block> TEMPLE_FINISH = BLOCKS.register("temple_finish", TempleFinishBlock::new);
	public static final RegistryObject<Item> ITEM_TEMPLE_FINISH = ItemLoader.ITEMS.register("temple_finish",() -> new BlockItem(TEMPLE_FINISH.get(), new Item.Properties().tab(Utils.ZELDA_CREATIVE_TAB)));
}
