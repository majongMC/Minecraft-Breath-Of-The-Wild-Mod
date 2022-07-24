package com.majong.zelda.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZeldaConfig {
	public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue WATER,WIND,FIRE,THUNDER;
    public static ForgeConfigSpec.IntValue SHIELD,LINKTIME;
    public static ForgeConfigSpec.BooleanValue DISPLAYTIME;
    public static ForgeConfigSpec.DoubleValue GUARDIAN,WALKING_GUARDIAN,MOLLYBRIN,ROCKGIANT,BOKOBRIN,YIGATEAM;
    public static ForgeConfigSpec.BooleanValue ATTRIBUTE,FIRE_ARROW,BOMB_ARROW_DESTROY,KILLWITHER,BOMB,BOMBDESTROY;
    public static ForgeConfigSpec.DoubleValue ELECTRICITY;
    public static ForgeConfigSpec.BooleanValue WEATHER_CHANGE;
    public static ForgeConfigSpec.DoubleValue WEATHER_CHANGE_CHANCE;
    public static ForgeConfigSpec.BooleanValue WINDBOMB,REDENVELOPE;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Skill 技能").push("skill");
        WATER = COMMON_BUILDER.comment("Water Mythical Animals Cool Down Time 水神兽技能冷却时间").defineInRange("water", 12000, 0, Integer.MAX_VALUE);
        WIND = COMMON_BUILDER.comment("Wind Mythical Animals Cool Down Time 风神兽技能冷却时间").defineInRange("wind", 12000, 0, Integer.MAX_VALUE);
        FIRE = COMMON_BUILDER.comment("Fire Mythical Animals Cool Down Time 火神兽技能冷却时间").defineInRange("fire", 12000, 0, Integer.MAX_VALUE);
        THUNDER = COMMON_BUILDER.comment("Thunder Mythical Animals Cool Down Time 雷神兽技能冷却时间").defineInRange("thunder", 12000, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Battle 战斗").push("battle");
        SHIELD = COMMON_BUILDER.comment("Shield Reflect Error(tick) 盾反允许误差(tick)").defineInRange("shield", 5, 0, Integer.MAX_VALUE);
        DISPLAYTIME = COMMON_BUILDER.comment("Display React Time 显示反应时间").define("displaytime", true);
        LINKTIME = COMMON_BUILDER.comment("Max Link Time 最大林克时间").defineInRange("linktime", 100, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Entity 生物").push("entity");
        GUARDIAN = COMMON_BUILDER.comment("Guardian Spawn Chance 守护者生成几率").defineInRange("guardian", 1, 0, Double.POSITIVE_INFINITY);
        WALKING_GUARDIAN = COMMON_BUILDER.comment("Walking Guardian Spawn Chance 行走的守护者生成几率").defineInRange("walking_guardian", 1, 0, Double.POSITIVE_INFINITY);
        MOLLYBRIN = COMMON_BUILDER.comment("Molly Brin Spawn Chance 莫利布林生成几率").defineInRange("mollybrin", 1, 0, Double.POSITIVE_INFINITY);
        BOKOBRIN= COMMON_BUILDER.comment("Boko Brin Spawn Chance 波克布林生成几率").defineInRange("bokobrin", 1, 0, Double.POSITIVE_INFINITY);
        ROCKGIANT = COMMON_BUILDER.comment("Rock Giant Spawn Chance 岩石巨人生成几率").defineInRange("rockgiant", 1, 0, Double.POSITIVE_INFINITY);
        YIGATEAM = COMMON_BUILDER.comment("Yiga Team Member Spawn Chance 依盖队成员生成几率").defineInRange("yigateam", 1, 0, Double.POSITIVE_INFINITY);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Item 物品").push("item");
        ATTRIBUTE = COMMON_BUILDER.comment("Enable Attribute Restraint 允许属性克制").define("attribute", true);
        ELECTRICITY = COMMON_BUILDER.comment("Electricity Arrow Disarm Chance 电箭缴械概率").defineInRange("electricity", 0.5, 0, 1);
        FIRE_ARROW = COMMON_BUILDER.comment("Enable Fire Arrow Burn Block 允许火箭点燃方块").define("fire_arrow", true);
        BOMB_ARROW_DESTROY = COMMON_BUILDER.comment("Enable Bomb Arrow Destroy Block 允许炸弹箭破坏方块").define("bomb_arrow", true);
        KILLWITHER = COMMON_BUILDER.comment("(由于龙之研究暂未更新到1.19版本，故该选项目前没有作用)Enable ancient arrow kill Wither which summoned by Chao Guardian. 允许古代箭秒杀混沌守卫召唤的凋灵").define("killwither", true);
        BOMB = COMMON_BUILDER.comment("Enable Bomb 允许使用炸弹").define("bomb", true);
        BOMBDESTROY = COMMON_BUILDER.comment("Enable Bomb Destroy Block 允许炸弹破坏方块").define("bombdestroy", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("World 世界").push("world");
        WEATHER_CHANGE = COMMON_BUILDER.comment("Enable Mod Enfluence Weather 允许本模组影响天气").define("weatherchange", true);
        WEATHER_CHANGE_CHANCE = COMMON_BUILDER.comment("Weather Change Chance 天气改变频率").defineInRange("weatherchangechance", 1, 0, Double.POSITIVE_INFINITY);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Feature 特性").push("feature");
        WINDBOMB = COMMON_BUILDER.comment("Enable Wind Bomb 允许使用风弹").define("windbomb", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Others 其他").push("others");
        REDENVELOPE = COMMON_BUILDER.comment("Enable Red Envelope 允许新年红包").define("redenvelope", true);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
