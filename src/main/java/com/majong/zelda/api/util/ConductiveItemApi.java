package com.majong.zelda.api.util;

import com.majong.zelda.util.ConductiveItem;

import net.minecraft.item.Item;
public class ConductiveItemApi {
	//���Ƽ�ʹ�ã�����Ʒ���zelda:conductive��ǩ����ʵ����ͬ����
	//��FMLServerStartingEvent�¼���ʹ�����·���ע�ᵼ����Ʒ(ע���������������ֳֻ򴩴�����Ʒʱ������)
	//ʹ��ʾ����com.majong.zelda.event.ServerStarting
	@Deprecated
	public static void registerConductiveItem(Item item) {
		ConductiveItem.CONDUCTIVE_ITEM.add(item);
	}
	//���Ƽ�ʹ�ã���д���ݰ�����ʵ���Ƴ�
	//��FMLServerStartingEvent�¼���ʹ�����·����Ƴ�������Ʒ(��ʹ����͵����ȼ�)
	@Deprecated
	public static void removeConductiveItem(Item item) {
		if(ConductiveItem.CONDUCTIVE_ITEM.contains(item))
			ConductiveItem.CONDUCTIVE_ITEM.remove(item);
	}
}
