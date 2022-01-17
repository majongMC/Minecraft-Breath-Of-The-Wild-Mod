package com.majong.zelda.api.util;

import com.majong.zelda.util.ConductiveItem;

import net.minecraft.item.Item;
public class ConductiveItemApi {
	//在FMLServerStartingEvent事件中使用以下方法注册导电物品(注册后当玩家在雷雨天手持或穿戴该物品时会引雷)
	//使用示例见com.majong.zelda.event.ServerStarting
	public static void registerConductiveItem(Item item) {
		ConductiveItem.CONDUCTIVE_ITEM.add(item);
	}
	//在FMLServerStartingEvent事件中使用以下方法移除导电物品(请使用最低的优先级)
	public static void removeConductiveItem(Item item) {
		if(ConductiveItem.CONDUCTIVE_ITEM.contains(item))
			ConductiveItem.CONDUCTIVE_ITEM.remove(item);
	}
}
