package com.majong.zelda.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZeldaConfig {
	public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue WATER,WIND,FIRE,THUNDER;
    public static ForgeConfigSpec.IntValue SHIELD,LINKTIME;
    public static ForgeConfigSpec.BooleanValue DISPLAYTIME,DELAY_ATTACK;
    public static ForgeConfigSpec.DoubleValue GUARDIAN,WALKING_GUARDIAN,MOLLYBRIN,ROCKGIANT,POKBRIN,YIGATEAM;
    public static ForgeConfigSpec.BooleanValue NPCONLY,ATTRIBUTE,FIRE_ARROW,BOMB_ARROW_DESTROY,KILLWITHER,BOMB,BOMBDESTROY;
    public static ForgeConfigSpec.DoubleValue ELECTRICITY;
    public static ForgeConfigSpec.BooleanValue WEATHER_CHANGE;
    public static ForgeConfigSpec.DoubleValue WEATHER_CHANGE_CHANCE;
    public static ForgeConfigSpec.BooleanValue WINDBOMB,REDENVELOPE,CANDEATHINTEMPLE;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Skill ����").push("skill");
        WATER = COMMON_BUILDER.comment("Water Mythical Animals Cool Down Time ˮ���޼�����ȴʱ��").defineInRange("water", 12000, 0, Integer.MAX_VALUE);
        WIND = COMMON_BUILDER.comment("Wind Mythical Animals Cool Down Time �����޼�����ȴʱ��").defineInRange("wind", 12000, 0, Integer.MAX_VALUE);
        FIRE = COMMON_BUILDER.comment("Fire Mythical Animals Cool Down Time �����޼�����ȴʱ��").defineInRange("fire", 12000, 0, Integer.MAX_VALUE);
        THUNDER = COMMON_BUILDER.comment("Thunder Mythical Animals Cool Down Time �����޼�����ȴʱ��").defineInRange("thunder", 12000, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Battle ս��").push("battle");
        SHIELD = COMMON_BUILDER.comment("Shield Reflect Error(tick) �ܷ��������(tick)").defineInRange("shield", 5, 0, Integer.MAX_VALUE);
        DISPLAYTIME = COMMON_BUILDER.comment("Display React Time ��ʾ��Ӧʱ��").define("displaytime", true);
        LINKTIME = COMMON_BUILDER.comment("Max Link Time ����ֿ�ʱ��").defineInRange("linktime", 100, 0, Integer.MAX_VALUE);
        DELAY_ATTACK = COMMON_BUILDER.comment("Enable Delay Attack ������ʱ����������������ڹ���ʱ����ǰ���֣��������ڶܷ���������ò�����Ļ����Թر�").define("delay_attack", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Entity ����").push("entity");
        GUARDIAN = COMMON_BUILDER.comment("Guardian Spawn Chance �ػ������ɼ���").defineInRange("guardian", 1, 0, Double.POSITIVE_INFINITY);
        WALKING_GUARDIAN = COMMON_BUILDER.comment("Walking Guardian Spawn Chance ���ߵ��ػ������ɼ���").defineInRange("walking_guardian", 1, 0, Double.POSITIVE_INFINITY);
        MOLLYBRIN = COMMON_BUILDER.comment("Molly Brin Spawn Chance Ī���������ɼ���").defineInRange("mollybrin", 1, 0, Double.POSITIVE_INFINITY);
        POKBRIN= COMMON_BUILDER.comment("Pok Brin Spawn Chance ���˲������ɼ���").defineInRange("pokbrin", 1, 0, Double.POSITIVE_INFINITY);
        ROCKGIANT = COMMON_BUILDER.comment("Rock Giant Spawn Chance ��ʯ�������ɼ���").defineInRange("rockgiant", 1, 0, Double.POSITIVE_INFINITY);
        YIGATEAM = COMMON_BUILDER.comment("Yiga Team Member Spawn Chance ���Ƕӳ�Ա���ɼ���").defineInRange("yigateam", 1, 0, Double.POSITIVE_INFINITY);
        NPCONLY=COMMON_BUILDER.comment("Made Yiga Team Member NPC Only �����Ƕӳ�Ա����NPC������Ϊtrue���Ҽ����Ƕӳ�Ա���ٱ����һ������Զ���Ի�����ʹ�ã�").define("npc_only",false);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Item ��Ʒ").push("item");
        ATTRIBUTE = COMMON_BUILDER.comment("Enable Attribute Restraint �������Կ���").define("attribute", true);
        ELECTRICITY = COMMON_BUILDER.comment("Electricity Arrow Disarm Chance �����е����").defineInRange("electricity", 0.5, 0, 1);
        FIRE_ARROW = COMMON_BUILDER.comment("Enable Fire Arrow Burn Block ��������ȼ����").define("fire_arrow", true);
        BOMB_ARROW_DESTROY = COMMON_BUILDER.comment("Enable Bomb Arrow Destroy Block ����ը�����ƻ�����").define("bomb_arrow", true);
        KILLWITHER = COMMON_BUILDER.comment("Enable ancient arrow kill Wither which summoned by Chao Guardian. ����Ŵ�����ɱ���������ٻ��ĵ���").define("killwither", true);
        BOMB = COMMON_BUILDER.comment("Enable Bomb ����ʹ��ը��").define("bomb", true);
        BOMBDESTROY = COMMON_BUILDER.comment("Enable Bomb Destroy Block ����ը���ƻ�����").define("bombdestroy", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("World ����").push("world");
        WEATHER_CHANGE = COMMON_BUILDER.comment("Enable Mod Enfluence Weather ����ģ��Ӱ������").define("weatherchange", true);
        WEATHER_CHANGE_CHANCE = COMMON_BUILDER.comment("Weather Change Chance �����ı�Ƶ��").defineInRange("weatherchangechance", 1, 0, Double.POSITIVE_INFINITY);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Feature ����").push("feature");
        WINDBOMB = COMMON_BUILDER.comment("Enable Wind Bomb ����ʹ�÷絯").define("windbomb", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Others ����").push("others");
        CANDEATHINTEMPLE=COMMON_BUILDER.comment("Can Death In Temple ��һ�������������,�رպ�������������ܵ������˺�ʱ�ᱻǿ�ƴ��ͳ����������ֱ�����������Է�ֹ����������ڶ�ʧ��Ʒ��").define("can_death_in_temple", false);
        REDENVELOPE = COMMON_BUILDER.comment("Enable Red Envelope ����������").define("redenvelope", true);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
