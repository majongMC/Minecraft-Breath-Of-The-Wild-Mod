package com.majong.zelda.event;

import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.entity.BokoBrinEntity;
import com.majong.zelda.entity.EntityLoader;
import com.majong.zelda.entity.GuardianEntity;
import com.majong.zelda.entity.MollyBrinEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
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
	@SubscribeEvent
    public static void biomeLoadingEvent(BiomeLoadingEvent event) {
		addEntityToAllOverworldBiomes(event,EntityLoader.GUARDIAN.get(),(int) (6*ZeldaConfig.GUARDIAN.get()),1,1);
		addEntityToAllOverworldBiomes(event,EntityLoader.WALKING_GUARDIAN.get(),(int) (3*ZeldaConfig.WALKING_GUARDIAN.get()),1,1);
		addEntityToAllOverworldBiomes(event,EntityLoader.MOLLY_BRIN.get(),(int) (10*ZeldaConfig.MOLLYBRIN.get()),1,1);
		addEntityNether(event,EntityLoader.BOKO_BRIN.get(),(int) (2*ZeldaConfig.BOKOBRIN.get()),1,1);
		addEntityToAllOverworldBiomes(event,EntityLoader.BOKO_BRIN.get(),(int) (8*ZeldaConfig.BOKOBRIN.get()),1,1);
    }
	private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
				addEntityToAllBiomes(event, type, weight, minCount, maxCount);
			}
	}
	private static void addEntityNether(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
				addEntityToAllBiomes(event, type, weight, minCount, maxCount);
			}
	}
	private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount) {
			List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
			base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
	}
}
