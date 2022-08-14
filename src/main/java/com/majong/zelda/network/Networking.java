package com.majong.zelda.network;

import com.majong.zelda.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	public static SimpleChannel PARTICLE,SOUND,ZELDANBT,PACKTOSERVER,FOODMESSAGEPACK,BAR;
    public static final String VERSION = "165.1.4.5";
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
        PACKTOSERVER = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "pack_to_server"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        PACKTOSERVER.messageBuilder(PackToServer.class, nextID())
                .encoder(PackToServer::toBytes)
                .decoder(PackToServer::new)
                .consumer(PackToServer::handler)
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
