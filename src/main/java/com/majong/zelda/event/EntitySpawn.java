package com.majong.zelda.event;

import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;
import com.majong.zelda.entity.PokBrinEntity;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EntitySpawn {
	@SubscribeEvent
	public static void onLivingSpawn(CheckSpawn event) {
		if(!event.getWorld().isClientSide()) {
			if(isZeldaMobs(event.getEntityLiving())&&isInAir(event.getWorld(),(int)(event.getX()),(int)(event.getY()),(int)(event.getZ()))) {
				event.setResult(Result.DENY);
			}
		}
	}
	private static boolean isZeldaMobs(LivingEntity entity) {
		return entity instanceof GuardianEntity||entity instanceof PokBrinEntity||entity instanceof MollyBrinEntity;
	}
	private static boolean isInAir(IWorld world,int x,int y,int z) {
		for(int i=y;i>y-5;i--) {
			Block block=world.getBlockState(new BlockPos(x,i,z)).getBlock();
			if(!(block instanceof AirBlock))
				return false;
		}
		return true;
	}
	@SubscribeEvent
    public static void biomeLoadingEvent(BiomeLoadingEvent event) {
		addEntityToAllOverworldBiomes(event,EntityLoader.GUARDIAN.get(),(int) (8*ZeldaConfig.GUARDIAN.get()),1,2);
		addEntityToAllOverworldBiomes(event,EntityLoader.WALKING_GUARDIAN.get(),(int) (4*ZeldaConfig.WALKING_GUARDIAN.get()),1,2);
		addEntityToAllOverworldBiomes(event,EntityLoader.MOLLY_BRIN.get(),(int) (10*ZeldaConfig.MOLLYBRIN.get()),1,2);
		addEntityNether(event,EntityLoader.POK_BRIN.get(),(int) (1*ZeldaConfig.POKBRIN.get()),1,1);
		addEntityToAllOverworldBiomes(event,EntityLoader.POK_BRIN.get(),(int) (8*ZeldaConfig.POKBRIN.get()),1,2);
    }
	private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			if(!event.getCategory().equals(Biome.Category.THEEND) && !event.getCategory().equals(Biome.Category.NETHER)) {
				addEntityToAllBiomes(event, type, weight, minCount, maxCount);
			}
	}
	private static void addEntityNether(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			if(event.getCategory().equals(Biome.Category.NETHER)) {
				addEntityToAllBiomes(event, type, weight, minCount, maxCount);
			}
	}
	private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			List<Spawners> base = event.getSpawns().getSpawner(type.getCategory());
			base.add(new Spawners(type,weight, minCount, maxCount));
	}
}
