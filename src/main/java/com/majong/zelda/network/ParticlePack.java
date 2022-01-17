package com.majong.zelda.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraftforge.fml.network.NetworkEvent;

public class ParticlePack {
	private final double DATA[]=new double[6];
	private final int type;
    public ParticlePack(PacketBuffer buffer) {
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

    public void toBytes(PacketBuffer buf) {
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
        		Minecraft.getInstance().world.addOptionalParticle(RedstoneParticleData.REDSTONE_DUST,rx,ry,rz,0,0,0);
        	}
        	}
        	if(type!=0) {
        		switch(type) {
        		case 1:Minecraft.getInstance().world.addOptionalParticle(new RedstoneParticleData(1F,1F,0F,1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 2:Minecraft.getInstance().world.addOptionalParticle(new RedstoneParticleData(1F,0.5F,0F,1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 3:Minecraft.getInstance().world.addOptionalParticle(new RedstoneParticleData(1F,1F,1F,1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 4:Minecraft.getInstance().world.addOptionalParticle(RedstoneParticleData.REDSTONE_DUST,DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 5:Minecraft.getInstance().world.addOptionalParticle(new RedstoneParticleData(0F,0F,1F,1F),DATA[0],DATA[1],DATA[2],0,0,0);break;
        		case 6:Minecraft.getInstance().world.addOptionalParticle(ParticleTypes.FLASH,DATA[0],DATA[1],DATA[2],0,0,0);break;
        		}
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
