package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.overlays.RenderOverlays;
import com.majong.zelda.util.BiomeUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public class HealthBarPack {
	private final double DATA;
	private final Component NAME;
	public static final Component HINOX=Component.translatable("boss.hinox.bar");
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
        	RenderOverlays.DisplayHealthBar(DATA, NAME,BiomeUtil.getBiomeName(ClientUtils.GetClientLevel().getBiome(ClientUtils.GetClientPlayer().blockPosition()).get(), ClientUtils.GetClientLevel())+"的");
        });
        ctx.get().setPacketHandled(true);
    }
}
