package com.majong.zelda.item;

import com.majong.zelda.Utils;
import com.majong.zelda.entity.AncientArrowEntity;
import com.majong.zelda.entity.BombArrowEntity;
import com.majong.zelda.entity.ElectricityArrowEntity;
import com.majong.zelda.entity.FireArrowEntity;
import com.majong.zelda.entity.IceArrowEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
	public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO 自动生成的方法存根
			ArrowEntity arrowentity = new AncientArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> ELECTRICITY_ARROW = ITEMS.register("electricity_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO 自动生成的方法存根
			ArrowEntity arrowentity = new ElectricityArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO 自动生成的方法存根
			ArrowEntity arrowentity = new BombArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO 自动生成的方法存根
			ArrowEntity arrowentity = new IceArrowEntity(worldIn, shooter);
		    arrowentity.setEffectsFromItem(stack);
		    return arrowentity;
		}});
	public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow", ()->new AbstractAttributeArrow() {

		@Override
		public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
			// TODO 自动生成的方法存根
			ArrowEntity arrowentity = new FireArrowEntity(worldIn, shooter);
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
}
