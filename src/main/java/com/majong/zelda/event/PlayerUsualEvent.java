package com.majong.zelda.event;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.RockGiantEntity;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class PlayerUsualEvent {
	@SubscribeEvent
	public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
		if(event.getEntity().level.isClientSide) {
			Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,Minecraft.getInstance().player.blockPosition(), SoundLoader.WAKE_UP.get(), SoundCategory.AMBIENT, 10f, 1f);
		}
	}
	@SubscribeEvent
	public static void onPlayerDestroyBlock(HarvestCheck event) {
		if(!event.getEntity().level.isClientSide&&event.canHarvest()) {
			PlayerEntity player=event.getPlayer();
			if(player.level.dimension().location().equals(DimensionType.OVERWORLD_EFFECTS)&&player.getY()<40) {
				if(Math.random()<0.0005*ZeldaConfig.ROCKGIANT.get()) {
					RockGiantEntity entity=new RockGiantEntity(EntityLoader.ROCK_GIANT.get(),event.getEntity().level);
					BlockPos pos=new BlockPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
					int count=0;
					while(!(player.level.getBlockState(pos).getBlock()==Blocks.AIR)) {
						pos=new BlockPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
						count++;
						if(count>30) {
							break;
						}
					}
					entity.setPos(player.getX()+randomint(), player.getY(), player.getZ()+randomint());
					event.getEntity().level.addFreshEntity(entity);
				}
			}
		}
	}
	private static int randomint() {
		int random=0;
		while(true) {
			random=(int) (32*Math.random()-16);
			if(Math.abs(random)>=8)
				return random;
		}
	}
}
