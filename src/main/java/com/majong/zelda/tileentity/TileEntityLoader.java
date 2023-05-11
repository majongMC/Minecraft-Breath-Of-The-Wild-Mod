package com.majong.zelda.tileentity;

import com.majong.zelda.Utils;
import com.majong.zelda.block.BlockLoader;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleEntryTileEntity;
import com.majong.zelda.tileentity.HasTempleIDTileEntity.TempleFinishTileEntity;

import majongmc.hllib.common.registry.DeferredRegister;
import majongmc.hllib.common.registry.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TileEntityLoader {
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Utils.MOD_ID);
	public static final RegistryObject<BlockEntityType<PotTileEntity>> POT_TILE_ENTITY = TILE_ENTITIES.register("pot_tileentity", () -> BlockEntityType.Builder.of(PotTileEntity::new, BlockLoader.POT.get()).build(null));
	public static final RegistryObject<BlockEntityType<TempleEntryTileEntity>> TEMPLE_ENTRY_TILE_ENTITY = TILE_ENTITIES.register("temple_entry_tileentity", () -> BlockEntityType.Builder.of(TempleEntryTileEntity::new, BlockLoader.TEMPLE_ENTRY.get()).build(null));
	public static final RegistryObject<BlockEntityType<TempleStartTileEntity>> TEMPLE_START_TILE_ENTITY = TILE_ENTITIES.register("temple_start_tileentity", () -> BlockEntityType.Builder.of(TempleStartTileEntity::new, BlockLoader.TEMPLE_START.get()).build(null));
	public static final RegistryObject<BlockEntityType<TempleFinishTileEntity>> TEMPLE_FINISH_TILE_ENTITY = TILE_ENTITIES.register("temple_finish_tileentity", () -> BlockEntityType.Builder.of(TempleFinishTileEntity::new, BlockLoader.TEMPLE_FINISH.get()).build(null));
}
