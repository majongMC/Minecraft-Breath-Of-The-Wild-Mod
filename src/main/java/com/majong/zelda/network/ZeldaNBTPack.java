package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.data.DataManager;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ZeldaNBTPack {
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
        	case 1:DataManager.writefromnbt(nbt,Minecraft.getInstance().player);
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
