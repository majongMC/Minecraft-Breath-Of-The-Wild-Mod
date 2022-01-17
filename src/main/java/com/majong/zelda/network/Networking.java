package com.majong.zelda.network;

import com.majong.zelda.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	public static SimpleChannel PARTICLE,SOUND,ZELDAPLAYERDATA,KEYPACK,FOODMESSAGEPACK,BAR;
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
        ZELDAPLAYERDATA = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "zeldaplayer_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        ZELDAPLAYERDATA.messageBuilder(ZeldaPlayerDataPack.class, nextID())
                .encoder(ZeldaPlayerDataPack::toBytes)
                .decoder(ZeldaPlayerDataPack::new)
                .consumer(ZeldaPlayerDataPack::handler)
                .add();
        KEYPACK = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "key_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        KEYPACK.messageBuilder(KeyPack.class, nextID())
                .encoder(KeyPack::toBytes)
                .decoder(KeyPack::new)
                .consumer(KeyPack::handler)
                .add();
        FOODMESSAGEPACK = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "foodmessage_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        FOODMESSAGEPACK.messageBuilder(FoodMessagePack.class, nextID())
                .encoder(FoodMessagePack::toBytes)
                .decoder(FoodMessagePack::new)
                .consumer(FoodMessagePack::handler)
                .add();
        BAR = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "bar_pack"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        BAR.messageBuilder(BloodBarPack.class, nextID())
                .encoder(BloodBarPack::toBytes)
                .decoder(BloodBarPack::new)
                .consumer(BloodBarPack::handler)
                .add();
    }
}
