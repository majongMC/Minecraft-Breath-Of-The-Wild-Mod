package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class FireArrowEntity extends ArrowEntity{

	public FireArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO 自动生成的构造函数存根
	}
	public FireArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public FireArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		AttributeDamage.firedamage(living, this.getOwner());
		if(ZeldaConfig.FIRE_ARROW.get())
			placefire();
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			List<PlayerEntity> playerlist= level.getEntitiesOfClass(PlayerEntity.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new ParticlePack(2,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
		}
		if(!this.level.isClientSide&&this.inGround&&ZeldaConfig.FIRE_ARROW.get()) {
			placefire();
			this.kill();
		}
	}
	private void placefire() {
		for(int i=-1;i<2;i++)
			 for(int j=-1;j<2;j++)
			 {
				 for(int y=(int) (this.getY()-2);y<(int) (this.getY()+1);y++)
				 {
					 if(this.level.getBlockState(new BlockPos(this.getX()+i,y,this.getZ()+j)).getBlock()==Blocks.AIR||this.level.getBlockState(new BlockPos(this.getX()+i,y,this.getZ()+j)).getBlock()==Blocks.FIRE)
					 {
						 this.level.setBlockAndUpdate(new BlockPos(this.getX()+i,y,this.getZ()+j),Blocks.FIRE.defaultBlockState());
						 break;
					 }
				 }
			 }
	}
}
