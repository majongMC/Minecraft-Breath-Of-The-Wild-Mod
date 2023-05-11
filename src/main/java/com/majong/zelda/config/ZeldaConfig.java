package com.majong.zelda.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZeldaConfig {
	public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue WATER,WIND,FIRE,THUNDER;
    public static ForgeConfigSpec.IntValue SHIELD,LINKTIME;
    public static ForgeConfigSpec.BooleanValue DISPLAYTIME,SHIELD_COOLDOWN;
    public static ForgeConfigSpec.DoubleValue GUARDIAN,WALKING_GUARDIAN,MOLLYBRIN,BOKOBRIN,ROCKGIANT,YIGATEAM;
    public static ForgeConfigSpec.BooleanValue NPCONLY,ATTRIBUTE,FIRE_ARROW,BOMB_ARROW_DESTROY,KILLWITHER,BOMB,BOMBDESTROY,USEFUL_MAGNET,CANMOVEBE;
    public static ForgeConfigSpec.IntValue MAX_HEART;
    public static ForgeConfigSpec.DoubleValue ELECTRICITY;
    public static ForgeConfigSpec.BooleanValue WINDBOMB,REDENVELOPE,CANDEATHINTEMPLE;
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
        DISPLAYTIME = COMMON_BUILDER.comment("Display React Time 显示反应时间").define("displaytime", false);
        SHIELD_COOLDOWN=COMMON_BUILDER.comment("Enable Shield Cooldown 启用盾牌冷却").define("shield_cooldown", true);
        LINKTIME = COMMON_BUILDER.comment("Max Link Time 最大林克时间").defineInRange("linktime", 100, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Entity 生物").push("entity");
        GUARDIAN = COMMON_BUILDER.comment("Guardian Spawn Chance 守护者生成几率").defineInRange("guardian", 1, 0, Double.POSITIVE_INFINITY);
        WALKING_GUARDIAN = COMMON_BUILDER.comment("Walking Guardian Spawn Chance 行走的守护者生成几率").defineInRange("walking_guardian", 1, 0, Double.POSITIVE_INFINITY);
        MOLLYBRIN = COMMON_BUILDER.comment("Molly Brin Spawn Chance 莫利布林生成几率").defineInRange("mollybrin", 1, 0, Double.POSITIVE_INFINITY);
        BOKOBRIN= COMMON_BUILDER.comment("Boko Brin Spawn Chance 波克布林生成几率").defineInRange("bokobrin", 1, 0, Double.POSITIVE_INFINITY);
        ROCKGIANT = COMMON_BUILDER.comment("Rock Giant Spawn Chance 岩石巨人生成几率").defineInRange("rockgiant", 1, 0, Double.POSITIVE_INFINITY);
        YIGATEAM = COMMON_BUILDER.comment("Yiga Team Member Spawn Chance 依盖队成员生成几率").defineInRange("yigateam", 1, 0, Double.POSITIVE_INFINITY);
        NPCONLY=COMMON_BUILDER.comment("Made Yiga Team Member NPC Only 将依盖队成员用做NPC，设置为true后右键依盖队成员不再被激活（一般搭配自定义对话内容使用）").define("npc_only",false);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Item 物品").push("item");
        ATTRIBUTE = COMMON_BUILDER.comment("Enable Attribute Restraint 允许属性克制").define("attribute", true);
        ELECTRICITY = COMMON_BUILDER.comment("Electricity Arrow Disarm Chance 电箭缴械概率").defineInRange("electricity", 0.5, 0, 1);
        FIRE_ARROW = COMMON_BUILDER.comment("Enable Fire Arrow Burn Block 允许火箭点燃方块").define("fire_arrow", true);
        BOMB_ARROW_DESTROY = COMMON_BUILDER.comment("Enable Bomb Arrow Destroy Block 允许炸弹箭破坏方块").define("bomb_arrow", true);
        KILLWITHER = COMMON_BUILDER.comment("(由于龙之研究暂未更新到1.19版本，故该选项目前没有作用)Enable ancient arrow kill Wither which summoned by Chao Guardian. 允许古代箭秒杀混沌守卫召唤的凋灵").define("killwither", true);
        BOMB = COMMON_BUILDER.comment("Enable Bomb 允许使用炸弹").define("bomb", true);
        BOMBDESTROY = COMMON_BUILDER.comment("Enable Bomb Destroy Block 允许炸弹破坏方块").define("bombdestroy", true);
        USEFUL_MAGNET = COMMON_BUILDER.comment("Useful Magnet 更实用的磁铁，开启后希卡之石的磁铁会像之前的版本一样吸收掉落物，而不是还原原作").define("useful_magnet", false);
        CANMOVEBE = COMMON_BUILDER.comment("Can Move Block Entities 允许用磁力移动方块实体(移动方块实体会导致其数据丢失，请谨慎使用)").define("canmovebe", false);
        MAX_HEART = COMMON_BUILDER.comment("Max Heart Increase 最大心心增加量").defineInRange("max_heart", 10, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Feature 特性").push("feature");
        WINDBOMB = COMMON_BUILDER.comment("Enable Wind Bomb 允许使用风弹").define("windbomb", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Others 其他").push("others");
        CANDEATHINTEMPLE=COMMON_BUILDER.comment("Can Death In Temple 玩家会在神庙内死亡,关闭后当玩家在神庙内受到致命伤害时会被强制传送出神庙而不是直接死亡（可以防止玩家在神庙内丢失物品）").define("can_death_in_temple", false);
        REDENVELOPE = COMMON_BUILDER.comment("Enable Red Envelope 允许新年红包").define("redenvelope", true);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
