package com.majong.zelda.tileentity;

import com.majong.zelda.Utils;
import com.majong.zelda.block.BlockLoader;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityLoader {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.MOD_ID);
	public static final RegistryObject<TileEntityType<PotTileEntity>> POT_TILE_ENTITY = TILE_ENTITIES.register("pot_tileentity", () -> TileEntityType.Builder.create(PotTileEntity::new, BlockLoader.POT.get()).build(null));
}
