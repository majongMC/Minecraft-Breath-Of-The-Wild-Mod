package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class SoundPack {
	private final BlockPos pos;
	private final int type;
    public SoundPack(PacketBuffer buffer) {
    	type=buffer.readInt();
    	pos=buffer.readBlockPos();
    }

    public SoundPack(int type,BlockPos pos) {
    	this.type=type;
    	this.pos=pos;
    }
    public SoundPack() {
    	this(0,BlockPos.ZERO);
    }
    public void toBytes(PacketBuffer buf) {
    	buf.writeInt(type);
    	buf.writeBlockPos(pos);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 0:Minecraft.getInstance().getSoundManager().stop();EntitySpottedEvent.SoundRemainTime=0;break;
        	case 1:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.DEAD.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 2:if(EntitySpottedEvent.SoundRemainTime==0) {Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.WALKING_GUARDIAN.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=1800;}break;
        	case 3:if(EntitySpottedEvent.SoundRemainTime==0) {Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.GUARDIAN.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=1200;}break;
        	case 4:if(EntitySpottedEvent.SoundRemainTime==0) {Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.FIGHT.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=2060;}break;
        	case 5:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.COOKING.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 6:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.OBTAIN.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 7:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.COOKING_FAILED.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 8:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.WAKE_UP.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 9:if(EntitySpottedEvent.SoundRemainTime==0) {Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.SINOX.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=2200;}break;
        	case 10:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundLoader.MIFA.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 11:Minecraft.getInstance().level.playSound(Minecraft.getInstance().player,pos, SoundEvents.GENERIC_HURT, SoundCategory.AMBIENT, 10f, 1f);break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
