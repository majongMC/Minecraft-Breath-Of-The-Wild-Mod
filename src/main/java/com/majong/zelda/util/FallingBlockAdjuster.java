package com.majong.zelda.util;

import java.util.HashMap;
import java.util.Map;

import com.majong.zelda.api.tickutils.Ticker;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.IDDownPack;
import com.majong.zelda.network.Networking;
import com.majong.zelda.tag.BlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

public class FallingBlockAdjuster extends Ticker<FallingBlockEntity>{
	private static final Map<FallingBlockEntity,FallingBlockAdjuster> CLIENT_FALLING_BLOCKS=new HashMap<>();
	private static final Map<FallingBlockEntity,FallingBlockAdjuster> SERVER_FALLING_BLOCKS=new HashMap<>();
	private final Player player;
	private final double distance;
	private final boolean clientside;
	public FallingBlockAdjuster(FallingBlockEntity fallingblock,Player player,double distance,boolean clientside) {
		super(fallingblock);
		fallingblock.setNoGravity(true);
		this.player=player;
		this.distance=distance;
		this.clientside=clientside;
		if(clientside)
			CLIENT_FALLING_BLOCKS.put(fallingblock, this);
		else
			SERVER_FALLING_BLOCKS.put(fallingblock, this);
	}
	public static void fall(Player player, BlockPos pos, BlockState state) {
		if(!canMove(state,player.level,pos))
			return;
		Vec3 headpos=new Vec3(player.getX() ,player.getY()+1.5,player.getZ());
		FallingBlockEntity entity=FallingBlockEntity.fall(player.level, pos, state);
		entity.setPos(entity.getX(),entity.getY()+0.2,entity.getZ());
		Vec3 blockpos=new Vec3(entity.getX(),entity.getY()+1.5,entity.getZ());
		double distance=headpos.distanceTo(blockpos);
		new FallingBlockAdjuster(entity,player,distance,false);
		Networking.IDD.send(
                PacketDistributor.PLAYER.with(
                        () -> (ServerPlayer) player
                ),
                new IDDownPack(entity.getId()));
	}
	public static void bindFallingBlock(FallingBlockEntity entity,Player player) {
		Vec3 headpos=new Vec3(player.getX() ,player.getY()+1.5,player.getZ());
		Vec3 blockpos=new Vec3(entity.getX(),entity.getY()+1.5,entity.getZ());
		double distance=headpos.distanceTo(blockpos);
		new FallingBlockAdjuster(entity,player,distance,true);
	}
	public static void loose(FallingBlockEntity entity,Player player,boolean clientside) {
		if(clientside&&!CLIENT_FALLING_BLOCKS.containsKey(entity))
			return;
		if(!clientside&&!SERVER_FALLING_BLOCKS.containsKey(entity))
			return;
		FallingBlockAdjuster adjuster;
		if(clientside)
			adjuster=CLIENT_FALLING_BLOCKS.get(entity);
		else
			adjuster=SERVER_FALLING_BLOCKS.get(entity);
		if(adjuster.player!=player)
			return;
		entity.setNoGravity(false);
		if(clientside)
			CLIENT_FALLING_BLOCKS.remove(entity);
		else
			SERVER_FALLING_BLOCKS.remove(entity);
		adjuster.remove();
	}
	@Override
	public void tick() {
		FallingBlockEntity entity=this.getParaments()[0];
		entity.setOnGround(false);
		entity.time=0;
		if(player==null||player.isDeadOrDying()||entity==null||entity.isRemoved())
			loose(entity,player,isclientside());
		this.performmagneticforce();
        if(!FallingBlock.isFree(entity.level.getBlockState(entity.blockPosition().offset(0, -1, 0)))&&(entity.getY()-(int)(entity.getY()))<0.3&&(entity.getY()-(int)(entity.getY()))>0) {
			entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(0,(0.3-(entity.getY()-(int)(entity.getY())))*3,0)));
		}
	}
	private void performmagneticforce() {
		FallingBlockEntity block=this.getParaments()[0];
		float yaw=player.yHeadRot;
		float pitch=player.xRotO;
		double sinyaw=Math.sin((yaw+90)*Math.PI/180);
		double cosyaw=Math.cos((yaw+90)*Math.PI/180);
		double sinpitch=-Math.sin(pitch*Math.PI/180);
		double cospitch=Math.cos(pitch*Math.PI/180);
		Vec3 headpos=new Vec3(player.getX(),player.getY()+1.5,player.getZ());
		double x,y,z,r;
		y=distance*sinpitch;
		r=distance*cospitch;
		x=r*cosyaw;
		z=r*sinyaw;
		Vec3 center=headpos.add(x, y, z);
		Vec3 blockpos=new Vec3(block.getX(),block.getY()+1.5,block.getZ());
		Vec3 force=center.subtract(blockpos);
		force=force.scale(0.1);
		block.setDeltaMovement(force);
	}
	private static boolean canMove(BlockState state,Level level,BlockPos pos) {
		return !state.isAir()&&(!(state.getBlock() instanceof EntityBlock)||ZeldaConfig.CANMOVEBE.get())&&state.getDestroySpeed(level, pos)<50&&state.getDestroySpeed(level, pos)>=0&&!state.getBlock().builtInRegistryHolder().tags().anyMatch((TagKey<Block> t)->t.equals(BlockTag.CANT_MOVE));
	}
	@Override
	public boolean isclientside() {
		return clientside;
	}
}
