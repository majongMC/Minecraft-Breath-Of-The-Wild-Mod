package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.AncientArrowEntity;
import com.majong.zelda.entity.BombArrowEntity;
import com.majong.zelda.entity.ElectricityArrowEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.FireArrowEntity;
import com.majong.zelda.entity.IceArrowEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemLoader {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
	public static final RegistryObject<Item> ANCIENT_GEAR = ITEMS.register("ancient_gear", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_CORE = ITEMS.register("ancient_core", BasicItem::new);
	public static final RegistryObject<Item> BIG_ANCIENT_CORE = ITEMS.register("big_ancient_core", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SPRING = ITEMS.register("ancient_spring", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SHAFT = ITEMS.register("ancient_shaft", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SCREW = ITEMS.register("ancient_screw", BasicItem::new);
	public static final RegistryObject<Item> ANCIENT_SHIELD = ITEMS.register("ancient_shield", AncientShield::new);
	public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO �Զ����ɵķ������
			Arrow arrowentity = new AncientArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> ELECTRICITY_ARROW = ITEMS.register("electricity_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO �Զ����ɵķ������
			Arrow arrowentity = new ElectricityArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO �Զ����ɵķ������
			Arrow arrowentity = new BombArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO �Զ����ɵķ������
			Arrow arrowentity = new IceArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO �Զ����ɵķ������
			Arrow arrowentity = new FireArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    arrowentity.setSecondsOnFire(32767);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> FOOD = ITEMS.register("food", ZeldaFood::new);
	public static final RegistryObject<Item> HARD_FOOD = ITEMS.register("hard_food", HardFood::new);
	public static final RegistryObject<Item> SHIKA_STONE = ITEMS.register("shika_stone", ShikaStone::new);
	public static final RegistryObject<Item> HORN = ITEMS.register("horn", HornItem::new);
	public static final RegistryObject<Item> RED_ENVELOPE = ITEMS.register("red_envelope", RedEnvelope::new);
	public static final RegistryObject<Item> SPIRIT_ORB = ITEMS.register("spirit_orb", BasicItem::new);
	public static final RegistryObject<Item> HEART_CONTAINER = ITEMS.register("heart_container", HeartContainer::new);
	public static final RegistryObject<Item> ANCIENT_HORN = ITEMS.register("ancient_horn", AncientHorn::new);
	public static final RegistryObject<Item> CHOPPING_WIND_BLADE = ITEMS.register("chopping_wind_blade", ChoppingWindBladeItem::new);
	public static final RegistryObject<Item> GUARDIAN_SPAWN_EGG = ITEMS.register("guardian_spawn_egg", 
            () -> new ForgeSpawnEggItem(EntityLoader.GUARDIAN, 3515354, 920940, new Item.Properties()));
	public static final RegistryObject<Item> WALKING_GUARDIAN_SPAWN_EGG = ITEMS.register("walking_guardian_spawn_egg", 
            () -> new ForgeSpawnEggItem(EntityLoader.WALKING_GUARDIAN, 3515354, 920940, new Item.Properties()));
	public static final RegistryObject<Item> MOLLY_BRIN_SPAWN_EGG = ITEMS.register("molly_brin_spawn_egg", 
            () -> new ForgeSpawnEggItem(EntityLoader.MOLLY_BRIN, 15000806, 4671303, new Item.Properties()));
	public static final RegistryObject<Item> BOKO_BRIN_SPAWN_EGG = ITEMS.register("boko_brin_spawn_egg", 
            () -> new ForgeSpawnEggItem(EntityLoader.BOKO_BRIN, 6912441, 12266, new Item.Properties()));
	public static final RegistryObject<Item> ROCK_GIANT_SPAWN_EGG = ITEMS.register("rock_giant_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityLoader.ROCK_GIANT, 13948116, 7631988, new Item.Properties()));
	public static final RegistryObject<Item> YIGA_TEAM_MEMBER_SPAWN_EGG = ITEMS.register("yiga_team_member_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityLoader.YIGA_TEAM_MEMBER, 16299861, 14431027, new Item.Properties()));
	public static final RegistryObject<Item> LYNEL_SPAWN_EGG = ITEMS.register("lynel_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityLoader.LYNEL, 13413697, 9860685, new Item.Properties()));
	public static final RegistryObject<Item> BEAST_GOD_SWORD = ITEMS.register("beast_god_sword", BeastGodSword::new);
	public static final RegistryObject<Item> BEAST_GOD_BOW = ITEMS.register("beast_god_bow", BeastGodBow::new);
	public static final RegistryObject<Item> GUIDE_BOOK = ITEMS.register("guide_book", GuideBook::new);
}
