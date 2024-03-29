package com.majong.zelda.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.majong.zelda.Utils;
import com.majong.zelda.api.overlays.ZeldaBloodBarApi;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.BiomeUtil;
import com.majong.zelda.util.ConductiveItem;
import com.majong.zelda.world.dimension.TempleDimensionData;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntityTick {
	public static final Map<PlayerEntity,Integer> THUNDER_COUNT_TIME=new HashMap<>();
	public static final Map<PlayerEntity,BlockPos> LAST_STAND_POS=new HashMap<>();
	public static final Map<PlayerEntity,Long> ENTER_TEMPLE_TIME=new HashMap<>();
	public static final ResourceLocation HAS_HEALTHBAR=new ResourceLocation(Utils.MOD_ID,"has_healthbar");
	@SubscribeEvent
	public static void onEntityTick(LivingUpdateEvent event) {
		if(event.getEntity() instanceof PlayerEntity&&!event.getEntity().level.isClientSide) {
			PlayerEntity player=(PlayerEntity) event.getEntity();
			ZeldaPlayerData playerdata=DataManager.data.get(player);
			if(LinkTime.LINK_TIME.containsKey(player)) {
				int time=LinkTime.LINK_TIME.get(player);
				if(time==0) {
					player.setNoGravity(false);
				}
				LinkTime.LINK_TIME.put(player,time-1);
			}
			if(playerdata.intemple>1) {
				if(ENTER_TEMPLE_TIME.containsKey(player)) {
					int waittime=(int) (player.level.getServer().getLevel(World.OVERWORLD).getGameTime()-ENTER_TEMPLE_TIME.get(player));
					if(waittime>100) {
						int[] position={(int) player.getX(),-100,(int) player.getZ()};
						LogManager.getLogger().error("神庙传送超时，若该问题屡次发生，请检查数据包中是否存在非法或过大的神庙结构(出现问题的神庙位于"+position[0]+","+position[2]+")");
						TempleDimensionData.getTempleData(player.level, playerdata.intemple).putIntArray("startpoint", position);
						TempleDimensionData.get(player.level).setDirty();
						ENTER_TEMPLE_TIME.remove(player);
						TempleDimensionData.ExitTemple(player.level, player);
					}
				}
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
			List<LivingEntity> list=event.getEntity().level.getEntitiesOfClass(YigaTeamMemberEntity.class,event.getEntity().getBoundingBox().inflate(16, 16, 16),(LivingEntity entity)->true);
			if(list.size()>5)
				return;
			Entity villager=event.getEntity();
			BlockPos pos=villager.blockPosition();
			YigaTeamMemberEntity entity=new YigaTeamMemberEntity(EntityLoader.YIGA_TEAM_MEMBER.get(),event.getEntity().level);
			entity.setPos(pos.getX(), pos.getY(), pos.getZ());
			villager.level.addFreshEntity(entity);
		}
		if(event.getEntity() instanceof LivingEntity&&event.getEntity().getType().getTags().contains(HAS_HEALTHBAR)&&event.getEntity().level.isClientSide) {
			LivingEntity entity=(LivingEntity) event.getEntity();
			if(entity.blockPosition().getY()>=0)
				ZeldaBloodBarApi.DisplayBloodBarClient(entity.getHealth()/entity.getMaxHealth(), entity.getName(),BiomeUtil.getBiomeName(entity.level.getBiome(entity.blockPosition()),entity.level)+"的");
		}
		if(event.getEntity() instanceof PlayerEntity&&event.getEntity().level.isClientSide) {
			if(EntitySpottedEvent.SoundRemainTime>0)
				EntitySpottedEvent.SoundRemainTime--;
		}
}
	private static boolean isOutdoors(Entity player) {
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
