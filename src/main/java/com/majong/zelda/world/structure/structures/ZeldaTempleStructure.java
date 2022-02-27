package com.majong.zelda.world.structure.structures;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.google.common.collect.ImmutableList;
import com.majong.zelda.Utils;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class ZeldaTempleStructure extends Structure<NoFeatureConfig>{
	public ZeldaTempleStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return ZeldaTempleStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
            /*new MobSpawnInfo.Spawners(EntityType.ILLUSIONER, 10, 1, 4),
            new MobSpawnInfo.Spawners(EntityType.VINDICATOR, 10, 1, 4)*/
    );

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
            /*new MobSpawnInfo.Spawners(EntityType.SHEEP, 10, 1, 5),
            new MobSpawnInfo.Spawners(EntityType.RABBIT, 10, 1, 2)*/
    );

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return STRUCTURE_MONSTERS;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return STRUCTURE_CREATURES;
    }

    //�������ɵ��������
    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource,
                                     long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ,
                                     Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {

        //�������ǵĴ�С��chunkX * 16+7,chunkZ*16+7
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
    }


    @Override
    public boolean getDefaultRestrictsSpawnsToInside() {
        return true;
    }

    public static class Start extends StructureStart<NoFeatureConfig>  {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {

            // Turns the chunk coordinates into actual coordinates we can use
            int x = chunkX * 16 + 7;
            int z = chunkZ * 16 + 7;

            /*���ǽ��䴫�ݵ�addPieces�У����������������ɽṹ��
            ���addPieces�����һ������Ϊtrue��blockpos��Yֵ�������ԣ�
            �ṹ���ڵ��θ߶����ɡ�
            ���ò�������Ϊfalse����ǿ�ƽṹ��blockpos��Yֵ���ɡ�����ѡ��*/
            BlockPos centerPos = new BlockPos(x, 0, z);

            /*
             * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
             * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
             * Then loop through it and look for land with air above it and set blockpos's Y value to it.
             * Make sure to set the final boolean in JigsawManager.addPieces to false so
             * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
             */
            /*��������ڽ����½�ṹ������ܻ����ڱڼ��Ͻ�����Ľṹ��
             ��õķ�����ʹ��getBaseColumn�ڽṹ��x/zλ�û�ȡһ�п顣
             Ȼ��ͨ����ѭ����Ѱ�������п�����½�أ�����blockpos��Yֵ����Ϊ����
             ȷ����JigsawManager���������ղ���ֵ��
             ����Ƭ��ӵ�false��ʹ�ṹ��blockpos��yֵ���ɣ������ǽ��ṹ�����ڻ����ݶ��ϡ�*/
            //IBlockReader blockReader = chunkGenerator.getBaseColumn(blockpos.getX(), blockpos.getZ());

            // All a structure has to do is call this method to turn it into a jigsaw based structure!
            //һ���ṹ��Ҫ���ľ��ǵ�������������������һ������ƴͼ�Ľṹ��
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            // The path to the starting Template Pool JSON file to read.
                            //
                            // Note, this is "structure_tutorial:run_down_house/start_pool" which means
                            // the game will automatically look into the following path for the template pool:
                            // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/start_pool.json"
                            // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
                            // because the game automatically will check in worldgen/template_pool for the pools.
                            .get(new ResourceLocation(Utils.MOD_ID, "zelda_temple/start_pool")),

                            // How many pieces outward from center can a recursive jigsaw structure spawn.
                            // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
                            //���ǵĽṹֻ��һ������Ĳ��֣����Ҳ��ǵݹ�ģ������κ�1������ֵ������ı��κζ�����
                            // However, I recommend you keep this a decent value like 10 so people can use datapacks to add additional pieces to your structure easily.
                            // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    centerPos, // Position of the structure. Y value is ignored if last parameter is set to true.
                    this.pieces, // The list that will be populated with the jigsaw pieces after this method.
                    this.random,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    true);  // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
            // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.


            // **THE FOLLOWING TWO LINES ARE OPTIONAL**
            //
            // Right here, you can do interesting stuff with the pieces in this.pieces such as offset the
            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
            // holds just the piece's size and positions. Blocks will be placed later in JigsawManager.
            /*����������������Щ��Ʒ��һЩ��Ȥ�����顣
            ������������Ե�޹ʵؽ����Ĺ�������ƫ��50���飬ɾ��һ���������ظ���
            �����һ���¹�����ʹ�ù���ֻ����һ�����ȵȡ�
            �����޷����ʸù����Ŀ飬��Ϊ���б�����湤���Ĵ�С��λ�á�
            �Ժ���JigsawManager�з��ÿ顣*/
            // In this case, we do `piece.offset` to raise pieces up by 1 block so that the house is not right on
            // the surface of water or sunken into land a bit.
            //
            // Then we extend the bounding box down by 1 by doing `piece.getBoundingBox().minY` which will cause the
            // land formed around the structure to be lowered and not cover the doorstep. You can raise the bounding
            // box to force the structure to be buried as well. This bounding box stuff with land is only for structures
            // that you added to Structure.NOISE_AFFECTING_FEATURES field handles adding land around the base of structures.
            //
            // By lifting the house up by 1 and lowering the bounding box, the land at bottom of house will now be
            // flush with the surrounding terrain without blocking off the doorstep.
            this.pieces.forEach(piece -> piece.move(0, 1, 0));
            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);

            // Since by default, the start piece of a structure spawns with it's corner at centerPos
            // and will randomly rotate around that corner, we will center the piece on centerPos instead.
            // This is so that our structure's start piece is now centered on the water check done in isFeatureChunk.
            // Whatever the offset done to center the start piece, that offset is applied to all other pieces
            // so the entire structure is shifted properly to the new spot.
            /*����Ĭ������£��ṹ����ʼ������centerPos�Ľǵ㴦���ɣ�����Χ�Ƹýǵ������ת��
            ������ǽ���Ϊ��centerPos�Ͼ��С���ˣ����ǵĽṹ�Ŀ�ʼ����������isFeatureChunk����ɵ�ˮ���Ϊ���ġ�
            ����Ϊʹ��ʼ�������ж�����ƫ����ʲô����ƫ�ƶ���Ӧ�������������������Ӷ�ʹ�����ṹ��ȷ���ƶ����µ�λ�á�*/
            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
            int xOffset = centerPos.getX() - structureCenter.getX();
            int zOffset = centerPos.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.pieces){
                structurePiece.move(xOffset, 0, zOffset);
            }

            this.calculateBoundingBox();

            //����Debug���鿴�������ɵ�λ��
            LogManager.getLogger().log(Level.DEBUG, "Rundown House at " +
                    this.pieces.get(0).getBoundingBox().x0 + " " +
                    this.pieces.get(0).getBoundingBox().y0 + " " +
                    this.pieces.get(0).getBoundingBox().z0);
        }
    }

}
