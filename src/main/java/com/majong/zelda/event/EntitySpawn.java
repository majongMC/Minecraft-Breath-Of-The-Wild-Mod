package com.majong.zelda.event;

import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EntitySpawn {
	@SubscribeEvent
	public static void onLivingSpawn(CheckSpawn event) {
		if(!event.getLevel().isClientSide()) {
			if(isZeldaMobs(event.getEntity())&&isInAir(event.getLevel(),(int)(event.getX()),(int)(event.getY()),(int)(event.getZ()))) {
				event.setResult(Result.DENY);
			}
		}
	}
	private static boolean isZeldaMobs(LivingEntity entity) {
		return entity instanceof GuardianEntity||entity instanceof BokoBrinEntity||entity instanceof MollyBrinEntity;
	}
	private static boolean isInAir(LevelAccessor Level,int x,int y,int z) {
		for(int i=y;i>y-5;i--) {
			Block block=Level.getBlockState(new BlockPos(x,i,z)).getBlock();
			if(!(block instanceof AirBlock))
				return false;
		}
		return true;
	}
}
