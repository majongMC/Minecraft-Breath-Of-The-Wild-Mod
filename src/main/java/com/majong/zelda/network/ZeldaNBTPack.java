package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.config.ZeldaConfig;
import com.majong.zelda.data.DataManager;

import majongmc.hllib.client.effects.CameraShakeApi;
import majongmc.hllib.common.network.INDP;
import majongmc.hllib.common.network.NetworkEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class ZeldaNBTPack implements INDP{
	private final int type;
	private final CompoundTag nbt;
    public ZeldaNBTPack(FriendlyByteBuf buffer) {
    	this.type=buffer.readInt();
    	this.nbt=buffer.readNbt();
    }

    public ZeldaNBTPack(int type,CompoundTag nbt) {
    	this.type=type;
    	this.nbt=nbt;
    }
    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(type);
    	buf.writeNbt(nbt);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	switch(type) {
        	case 1:DataManager.writefromnbt(nbt,ClientUtils.GetClientPlayer());break;
        	case 2:setConfig();break;
        	case 3:CameraShakeApi.CameraShakeClient(nbt.getInt("frame"));break;
        	}
        });
        ctx.get().setPacketHandled(true);
    }
    private void setConfig() {
    	int[] cd=nbt.getIntArray("cd");
    	ZeldaConfig.WATER.set(cd[0]);
    	ZeldaConfig.WIND.set(cd[1]);
    	ZeldaConfig.FIRE.set(cd[2]);
    	ZeldaConfig.THUNDER.set(cd[3]);
    }
}
