package com.majong.zelda.api.util;

import com.majong.zelda.util.ConductiveItem;

import net.minecraft.world.item.Item;
public class ConductiveItemApi {
	//不推荐使用，给物品添加zelda:conductive标签即可实现相同功能
	@Deprecated
	public static void registerConductiveItem(Item item) {
		ConductiveItem.CONDUCTIVE_ITEM.add(item);
	}
	@Deprecated
	public static void removeConductiveItem(Item item) {
		if(ConductiveItem.CONDUCTIVE_ITEM.contains(item))
			ConductiveItem.CONDUCTIVE_ITEM.remove(item);
	}
}
