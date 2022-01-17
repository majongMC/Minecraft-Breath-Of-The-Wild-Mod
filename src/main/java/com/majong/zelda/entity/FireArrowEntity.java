package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

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
		// TODO �Զ����ɵĹ��캯�����
	}
	public FireArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public FireArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void arrowHit(LivingEntity living) {
		super.arrowHit(living);
		AttributeDamage.firedamage(living, this.func_234616_v_());
		if(ZeldaConfig.FIRE_ARROW.get())
			placefire();
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.world.isRemote) {
			List<PlayerEntity> playerlist= world.getEntitiesWithinAABB(PlayerEntity.class,this.getBoundingBox().grow(20, 20, 20) ,new Predicate<Object>() {

				@Override
				public boolean test(Object t) {
					// TODO �Զ����ɵķ������
					if(t instanceof PlayerEntity) 
						return true;
					else
						return false;
				}});
    		Iterator<PlayerEntity> it=playerlist.iterator();
    		while(it.hasNext()) {
    			PlayerEntity player=(PlayerEntity) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayerEntity) player
	                    ),
	                    new ParticlePack(2,this.getPosX(),this.getPosY(),this.getPosZ(),0,0,0));
    		}
		}
		if(!this.world.isRemote&&this.inGround&&ZeldaConfig.FIRE_ARROW.get()) {
			placefire();
			this.onKillCommand();
		}
	}
	private void placefire() {
		for(int i=-1;i<2;i++)
			 for(int j=-1;j<2;j++)
			 {
				 for(int y=(int) (this.getPosY()-2);y<(int) (this.getPosY()+1);y++)
				 {
					 if(this.world.getBlockState(new BlockPos(this.getPosX()+i,y,this.getPosZ()+j)).getBlock()==Blocks.AIR||this.world.getBlockState(new BlockPos(this.getPosX()+i,y,this.getPosZ()+j)).getBlock()==Blocks.FIRE)
					 {
						 this.world.setBlockState(new BlockPos(this.getPosX()+i,y,this.getPosZ()+j),Blocks.FIRE.getDefaultState());
						 break;
					 }
				 }
			 }
	}
}
