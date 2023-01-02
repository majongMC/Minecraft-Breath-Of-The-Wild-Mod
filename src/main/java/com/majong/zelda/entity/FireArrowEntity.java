package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.PacketDistributor;

public class FireArrowEntity extends Arrow{

	public FireArrowEntity(EntityType<? extends Arrow> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public FireArrowEntity(Level worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public FireArrowEntity(Level worldIn, LivingEntity shooter) {
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
			List<Player> playerlist= level.getEntitiesOfClass(Player.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
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
