package com.majong.zelda.event;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.majong.zelda.Utils;
import com.majong.zelda.data.DataManager;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.util.Linkage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntityDead {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEntityDead(LivingDeathEvent event) {
		Entity entity=event.getEntity();
		if(entity instanceof PlayerEntity&&!entity.level.isClientSide) {
			PlayerEntity player=(PlayerEntity) entity;
			if(DataManager.data.get(player).unlocked[0]&&DataManager.data.get(player).skill[0]==1) {
				player.setHealth(((PlayerEntity)event.getEntity()).getMaxHealth());
				player.addEffect(new EffectInstance(Effects.ABSORPTION,1200,2));
				event.setCanceled(true);
				DataManager.data.get(player).skill[0]=0;
				DataManager.sendzeldaplayerdatapack(player);
				Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new ParticlePack(7,player.getX()+0.5,player.getY(),player.getZ()+0.5,0,0,0));
				Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new SoundPack(10,new BlockPos(player.getX(),player.getY(),player.getZ())));
				return;
			}
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) player
                    ),
                    new SoundPack());
			if(DataManager.data.get(player).intemple>1) {
			DataManager.AdjustAllSkills(player, true);
			DataManager.data.get(player).intemple=0;
			player.setGameMode(GameType.SURVIVAL);
			}
			Networking.SOUND.send(
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) player
                    ),
                    new SoundPack(1,new BlockPos(player.getX(),player.getY(),player.getZ())));
		}
		if(!entity.level.isClientSide&&(entity instanceof GuardianEntity||entity instanceof MollyBrinEntity||entity instanceof PokBrinEntity||entity instanceof YigaTeamMemberEntity||(Utils.ICE_AND_FIRE_LOADED&&Linkage.isHinox(entity)))) {
			List<PlayerEntity> playerlist= entity.level.getEntitiesOfClass(PlayerEntity.class,entity.getBoundingBox().inflate(64, 32, 64) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO ??????????????????
					if(t instanceof PlayerEntity) 
						return true;
					else
						return false;
				}});
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.SOUND.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new SoundPack());
    		}
		}
	}
}
