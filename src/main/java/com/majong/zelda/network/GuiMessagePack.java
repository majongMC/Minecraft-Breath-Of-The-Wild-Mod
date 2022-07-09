package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.gui.ZeldaMessageGui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class GuiMessagePack {
	private final int heal,hunger,type;
    public GuiMessagePack(FriendlyByteBuf buffer) {
    	type=buffer.readInt();
    	heal=buffer.readInt();
    	hunger=buffer.readInt();
    }

    public GuiMessagePack(int type,int heal,int hunger) {
    	this.type=type;
    	this.heal=heal;
    	this.hunger=hunger;
    }
    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(type);
    	buf.writeInt(heal);
    	buf.writeInt(hunger);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	Minecraft.getInstance().setScreen(new ZeldaMessageGui(type,heal,hunger));
        });
        ctx.get().setPacketHandled(true);
    }
}
