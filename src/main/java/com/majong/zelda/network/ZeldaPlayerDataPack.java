package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.data.DataManager;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ZeldaPlayerDataPack {
	private final CompoundNBT nbt;
    public ZeldaPlayerDataPack(PacketBuffer buffer) {
    	this.nbt=buffer.readNbt();
    }

    public ZeldaPlayerDataPack(CompoundNBT nbt) {
    	this.nbt=nbt;
    }
    public void toBytes(PacketBuffer buf) {
    	buf.writeNbt(nbt);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	DataManager.writefromnbt(nbt,Minecraft.getInstance().player);
        });
        ctx.get().setPacketHandled(true);
    }
}
