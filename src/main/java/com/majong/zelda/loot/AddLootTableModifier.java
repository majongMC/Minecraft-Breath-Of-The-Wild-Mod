package com.majong.zelda.loot;

import com.majong.zelda.item.ItemLoader;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class AddLootTableModifier
{
	public static void registeraddition() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
		    if(id.equals(BuiltInLootTables.ANCIENT_CITY)){
		    	LootPool.Builder poolBuilder = LootPool.lootPool()
		                .setRolls(ConstantValue.exactly(1))
		                .add(LootItem.lootTableItem(ItemLoader.ANCIENT_ARROW.get()));
		 
		    	tableBuilder.withPool(poolBuilder);
		    }
		});
	}
}
