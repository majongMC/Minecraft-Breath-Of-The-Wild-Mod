package com.majong.zelda.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DestroyBlockGoal extends Goal{
	private Entity owner;
	private int range;
	public DestroyBlockGoal(Entity owner,int range) {
		this.owner=owner;
		this.range=range;
	}
	@Override
	public boolean shouldExecute() {
		// TODO 自动生成的方法存根
		if(owner.world.getGameTime()%20!=5)
			return false;
		int X=(int) owner.getPosX();
		int Y=(int) owner.getPosY();
		int Z=(int) owner.getPosZ();
		World world=owner.world;
		for(int x=X-range;x<=X+range;x++) {
			for(int z=Z-range;z<=Z+range;z++) {
				for(int y=Y;y<=Y+2*range;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					BlockState state=world.getBlockState(pos);
					if(!state.isAir()&&!state.getBlock().hasTileEntity(state)&&state.getBlockHardness(world, pos)<50&&state.getBlockHardness(world, pos)>0) {
						world.destroyBlock(pos, false);
					}
				}
			}
		}
		return false;
	}

}
