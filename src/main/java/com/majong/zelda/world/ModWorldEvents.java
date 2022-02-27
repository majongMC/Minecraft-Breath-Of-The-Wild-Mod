package com.majong.zelda.world;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.majong.zelda.Utils;
import com.majong.zelda.world.gen.ModStructureGeneration;
import com.majong.zelda.world.structure.ModStructures;
import com.mojang.serialization.Codec;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID)
public class ModWorldEvents {
	//StructureTutorialMain.java

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        //加入建筑的生成事件的声明
        ModStructureGeneration.generateStructures(event);

//        ModOreGeneration.generateOres(event);
//        ModFlowerGeneration.generateFlowers(event);
//        ModTreeGeneration.generateTrees(event);
    }

    //加入空间生成的相关设置
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            //建筑生成异常就会抛出相应错误
            try {
                Method GETCODEC_METHOD =
                        ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(
                        (Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                LogManager.getLogger().error("Was unable to check if " + serverWorld.getLevel()
                        + " is using Terraforged's ChunkGenerator.");
            }


            // 放止建筑在超平坦世界生成
            if (serverWorld.getChunkSource().generator instanceof FlatChunkGenerator &&
                    serverWorld.getLevel().equals(World.OVERWORLD)) {
                return;
            }

            // 将我们的建筑添加到建筑生成地图中
            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.ZELDA_TEMPLE.get(),
                    DimensionStructuresSettings.DEFAULTS.get(ModStructures.ZELDA_TEMPLE.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
