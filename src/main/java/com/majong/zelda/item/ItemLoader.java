package com.majong.zelda.item;

import com.majong.zelda.Utils;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemLoader {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
	public static final RegistryObject<Item> ANCIENT_GEAR = ITEMS.register("ancient_gear", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_CORE = ITEMS.register("ancient_core", BasicItem::new);
	public static final RegistryObject<Item> BIG_ANCIENT_CORE = ITEMS.register("big_ancient_core", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SPRING = ITEMS.register("ancient_spring", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SHAFT = ITEMS.register("ancient_shaft", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SCREW = ITEMS.register("ancient_screw", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SHIELD = ITEMS.register("ancient_shield", AncientShield::new);
	public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow", AncientArrow::new);
	public static final RegistryObject<Item> ELECTRICITY_ARROW = ITEMS.register("electricity_arrow", ElectricityArrow::new);
	public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow", BombArrow::new);
	public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow", IceArrow::new);
	public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow", FireArrow::new);
	public static final RegistryObject<Item> FOOD = ITEMS.register("food", ZeldaFood::new);
	public static final RegistryObject<Item> HARD_FOOD = ITEMS.register("hard_food", HardFood::new);
	public static final RegistryObject<Item> SHIKA_STONE = ITEMS.register("shika_stone", ShikaStone::new);
}
