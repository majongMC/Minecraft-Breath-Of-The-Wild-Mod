package com.majong.zelda.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DestroyBlockGoal extends Goal{
	private Entity owner;
	private int range;
	public DestroyBlockGoal(Entity owner,int range) {
		this.owner=owner;
		this.range=range;
	}
	@Override
	public boolean canUse() {
		// TODO �Զ����ɵķ������
		if(owner.level.getGameTime()%20!=5||!owner.getServer().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
			return false;
		int X=(int) owner.getX();
		int Y=(int) owner.getY();
		int Z=(int) owner.getZ();
		Level Level=owner.level;
		for(int x=X-range;x<=X+range;x++) {
			for(int z=Z-range;z<=Z+range;z++) {
				for(int y=Y;y<=Y+range;y++) {
					BlockPos pos=new BlockPos(x,y,z);
					BlockState state=Level.getBlockState(pos);
					if(!state.isAir()&&!(state.getBlock() instanceof EntityBlock)&&state.getDestroySpeed(Level, pos)<50&&state.getDestroySpeed(Level, pos)>0) {
						Level.destroyBlock(pos, false, owner);
					}
				}
			}
		}
		return false;
	}

}
