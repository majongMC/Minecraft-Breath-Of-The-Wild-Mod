package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

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
        	case 0:ClientUtils.ClientStopSound();EntitySpottedEvent.SoundRemainTime=0;break;
        	case 1:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.DEAD.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 2:if(EntitySpottedEvent.SoundRemainTime==0) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.WALKING_GUARDIAN.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.WALKING_GUARDIAN.get();}break;
        	case 3:if(EntitySpottedEvent.SoundRemainTime==0) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.GUARDIAN.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.GUARDIAN.get();}break;
        	case 4:if(EntitySpottedEvent.SoundRemainTime==0) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.FIGHT.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.FIGHT.get();}break;
        	case 5:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.COOKING.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 6:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.OBTAIN.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 7:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.COOKING_FAILED.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 8:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.WAKE_UP.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 9:if(EntitySpottedEvent.SoundRemainTime==0) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.SINOX.get(), SoundCategory.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.HINOX.get();}break;
        	case 10:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.MIFA.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	case 11:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundEvents.GENERIC_HURT, SoundCategory.AMBIENT, 10f, 1f);break;
        	case 12:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.TEMPLE.get(), SoundCategory.AMBIENT, 10f, 1f);break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
