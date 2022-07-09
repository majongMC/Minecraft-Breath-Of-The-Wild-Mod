package com.majong.zelda.api.util;

import com.majong.zelda.util.ConductiveItem;

import net.minecraft.world.item.Item;
public class ConductiveItemApi {
	//��FMLServerStartingEvent�¼���ʹ�����·���ע�ᵼ����Ʒ(ע���������������ֳֻ򴩴�����Ʒʱ������)
	//ʹ��ʾ����com.majong.zelda.event.ServerStarting
	public static void registerConductiveItem(Item item) {
		ConductiveItem.CONDUCTIVE_ITEM.add(item);
	}
	//��FMLServerStartingEvent�¼���ʹ�����·����Ƴ�������Ʒ(��ʹ����͵����ȼ�)
	public static void removeConductiveItem(Item item) {
		if(ConductiveItem.CONDUCTIVE_ITEM.contains(item))
			ConductiveItem.CONDUCTIVE_ITEM.remove(item);
	}
}
