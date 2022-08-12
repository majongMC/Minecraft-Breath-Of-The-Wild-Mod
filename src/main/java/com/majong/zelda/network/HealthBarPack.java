package com.majong.zelda.network;

import java.util.function.Supplier;

import com.majong.zelda.client.ClientUtils;
import com.majong.zelda.overlays.RenderOverlays;
import com.majong.zelda.util.BiomeUtil;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class HealthBarPack {
	private final double DATA;
	private final ITextComponent NAME;
	public static final ITextComponent SINOX=new TranslationTextComponent("boss.sinox.bar");
	public HealthBarPack(PacketBuffer buffer) {
		DATA=buffer.readDouble();
		NAME=buffer.readComponent();
    }
	@Deprecated//请使用api中的方法
    public HealthBarPack(ITextComponent name,double percentage) {
        this.DATA=percentage;
        this.NAME=name;
    }
    public void toBytes(PacketBuffer buf) {
    	buf.writeDouble(DATA);
    	buf.writeComponent(NAME);
    }
    @SuppressWarnings("deprecation")
	public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	RenderOverlays.DisplayBloodBar(DATA, NAME,BiomeUtil.getBiomeName(ClientUtils.GetClientLevel().getBiome(ClientUtils.GetClientPlayer().blockPosition()), ClientUtils.GetClientLevel())+"的");
        });
        ctx.get().setPacketHandled(true);
    }
}
