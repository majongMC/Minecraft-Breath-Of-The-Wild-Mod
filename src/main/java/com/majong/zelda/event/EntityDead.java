package com.majong.zelda.event;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.util.Linkage;
import com.majong.zelda.world.dimension.TempleDimensionData;

import majongmc.hllib.common.tickutils.Schedule;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntityDead {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEntityDead(LivingDeathEvent event) {
		Entity entity=event.getEntity();
		if(entity instanceof Player&&!entity.level.isClientSide) {
			Player player=(Player) entity;
			if(DataManager.data.get(player).intemple>0&&!ZeldaConfig.CANDEATHINTEMPLE.get()) {
				player.setHealth(player.getMaxHealth());
				event.setCanceled(true);
				new Schedule<Player>(1, player) {

					@Override
					public boolean isclientside() {
						return false;
					}

					@Override
					public void finish() {
						Player p=this.getParaments()[0];
						if(p.isDeadOrDying())
							return;
						TempleDimensionData.ExitTemple(p.level, p);
						p.sendSystemMessage(Component.translatable("msg.zelda.temple_failed"));
					}};
				return;
			}
			if(DataManager.data.get(player).unlocked[0]&&DataManager.data.get(player).skill[0]>0) {
				player.setHealth(player.getMaxHealth());
				player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,1200,2));
				event.setCanceled(true);
				DataManager.data.get(player).skill[0]=0;
				DataManager.sendzeldaplayerdatapack(player);
				Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new ParticlePack(7,player.getX()+0.5,player.getY(),player.getZ()+0.5,0,0,0));
				Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new SoundPack(10,new BlockPos((int)player.getX(),(int)player.getY(),(int)player.getZ())));
				return;
			}
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayer) player
                    ),
                    new SoundPack());
			if(DataManager.data.get(player).intemple>1) {
			DataManager.AdjustAllSkills(player, true);
			DataManager.data.get(player).intemple=0;
			((ServerPlayer)player).setGameMode(GameType.SURVIVAL);
			}
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayer) player
                    ),
                    new SoundPack(1,new BlockPos((int)player.getX(),(int)player.getY(),(int)player.getZ())));
		}
		if(!entity.level.isClientSide&&(entity instanceof GuardianEntity||entity instanceof MollyBrinEntity||entity instanceof BokoBrinEntity||entity instanceof YigaTeamMemberEntity)||Linkage.isHinox(entity)) {
			List<Player> playerlist= entity.level.getEntitiesOfClass(Player.class,entity.getBoundingBox().inflate(64, 32, 64));
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new SoundPack());
    		}
		}
	}
}
