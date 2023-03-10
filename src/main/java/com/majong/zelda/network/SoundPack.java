package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfigClient;
import com.majong.zelda.event.EntitySpottedEvent;
import com.majong.zelda.sound.SoundLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

public class SoundPack {
	private final BlockPos pos;
	private final int type;
    public SoundPack(FriendlyByteBuf buffer) {
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
    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(type);
    	buf.writeBlockPos(pos);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 0:ClientUtils.ClientStopSound();EntitySpottedEvent.SoundRemainTime=0;break;
        	case 1:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.DEAD.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 2:if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.WALKING_GUARDIAN.get(), SoundSource.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.WALKING_GUARDIAN.get();}break;
        	case 3:if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.GUARDIAN.get(), SoundSource.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.GUARDIAN.get();}break;
        	case 4:if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.FIGHT.get(), SoundSource.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.FIGHT.get();}break;
        	case 5:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.COOKING.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 6:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.OBTAIN.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 7:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.COOKING_FAILED.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 8:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.WAKE_UP.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 9:if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.HINOX.get(), SoundSource.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.HINOX.get();}break;
        	case 10:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.MIPHA.get(), SoundSource.AMBIENT, 10f, 1f);break;
        	case 11:ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundEvents.GENERIC_HURT, SoundSource.BLOCKS, 10f, 1f);break;
        	case 12:if(!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.TEMPLE.get(), SoundSource.AMBIENT, 10f, 1f);}break;
        	case 13:if(EntitySpottedEvent.SoundRemainTime<=0&&!ZeldaConfigClient.DISABLE_MUSIC.get()) {ClientUtils.GetClientLevel().playSound(ClientUtils.GetClientPlayer(),pos, SoundLoader.FIGHT_ORIGINAL.get(), SoundSource.AMBIENT, 10f, 1f);EntitySpottedEvent.SoundRemainTime=ZeldaConfigClient.FIGHT_ORIGINAL.get();}break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
