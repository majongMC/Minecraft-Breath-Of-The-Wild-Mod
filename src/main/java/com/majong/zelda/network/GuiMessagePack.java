package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.gui.OpenZeldaMessageGui;

import majongmc.hllib.common.network.INDP;
import majongmc.hllib.common.network.NetworkEvent;
import net.minecraft.network.FriendlyByteBuf;

public class GuiMessagePack implements INDP{
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
        	new OpenZeldaMessageGui(type,heal,hunger);
        });
        ctx.get().setPacketHandled(true);
    }
}
