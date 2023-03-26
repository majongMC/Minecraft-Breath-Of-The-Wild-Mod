package com.majong.zelda.entity;

import java.util.Iterator;
import java.util.List;

import com.majong.zelda.network.Networking;
import com.majong.zelda.network.ParticlePack;
import com.majong.zelda.util.AttributeDamage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

public class AncientArrowEntity extends Arrow{

	public AncientArrowEntity(EntityType<? extends Arrow> type, Level worldIn) {
		super(type, worldIn);
		// TODO �Զ����ɵĹ��캯�����
	}
	public AncientArrowEntity(Level worldIn, double x, double y, double z) {
		super(worldIn,x,y,z);
	}
	public AncientArrowEntity(Level worldIn, LivingEntity shooter) {
		super(worldIn,shooter);
	}
	@Override
	public void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		AttributeDamage.ancientdamage(living, this.getOwner());
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide&&(!this.inGround||this.random.nextInt(0, 10)==0)) {
			List<Player> playerlist= level.getEntitiesOfClass(Player.class,this.getBoundingBox().inflate(20, 20, 20));
    		Iterator<Player> it=playerlist.iterator();
    		while(it.hasNext()) {
    			Player player=(Player) it.next();
    			Networking.PARTICLE.send(
	                    PacketDistributor.PLAYER.with(
	                            () -> (ServerPlayer) player
	                    ),
	                    new ParticlePack(5,this.getX(),this.getY(),this.getZ(),0,0,0));
    		}
		}
	}
}
