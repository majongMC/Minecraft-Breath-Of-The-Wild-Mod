package com.majong.zelda.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.majong.zelda.api.overlays.ZeldaHealthBarApi;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.data.ZeldaPlayerData;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.item.HeartContainer;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.tag.EntityTypeTag;
import com.majong.zelda.util.BiomeUtil;
import com.majong.zelda.util.ConductiveItem;
import com.majong.zelda.world.dimension.TempleDimensionData;
import com.mojang.logging.LogUtils;

import majongmc.hllib.common.event.LivingEvent.LivingTickEvent;
import majongmc.hllib.common.event.PlayerEvent.Clone;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityTick {
	public static final Map<Player,Integer> THUNDER_COUNT_TIME=new HashMap<>();
	public static final Map<Player,BlockPos> LAST_STAND_POS=new HashMap<>();
	public static final Map<Player,Long> ENTER_TEMPLE_TIME=new HashMap<>();
	public static void onEntityTick(LivingTickEvent event) {
		if(event.getEntity() instanceof Player&&!event.getEntity().level.isClientSide) {
			Player player=(Player) event.getEntity();
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
					int waittime=(int) (player.level.getServer().getLevel(Level.OVERWORLD).getGameTime()-ENTER_TEMPLE_TIME.get(player));
					if(waittime>100) {
						int[] position={(int) player.getX(),-100,(int) player.getZ()};
						LogUtils.getLogger().error("神庙传送超时，若该问题屡次发生，请检查数据包中是否存在非法或过大的神庙结构(出现问题的神庙位于"+position[0]+","+position[2]+")");
						TempleDimensionData.getTempleData(player.level, playerdata.intemple).putIntArray("startpoint", position);
						TempleDimensionData.get(player.level).setDirty();
						ENTER_TEMPLE_TIME.remove(player);
						TempleDimensionData.ExitTemple(player.level, player);
					}
				}
				if(player.isOnGround()&&player.level.getBlockState(player.blockPosition().offset(0,-1,0)).isCollisionShapeFullBlock(player.level, player.blockPosition().offset(0,-1,0)))
					LAST_STAND_POS.put(player, player.blockPosition());
				if(player.blockPosition().getY()<0&&LAST_STAND_POS.containsKey(player)) {
					BlockPos pos=LAST_STAND_POS.get(player);
					player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,60,8));
					player.teleportTo(pos.getX(),pos.getY()+3, pos.getZ());
					player.hurt(player.level.damageSources().outOfWorld(),2);
				}
			}
			for(int i=0;i<4;i++) {
			if(playerdata.skill[i]<=0) {
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
						LightningBolt lightning=new LightningBolt(EntityType.LIGHTNING_BOLT,player.level);
		    			lightning.setPos(player.getX(), player.getY()+1, player.getZ());
		    			player.level.addFreshEntity(lightning);
		    			THUNDER_COUNT_TIME.put(player,100);
					}
					else {
						Networking.PARTICLE.send((ServerPlayer) player,new ParticlePack(1,player.getX()-0.5+Math.random(),player.getY()+0.5+Math.random(),player.getZ()-0.5+Math.random(),0,0,0));
						THUNDER_COUNT_TIME.put(player,THUNDER_COUNT_TIME.get(player)-1);
					}
				}
				else
					THUNDER_COUNT_TIME.put(player,100);
			}
	}
		if(Math.random()<ZeldaConfig.YIGATEAM.get()*0.00002&&event.getEntity() instanceof Villager&&!event.getEntity().level.isClientSide) {
			List<YigaTeamMemberEntity> list=event.getEntity().level.getEntitiesOfClass(YigaTeamMemberEntity.class,event.getEntity().getBoundingBox().inflate(16, 16, 16) ,(YigaTeamMemberEntity entity)->true);
			if(list.size()>5)
				return;
			Entity villager=event.getEntity();
			BlockPos pos=villager.blockPosition();
			YigaTeamMemberEntity entity=new YigaTeamMemberEntity(EntityLoader.YIGA_TEAM_MEMBER.get(),event.getEntity().level);
			entity.setPos(pos.getX(), pos.getY(), pos.getZ());
			villager.level.addFreshEntity(entity);
		}
		if(event.getEntity() instanceof LivingEntity&&event.getEntity().getType().builtInRegistryHolder().tags().anyMatch((TagKey<EntityType<?>> t)->t.equals(EntityTypeTag.HAS_HEALTH_BAR))&&event.getEntity().level.isClientSide) {
			LivingEntity entity=(LivingEntity) event.getEntity();
			if(entity.blockPosition().getY()>=-64)
				ZeldaHealthBarApi.DisplayHealthBarClient(entity.getHealth()/entity.getMaxHealth(), entity.getName(),BiomeUtil.getBiomeName(entity.level.getBiome(entity.blockPosition()).value(),entity.level)+"的");
		}
		if(event.getEntity() instanceof Player&&event.getEntity().level.isClientSide) {
			if(EntitySpottedEvent.SoundRemainTime>0)
				EntitySpottedEvent.SoundRemainTime--;
		}
}
	private static boolean isOutdoors(Player player) {
		BlockPos pos=player.blockPosition();
		for(int i=pos.getY()+1;i<256;i++) {
			if(!player.level.getBlockState(new BlockPos(pos.getX(),i,pos.getZ())).isAir())
				return false;
		}
		return true;
	}
	public static void onPlayerClone(Clone event) {
		AttributeModifier modifier=event.getOriginal().getAttribute(Attributes.MAX_HEALTH).getModifier(HeartContainer.modifierID);
		if(modifier!=null)
			event.getEntity().getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier);
		//DataManager.preventnull(event.getOriginal());
		DataManager.preventnull(event.getEntity());
		DataManager.writefromnbt(DataManager.readtonbt(event.getOriginal()), event.getEntity());
		//DataManager.removedata(event.getOriginal());
		PlayerUseShield.PLAYER_LAST_USE_SHIELD.put(event.getEntity(),0L);
		PlayerUseShield.SHIELD_REFLECT_ACCOMPLISH.put(event.getEntity(),false);
		//PlayerUseShield.PLAYER_LAST_USE_SHIELD.remove(event.getOriginal());
		EntityTick.THUNDER_COUNT_TIME.put(event.getEntity(), 100);
		//PlayerTick.THUNDER_COUNT_TIME.remove(event.getOriginal());
	}
}
