package com.majong.zelda.api.tickutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class TickManager {
	private static final List<ITickable> SERVER_TICKER_POOL=new ArrayList<ITickable>();
	private static final List<ITickable> CLIENT_TICKER_POOL=new ArrayList<ITickable>();
	private static final List<ITickable> client_remove=new ArrayList<ITickable>();
	private static final List<ITickable> server_remove=new ArrayList<ITickable>();
	private static final List<ITickable> client_add=new ArrayList<ITickable>();
	private static final List<ITickable> server_add=new ArrayList<ITickable>();
	@SubscribeEvent
	public static void onServerTick(ServerTickEvent event) {
		if(!server_add.isEmpty()) {
			Iterator<ITickable> rem=server_add.iterator();
			while(rem.hasNext()) {
				SERVER_TICKER_POOL.add(rem.next());
			}
			server_add.clear();
		}
		Iterator<ITickable> it=SERVER_TICKER_POOL.iterator();
		while(it.hasNext()) {
			it.next().tick();
		}
		if(!server_remove.isEmpty()) {
			Iterator<ITickable> rem=server_remove.iterator();
			while(rem.hasNext()) {
				SERVER_TICKER_POOL.remove(rem.next());
			}
			server_remove.clear();
		}
	}
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent event) {
		if(!client_add.isEmpty()) {
			Iterator<ITickable> rem=client_add.iterator();
			while(rem.hasNext()) {
				CLIENT_TICKER_POOL.add(rem.next());
			}
			client_add.clear();
		}
		Iterator<ITickable> it=CLIENT_TICKER_POOL.iterator();
		while(it.hasNext()) {
			it.next().tick();
		}
		if(!client_remove.isEmpty()) {
			Iterator<ITickable> rem=client_remove.iterator();
			while(rem.hasNext()) {
				CLIENT_TICKER_POOL.remove(rem.next());
			}
			client_remove.clear();
		}
	}
	public static void registerTicker(ITickable ticker) {
		if(ticker.isclientside())
			client_add.add(ticker);
		else
			server_add.add(ticker);
	}
	public static void removeTicker(ITickable ticker) {
		if(ticker.isclientside())
			client_remove.add(ticker);
		else
			server_remove.add(ticker);
	}
}
