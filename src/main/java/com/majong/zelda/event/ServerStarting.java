package com.majong.zelda.event;

import com.majong.zelda.api.util.AttributeDamageApi;
import com.majong.zelda.api.util.ConductiveItemApi;
import com.majong.zelda.entity.GuardianEntity;

import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber()
public class ServerStarting {
	@SubscribeEvent
	public static void onServerAboutToStartStarting(FMLServerAboutToStartEvent event) {
		ServerTick.server=event.getServer();
	}
	@SubscribeEvent
	public static void onServerStarting(FMLServerStartingEvent event) {
		AttributeDamageApi.registerrestraint(AttributeDamageApi.ICE_RESTRAINTED, BlazeEntity.class);
		AttributeDamageApi.registerrestraint(AttributeDamageApi.FIRE_RESTRAINTED, StrayEntity.class);
		AttributeDamageApi.registerrestraint(AttributeDamageApi.ANCIENT_RESTRAINTED, GuardianEntity.class);
		registerConductiveItem();
	}
	private static void registerConductiveItem() {
		ConductiveItemApi.registerConductiveItem(Items.IRON_BLOCK);
		ConductiveItemApi.registerConductiveItem(Items.IRON_INGOT);
		ConductiveItemApi.registerConductiveItem(Items.IRON_NUGGET);
		ConductiveItemApi.registerConductiveItem(Items.IRON_SWORD);
		ConductiveItemApi.registerConductiveItem(Items.IRON_AXE);
		ConductiveItemApi.registerConductiveItem(Items.IRON_PICKAXE);
		ConductiveItemApi.registerConductiveItem(Items.IRON_HOE);
		ConductiveItemApi.registerConductiveItem(Items.IRON_SHOVEL);
		ConductiveItemApi.registerConductiveItem(Items.IRON_HELMET);
		ConductiveItemApi.registerConductiveItem(Items.IRON_CHESTPLATE);
		ConductiveItemApi.registerConductiveItem(Items.IRON_LEGGINGS);
		ConductiveItemApi.registerConductiveItem(Items.IRON_BOOTS);
		ConductiveItemApi.registerConductiveItem(Items.GOLD_BLOCK);
		ConductiveItemApi.registerConductiveItem(Items.GOLD_INGOT);
		ConductiveItemApi.registerConductiveItem(Items.GOLD_NUGGET);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_SWORD);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_AXE);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_PICKAXE);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_HOE);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_SHOVEL);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_HELMET);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_CHESTPLATE);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_LEGGINGS);
		ConductiveItemApi.registerConductiveItem(Items.GOLDEN_BOOTS);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_INGOT);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_SWORD);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_AXE);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_PICKAXE);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_HOE);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_SHOVEL);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_HELMET);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_CHESTPLATE);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_LEGGINGS);
		ConductiveItemApi.registerConductiveItem(Items.NETHERITE_BOOTS);
	}
}
