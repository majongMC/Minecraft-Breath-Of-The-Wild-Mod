package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.overlays.RenderOverlays;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public class HealthBarPack {
	private final double DATA;
	private final Component NAME;
	//public static final ITextComponent HINOX=new TranslationTextComponent("boss.sinox.bar");
	public HealthBarPack(FriendlyByteBuf buffer) {
		DATA=buffer.readDouble();
		NAME=buffer.readComponent();
    }
	@Deprecated//��ʹ��api�еķ���
    public HealthBarPack(Component name,double percentage) {
        this.DATA=percentage;
        this.NAME=name;
    }
    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeDouble(DATA);
    	buf.writeComponent(NAME);
    }
    @SuppressWarnings("deprecation")
	public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	RenderOverlays.DisplayHealthBar(DATA, NAME);
        });
        ctx.get().setPacketHandled(true);
    }
}
