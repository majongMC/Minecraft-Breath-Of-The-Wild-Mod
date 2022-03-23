package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.data.DataManager;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ZeldaNBTPack {
	private final int type;
	private final CompoundNBT nbt;
    public ZeldaNBTPack(PacketBuffer buffer) {
    	this.type=buffer.readInt();
    	this.nbt=buffer.readNbt();
    }

    public ZeldaNBTPack(int type,CompoundNBT nbt) {
    	this.type=type;
    	this.nbt=nbt;
    }
    public void toBytes(PacketBuffer buf) {
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
