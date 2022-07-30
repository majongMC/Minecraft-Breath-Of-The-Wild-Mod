package com.majong.zelda.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZeldaConfigClient {
	public static ForgeConfigSpec CLIENT_CONFIG;
	public static ForgeConfigSpec.IntValue GUARDIAN,WALKING_GUARDIAN,FIGHT,HINOX,ROCK_GIANT;
	public static ForgeConfigSpec.BooleanValue DISPLAY_ANGLE;
	static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("Sound ����(���������Դ���޸���ս�����֣����ڴ˴��޸Ķ�Ӧ�����ֳ��ȣ���λ:tick���������������ֹ�������ѭ��)").push("sound");
        GUARDIAN = CLIENT_BUILDER.comment("Guardian Misic Length �ػ������ֳ���").defineInRange("guardian", 1200, 0, Integer.MAX_VALUE);
        WALKING_GUARDIAN = CLIENT_BUILDER.comment("Walking Guardian Misic Length ���ߵ��ػ������ֳ���").defineInRange("walking_guardian", 1800, 0, Integer.MAX_VALUE);
        FIGHT = CLIENT_BUILDER.comment("Fight Misic Length ս�����ֳ���").defineInRange("fight", 2060, 0, Integer.MAX_VALUE);
        HINOX = CLIENT_BUILDER.comment("Hinox Misic Length ��ŵ��˹���ֳ���").defineInRange("hinox", 2200, 0, Integer.MAX_VALUE);
        ROCK_GIANT =CLIENT_BUILDER.comment("Rock Giant Misic Length ��ʯ�������ֳ���").defineInRange("rock_giant", 1200, 0, Integer.MAX_VALUE);
        CLIENT_BUILDER.pop();
        CLIENT_BUILDER.comment("Display ��ʾ").push("display");
        DISPLAY_ANGLE =CLIENT_BUILDER.comment("�ֳ�ϣ��֮ʯʱ��ʾĿ��������֮��ļн�").define("displayangle", true);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG=CLIENT_BUILDER.build();
	}
}
