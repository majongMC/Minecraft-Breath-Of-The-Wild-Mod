package com.majong.zelda.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class StaticTempleStartBlock extends TempleStartBlock{
	   @Override
	   public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
	      return p_185499_1_;
	   }

	   @Override
	   public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
	      return p_185471_1_;
	   }

}
