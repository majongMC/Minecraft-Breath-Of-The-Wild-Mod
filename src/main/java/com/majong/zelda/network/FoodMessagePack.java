package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.gui.FoodMessageGui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class FoodMessagePack {
	private final int heal,hunger,type;
    public FoodMessagePack(PacketBuffer buffer) {
    	type=buffer.readInt();
    	heal=buffer.readInt();
    	hunger=buffer.readInt();
    }

    public FoodMessagePack(int type,int heal,int hunger) {
    	this.type=type;
    	this.heal=heal;
    	this.hunger=hunger;
    }
    public void toBytes(PacketBuffer buf) {
    	buf.writeInt(type);
    	buf.writeInt(heal);
    	buf.writeInt(hunger);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	Minecraft.getInstance().displayGuiScreen(new FoodMessageGui(type,heal,hunger));
        });
        ctx.get().setPacketHandled(true);
    }
}
