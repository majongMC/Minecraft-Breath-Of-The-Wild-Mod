package com.majong.zelda.network;

import java.util.function.Supplier;

import org.joml.Vector3f;

import com.majong.zelda.client.ClientUtils;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ParticlePack {
	private final double DATA[]=new double[6];
	private final int type;
    public ParticlePack(FriendlyByteBuf buffer) {
    	type=buffer.readInt();
    	for(int i=0;i<6;i++) {
			DATA[i]=buffer.readDouble();
		}
    }

    public ParticlePack(int type,double x,double y,double z,double a,double b,double c) {
    	this.type=type;
    	DATA[0]=x;
        DATA[1]=y;
        DATA[2]=z;
        DATA[3]=a;
        DATA[4]=b;
        DATA[5]=c;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(type);
    	for(int i=0;i<6;i++) {
    		buf.writeDouble(DATA[i]);
    	}
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	if(type==0) {
        	int distance=(int)Math.sqrt((DATA[3]-DATA[0])*(DATA[3]-DATA[0])+(DATA[4]-DATA[1])*(DATA[4]-DATA[1])+(DATA[5]-DATA[2])*(DATA[5]-DATA[2]));
        	for(int i=0;i<(distance+1);i++) {
        		double rx,ry,rz,rand;
        		rand=Math.random();
        		rx=rand*DATA[0]+(1-rand)*DATA[3];
        		ry=rand*DATA[1]+(1-rand)*DATA[4];
        		rz=rand*DATA[2]+(1-rand)*DATA[5];
        		ClientUtils.GetClientLevel().addAlwaysVisibleParticle(DustParticleOptions.REDSTONE,rx,ry,rz,0,0,0);
        	}
        	}
        	if(type!=0) {
        		switch(type) {
        		case 1:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1F,1F,0F),1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 2:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1F,0.5F,0F),1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 3:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1F,1F,1F),1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 4:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(DustParticleOptions.REDSTONE,DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 5:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(0F,0F,1F),1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 6:ClientUtils.GetClientLevel().addAlwaysVisibleParticle(ParticleTypes.FLASH,DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 7:PerformMiphaParticle();break;
        		}
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void PerformMiphaParticle() {
    	for(int i=0;i<50;i++) {
    		ClientUtils.GetClientLevel().addAlwaysVisibleParticle(ParticleTypes.HAPPY_VILLAGER,DATA[0]-1+2*Math.random(),DATA[1]+2*Math.random(),DATA[2]-1+2*Math.random(),0,0,0);
    		ClientUtils.GetClientLevel().addAlwaysVisibleParticle(ParticleTypes.NAUTILUS,DATA[0]-1+2*Math.random(),DATA[1]+2*Math.random(),DATA[2]-1+2*Math.random(),0,0,0);
    	}
    }
}
