package com.majong.zelda.event;

import com.majong.zelda.Utils;
import com.majong.zelda.api.overlays.ZeldaBloodBarApi;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;
import com.majong.zelda.entity.WalkingGuardianEntity;
import com.majong.zelda.entity.YigaTeamMemberEntity;
import com.majong.zelda.network.HealthBarPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.SoundPack;
import com.majong.zelda.util.Linkage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber()
public class EntitySpottedEvent {
	public static int SoundRemainTime=0;
	@SubscribeEvent
	public static void onEntitySpotted(LivingSetAttackTargetEvent event) {
		LivingEntity target=event.getTarget();
		Entity entity=event.getEntity();
		if(target instanceof YigaTeamMemberEntity&&!((YigaTeamMemberEntity)target).isactivated()&&event.getEntity() instanceof IronGolemEntity&&!entity.level.isClientSide) {
			IronGolemEntity golem=(IronGolemEntity) entity;
			golem.setTarget(null);
			return;
		}
		if(target instanceof PlayerEntity&&!target.level.isClientSide) {
			if(entity instanceof GuardianEntity) {
				if(entity.level.getGameTime()%20==7) {
					if(entity instanceof WalkingGuardianEntity) {
						Networking.SOUND.send(
		                    PacketDistributor.PLAYER.with(
		                            () -> (ServerPlayerEntity) target
		                    ),
		                    new SoundPack(2,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
					else {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) event.getTarget()
			                    ),
			                    new SoundPack(3,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
				}
			}
			if(entity instanceof MollyBrinEntity||entity instanceof PokBrinEntity) {
				if(entity.level.getGameTime()%20==8) {
						Networking.SOUND.send(
			                    PacketDistributor.PLAYER.with(
			                            () -> (ServerPlayerEntity) target
			                    ),
			                    new SoundPack(4,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
				}
			}
			if(Utils.ICE_AND_FIRE_LOADED&&Linkage.isHinox(entity)) {
				LivingEntity cyclops=(LivingEntity) entity;
				PlayerEntity player=(PlayerEntity) target;
				ZeldaBloodBarApi.DisplayBloodBarServer(cyclops.getHealth()/cyclops.getMaxHealth(), HealthBarPack.SINOX, player);
				if(entity.level.getGameTime()%20==9) {
					Networking.SOUND.send(
				      PacketDistributor.PLAYER.with(
				                            () -> (ServerPlayerEntity) target
				             ),
				             new SoundPack(9,new BlockPos(entity.getX(),entity.getY(),entity.getZ())));
					}
				}
		}
	}
}
