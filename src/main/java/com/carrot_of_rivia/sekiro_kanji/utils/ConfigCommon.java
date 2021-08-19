package com.carrot_of_rivia.sekiro_kanji.utils;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigCommon {
    public static ForgeConfigSpec COMMON;

    public static ForgeConfigSpec.DoubleValue NOOB_CHANCE;
    public static ForgeConfigSpec.IntValue SHOW_DEATH_TIME;
    public static ForgeConfigSpec.BooleanValue DISABLE_DEATH_SCREEN;

    public static ForgeConfigSpec.IntValue DANGER_CD;
    public static ForgeConfigSpec.IntValue RESURRECT_CD;
    public static ForgeConfigSpec.IntValue POISON_CD;
    public static ForgeConfigSpec.IntValue BURN_CD;
    public static ForgeConfigSpec.IntValue ENEMY_ALERT_CD;

    public static ForgeConfigSpec.BooleanValue DISABLE_CREEPER_TRACK_DANGER_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_CREEPER_EXPLODE_DANGER_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_FALL_DANGER_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_ENDERMAN_TRACK_DANGER_SIGN;

    public static ForgeConfigSpec.BooleanValue DISABLE_BURN_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_POISON_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_RESURRECT_SIGN;
    public static ForgeConfigSpec.BooleanValue DISABLE_ENEMY_ALERT_SIGN;


    static {
        ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        CONFIG_BUILDER.push("death_screen");
        NOOB_CHANCE = CONFIG_BUILDER.comment("The chance of showing 'noob' instead of 'death' in the death screen. Note 'noob' will last 2.5 seconds longer than 'death' on your screen.").defineInRange("noob_chance", 0.1, 0, 1);
        SHOW_DEATH_TIME = CONFIG_BUILDER.comment("After this time, the 'DEATH' will disapear in your death screen. Unit: tick; 20 ticks = 1 second").defineInRange("show_death_time", 20, 0, Integer.MAX_VALUE);
        DISABLE_DEATH_SCREEN = CONFIG_BUILDER.define("disable_death_screen", false);
        CONFIG_BUILDER.pop();

        CONFIG_BUILDER.push("cool_down");
        DANGER_CD = CONFIG_BUILDER.comment("The cool down between entity sending/receiving danger sign. Unit: tick; 20 ticks = 1 second").defineInRange("danger_cd", 300, 0, Integer.MAX_VALUE);
        RESURRECT_CD = CONFIG_BUILDER.comment("The cool down between entity sending/receiving resurrect sign. Unit: tick; 20 ticks = 1 second").defineInRange("resurrect_cd", 300, 0, Integer.MAX_VALUE);
        POISON_CD = CONFIG_BUILDER.comment("The cool down between entity sending/receiving poison sign. Unit: tick; 20 ticks = 1 second").defineInRange("poison_cd", 300, 0, Integer.MAX_VALUE);
        BURN_CD = CONFIG_BUILDER.comment("The cool down between entity sending/receiving burn_ sign. Unit: tick; 20 ticks = 1 second").defineInRange("burn_cd", 300, 0, Integer.MAX_VALUE);
        ENEMY_ALERT_CD = CONFIG_BUILDER.comment("The cool down between entity sending/receiving enemy alert sign. Unit: tick; 20 ticks = 1 second").defineInRange("enemy_alert_cd", 900, 0, Integer.MAX_VALUE);
        CONFIG_BUILDER.pop();

        CONFIG_BUILDER.push("disable_signs");
        DISABLE_CREEPER_TRACK_DANGER_SIGN = CONFIG_BUILDER.comment("When a creeper starts to track you, you will not get danger alert.").define("disable_creeper_track_danger_sign", false);
        DISABLE_CREEPER_EXPLODE_DANGER_SIGN = CONFIG_BUILDER.comment("When a creeper starts to explode, you will not get danger alert.").define("disable_creeper_explode_danger_sign", false);
        DISABLE_FALL_DANGER_SIGN = CONFIG_BUILDER.comment("When you fall from a high place, you will not get danger alert.").define("disable_fall_danger_sign", false);
        DISABLE_ENDERMAN_TRACK_DANGER_SIGN = CONFIG_BUILDER.comment("When a enderman starts to track you, you will not get danger alert.").define("disable_enderman_track_danger_sign", false);

        DISABLE_BURN_SIGN = CONFIG_BUILDER.define("disable_burn_sign", false);
        DISABLE_POISON_SIGN = CONFIG_BUILDER.define("disable_poison_sign", false);
        DISABLE_RESURRECT_SIGN = CONFIG_BUILDER.define("disable_resurrect_sign", false);
        DISABLE_ENEMY_ALERT_SIGN = CONFIG_BUILDER.define("disable_enemy_alert_sign", false);
        CONFIG_BUILDER.pop();

        COMMON = CONFIG_BUILDER.build();
    }
}
