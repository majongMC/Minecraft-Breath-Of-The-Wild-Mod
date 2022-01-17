package com.majong.zelda.event;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.ConductiveItem;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class PlayerTick {
	public static final Map<PlayerEntity,Integer> THUNDER_COUNT_TIME=new HashMap<>();
	@SubscribeEvent
	public static void onPlayerTick(LivingUpdateEvent event) {
		if(event.getEntity() instanceof PlayerEntity&&!event.getEntity().world.isRemote) {
			PlayerEntity player=(PlayerEntity) event.getEntity();
			//player.sendMessage(new TranslationTextComponent("yaw:"+player.rotationYawHead), UUID.randomUUID());
			//DataManager.preventnull(player);
			for(int i=0;i<4;i++) {
			if(DataManager.data.get(player).unlocked[i]&&DataManager.data.get(player).skill[i]==0) {
				DataManager.data.get(player).cd[i]--;
				if(DataManager.data.get(player).cd[i]%20==0&&DataManager.data.get(player).cd[i]>0)
					DataManager.sendzeldaplayerdatapack(player);
				if(DataManager.data.get(player).cd[i]<=0) {
					switch(i) {
					case 0:DataManager.data.get(player).cd[i]=ZeldaConfig.WATER.get();break;
					case 1:DataManager.data.get(player).cd[i]=ZeldaConfig.WIND.get();break;
					case 2:DataManager.data.get(player).cd[i]=ZeldaConfig.FIRE.get();break;
					case 3:DataManager.data.get(player).cd[i]=ZeldaConfig.THUNDER.get();break;
					}
					if(i==0)
						DataManager.data.get(player).skill[i]=1;
					else
						DataManager.data.get(player).skill[i]=3;
					DataManager.sendzeldaplayerdatapack(player);
				}
			}
		}
			if(player.world.isThundering()) {
				if(ConductiveItem.HeldConductiveItem(player)&&isOutdoors(player)) {
					if(THUNDER_COUNT_TIME.get(player)==0) {
						LightningBoltEntity lightning=new LightningBoltEntity(EntityType.LIGHTNING_BOLT,player.world);
		    			lightning.setPosition(player.getPosX(), player.getPosY()+1, player.getPosZ());
		    			player.world.addEntity(lightning);
		    			THUNDER_COUNT_TIME.put(player,100);
					}
					else {
						Networking.PARTICLE.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) player
			                    ),
			                    new ParticlePack(1,player.getPosX()-0.5+Math.random(),player.getPosY()+0.5+Math.random(),player.getPosZ()-0.5+Math.random(),0,0,0));
						THUNDER_COUNT_TIME.put(player,THUNDER_COUNT_TIME.get(player)-1);
					}
				}
				else
					THUNDER_COUNT_TIME.put(player,100);
			}
	}
		if(event.getEntity() instanceof PlayerEntity&&event.getEntity().world.isRemote) {
			if(PlayerSpottedEvent.SoundRemainTime>0)
				PlayerSpottedEvent.SoundRemainTime--;
		}
}
	private static boolean isOutdoors(PlayerEntity player) {
		BlockPos pos=player.getPosition();
		for(int i=pos.getY()+1;i<256;i++) {
			if(!player.world.getBlockState(new BlockPos(pos.getX(),i,pos.getZ())).isAir())
				return false;
		}
		return true;
	}
	@SubscribeEvent
	public static void onPlayerClone(Clone event) {
		//DataManager.preventnull(event.getOriginal());
		DataManager.preventnull(event.getPlayer());
		DataManager.writefromnbt(DataManager.readtonbt(event.getOriginal()), event.getPlayer());
		//DataManager.removedata(event.getOriginal());
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.put(event.getPlayer(),0L);
		//PlayerUseShield.PLAYER_LAST_USE_SHIELD.remove(event.getOriginal());
		PlayerTick.THUNDER_COUNT_TIME.put(event.getPlayer(), 100);
		//PlayerTick.THUNDER_COUNT_TIME.remove(event.getOriginal());
	}
}
