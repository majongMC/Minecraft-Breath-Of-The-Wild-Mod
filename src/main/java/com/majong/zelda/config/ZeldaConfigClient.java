package com.majong.zelda.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZeldaConfigClient {
	public static ForgeConfigSpec CLIENT_CONFIG;
	public static ForgeConfigSpec.IntValue GUARDIAN,WALKING_GUARDIAN,FIGHT,HINOX,ROCK_GIANT;
	public static ForgeConfigSpec.BooleanValue DISABLE_MUSIC;
	public static ForgeConfigSpec.BooleanValue DISPLAY_ANGLE,CAMERA_SHAKE;
	static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("Sound 声音(如果你用资源包修改了战斗音乐，请在此处修改对应的音乐长度（单位:tick），否则会造成音乐过早或过晚循环)").push("sound");
        GUARDIAN = CLIENT_BUILDER.comment("Guardian Misic Length 守护者音乐长度").defineInRange("guardian", 1200, 0, Integer.MAX_VALUE);
        WALKING_GUARDIAN = CLIENT_BUILDER.comment("Walking Guardian Misic Length 行走的守护者音乐长度").defineInRange("walking_guardian", 1800, 0, Integer.MAX_VALUE);
        FIGHT = CLIENT_BUILDER.comment("Fight Misic Length 战斗音乐长度").defineInRange("fight", 2060, 0, Integer.MAX_VALUE);
        HINOX = CLIENT_BUILDER.comment("Hinox Misic Length 西诺克斯音乐长度").defineInRange("hinox", 2200, 0, Integer.MAX_VALUE);
        ROCK_GIANT =CLIENT_BUILDER.comment("Rock Giant Misic Length 岩石巨人音乐长度").defineInRange("rock_giant", 1200, 0, Integer.MAX_VALUE);
        DISABLE_MUSIC =CLIENT_BUILDER.comment("Disable Music 禁用音乐，设置为true时不再播放战斗音乐").define("disable_music", false);
        CLIENT_BUILDER.pop();
        CLIENT_BUILDER.comment("Display 显示").push("display");
        DISPLAY_ANGLE =CLIENT_BUILDER.comment("以图形形式显示视线与目标之间的夹角（设置为false时）会以数字形式显示").define("displayangle", true);
        CAMERA_SHAKE=CLIENT_BUILDER.comment("Enable Camera Shake 允许相机抖动").define("camera_shake", true);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG=CLIENT_BUILDER.build();
	}
}
