package com.majong.zelda.network;

import com.majong.zelda.Utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
	public static SimpleChannel PARTICLE,SOUND,ZELDANBT,PACKWITHUUID,FOODMESSAGEPACK,BAR;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        PARTICLE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "particle_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        PARTICLE.messageBuilder(ParticlePack.class, nextID())
                .encoder(ParticlePack::toBytes)
                .decoder(ParticlePack::new)
                .consumer(ParticlePack::handler)
                .add();
        SOUND = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "sound_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        SOUND.messageBuilder(SoundPack.class, nextID())
                .encoder(SoundPack::toBytes)
                .decoder(SoundPack::new)
                .consumer(SoundPack::handler)
                .add();
        ZELDANBT = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "zeldanbt_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        ZELDANBT.messageBuilder(ZeldaNBTPack.class, nextID())
                .encoder(ZeldaNBTPack::toBytes)
                .decoder(ZeldaNBTPack::new)
                .consumer(ZeldaNBTPack::handler)
                .add();
        PACKWITHUUID = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "pack_with_uuid"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        PACKWITHUUID.messageBuilder(PackWithUUID.class, nextID())
                .encoder(PackWithUUID::toBytes)
                .decoder(PackWithUUID::new)
                .consumer(PackWithUUID::handler)
                .add();
        FOODMESSAGEPACK = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "foodmessage_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        FOODMESSAGEPACK.messageBuilder(GuiMessagePack.class, nextID())
                .encoder(GuiMessagePack::toBytes)
                .decoder(GuiMessagePack::new)
                .consumer(GuiMessagePack::handler)
                .add();
        BAR = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "bar_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        BAR.messageBuilder(HealthBarPack.class, nextID())
                .encoder(HealthBarPack::toBytes)
                .decoder(HealthBarPack::new)
                .consumer(HealthBarPack::handler)
                .add();
    }
}
