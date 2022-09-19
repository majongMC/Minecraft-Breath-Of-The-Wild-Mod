package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.api.tickutils.Delayer;
import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.util.FallingBlockAdjuster;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class IDDownPack {
	private final int id;
    public IDDownPack(FriendlyByteBuf buffer) {
    	id=buffer.readInt();
    }

    public IDDownPack(int id) {
    	this.id=id;
    }
    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(id);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	Entity entity=ClientUtils.GetClientLevel().getEntity(id);
        	if(entity==null) {
        		new Delayer<Integer>(5,id) {

					@Override
					public boolean isclientside() {
						return true;
					}

					@Override
					public void finish() {
						Entity e=ClientUtils.GetClientLevel().getEntity(id);
						FallingBlockAdjuster.bindFallingBlock((FallingBlockEntity) e, ClientUtils.GetClientPlayer());
					}};
        	}else {
        		FallingBlockAdjuster.bindFallingBlock((FallingBlockEntity) entity, ClientUtils.GetClientPlayer());
        	}
        });
        ctx.get().setPacketHandled(true);
    }
}
