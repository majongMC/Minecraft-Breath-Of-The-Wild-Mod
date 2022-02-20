package com.majong.zelda.event;

import com.github.alexthe666.iceandfire.entity.EntityCyclops;
import com.majong.zelda.Utils;
import com.majong.zelda.api.overlays.ZeldaBloodBarApi;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;
import com.majong.zelda.entity.WalkingGuardianEntity;
import com.majong.zelda.network.BloodBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class PlayerSpottedEvent {
	public static int SoundRemainTime=0;
	@SubscribeEvent
	public static void onEntitySpotPlayer(LivingSetAttackTargetEvent event) {
		if(event.getTarget() instanceof PlayerEntity&&!event.getEntity().level.isClientSide) {
			if(event.getEntity() instanceof GuardianEntity) {
				if(event.getEntity().level.getGameTime()%20==7) {
					if(event.getEntity() instanceof WalkingGuardianEntity) {
						Networking.SOUND.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) event.getTarget()
		                    ),
		                    new SoundPack(2,new BlockPos(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ())));
					}
					else {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) event.getTarget()
			                    ),
			                    new SoundPack(3,new BlockPos(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ())));
					}
				}
			}
			if(event.getEntity() instanceof MollyBrinEntity||event.getEntity() instanceof PokBrinEntity) {
				if(event.getEntity().level.getGameTime()%20==8) {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) event.getTarget()
			                    ),
			                    new SoundPack(4,new BlockPos(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ())));
				}
			}
			if(Utils.ICE_AND_FIRE_LOADED&&event.getEntity() instanceof EntityCyclops) {
				EntityCyclops entity=(EntityCyclops) event.getEntity();
				PlayerEntity player=(PlayerEntity) event.getTarget();
				ZeldaBloodBarApi.DisplayBloodBarServer(entity.getHealth()/entity.getMaxHealth(), BloodBarPack.SINOX, player);
				if(event.getEntity().level.getGameTime()%20==9) {
					Networking.SOUND.send(
				      PacketDistributor.PLAYER.with(
				                            () -> (ServerPlayerEntity) event.getTarget()
				             ),
				             new SoundPack(9,new BlockPos(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ())));
					}
				}
		}
	}
}
