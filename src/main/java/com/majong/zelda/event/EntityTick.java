package com.majong.zelda.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.ConductiveItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntityTick {
	public static final Map<PlayerEntity,Integer> THUNDER_COUNT_TIME=new HashMap<>();
	public static final Map<PlayerEntity,BlockPos> LAST_STAND_POS=new HashMap<>();
	@SubscribeEvent
	public static void onEntityTick(LivingUpdateEvent event) {
		if(event.getEntity() instanceof PlayerEntity&&!event.getEntity().level.isClientSide) {
			PlayerEntity player=(PlayerEntity) event.getEntity();
			ZeldaPlayerData playerdata=DataManager.data.get(player);
			if(playerdata.intemple>1) {
				if(player.isOnGround()&&!player.level.getBlockState(player.blockPosition().offset(0,-1,0)).isAir())
					LAST_STAND_POS.put(player, player.blockPosition());
				if(player.blockPosition().getY()<0&&LAST_STAND_POS.containsKey(player)) {
					BlockPos pos=LAST_STAND_POS.get(player);
					player.addEffect(new EffectInstance(Effects.SLOW_FALLING,60,8));
					player.teleportTo(pos.getX(),pos.getY(), pos.getZ());
					player.hurt(DamageSource.OUT_OF_WORLD,2);
				}
			}
			for(int i=0;i<4;i++) {
			if(playerdata.skill[i]==0) {
				playerdata.cd[i]--;
				if(playerdata.cd[i]%20==0&&playerdata.cd[i]>0)
					DataManager.sendzeldaplayerdatapack(player);
				if(playerdata.cd[i]<=0) {
					switch(i) {
					case 0:playerdata.cd[i]=ZeldaConfig.WATER.get();break;
					case 1:playerdata.cd[i]=ZeldaConfig.WIND.get();break;
					case 2:playerdata.cd[i]=ZeldaConfig.FIRE.get();break;
					case 3:playerdata.cd[i]=ZeldaConfig.THUNDER.get();break;
					}
					if(i==0)
						playerdata.skill[i]=1;
					else
						playerdata.skill[i]=3;
					DataManager.sendzeldaplayerdatapack(player);
				}
			}
		}
			if(player.level.isThundering()) {
				if(ConductiveItem.HeldConductiveItem(player)&&isOutdoors(player)) {
					if(THUNDER_COUNT_TIME.get(player)==0) {
						LightningBoltEntity lightning=new LightningBoltEntity(EntityType.LIGHTNING_BOLT,player.level);
		    			lightning.setPos(player.getX(), player.getY()+1, player.getZ());
		    			player.level.addFreshEntity(lightning);
		    			THUNDER_COUNT_TIME.put(player,100);
					}
					else {
						Networking.PARTICLE.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) player
			                    ),
			                    new ParticlePack(1,player.getX()-0.5+Math.random(),player.getY()+0.5+Math.random(),player.getZ()-0.5+Math.random(),0,0,0));
						THUNDER_COUNT_TIME.put(player,THUNDER_COUNT_TIME.get(player)-1);
					}
				}
				else
					THUNDER_COUNT_TIME.put(player,100);
			}
	}
		if(Math.random()<ZeldaConfig.YIGATEAM.get()*0.00002&&event.getEntity() instanceof VillagerEntity&&!event.getEntity().level.isClientSide) {
			List<LivingEntity> list=event.getEntity().level.getEntitiesOfClass(YigaTeamMemberEntity.class,event.getEntity().getBoundingBox().inflate(16, 16, 16) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO ??????????????????
					return true;
				}});
			if(list.size()>5)
				return;
			Entity villager=event.getEntity();
			BlockPos pos=villager.blockPosition();
			YigaTeamMemberEntity entity=new YigaTeamMemberEntity(EntityLoader.YIGA_TEAM_MEMBER.get(),event.getEntity().level);
			entity.setPos(pos.getX(), pos.getY(), pos.getZ());
			villager.level.addFreshEntity(entity);
		}
		if(event.getEntity() instanceof PlayerEntity&&event.getEntity().level.isClientSide) {
			if(EntitySpottedEvent.SoundRemainTime>0)
				EntitySpottedEvent.SoundRemainTime--;
		}
}
	private static boolean isOutdoors(PlayerEntity player) {
		BlockPos pos=player.blockPosition();
		for(int i=pos.getY()+1;i<256;i++) {
			if(!player.level.getBlockState(new BlockPos(pos.getX(),i,pos.getZ())).isAir())
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
		EntityTick.THUNDER_COUNT_TIME.put(event.getPlayer(), 100);
		//PlayerTick.THUNDER_COUNT_TIME.remove(event.getOriginal());
	}
}
