package com.raremoon.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class RareMoonCommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<Integer> RARE_MOON_RARITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLOOD_MOON_WEIGHT;
	public static final ForgeConfigSpec.ConfigValue<Integer> FORTUNE_MOON_WEIGHT;
	public static final ForgeConfigSpec.ConfigValue<Integer> HARVEST_MOON_WEIGHT;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLUE_MOON_WEIGHT;
	public static final ForgeConfigSpec.ConfigValue<Double> BLOOD_MOON_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Integer> FORTUNE_MOON_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Integer> HARVEST_MOON_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLUE_MOON_DURATION;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLUE_MOON_COOLDOWN;

	private RareMoonCommonConfig() {
	}

	static {
		BUILDER.comment("RAREMOON COMMON CONFIG\n");
		BUILDER.comment("In single-player, these settings will affect only you. In multi-player, these settings will affect all players on the server.  Please consider your players when making changes.");
		BUILDER.comment("These settings have been set to defaults selected by the RareMoon development team.  It is recommended to make a backup of this file before modifying it.\n");

		BUILDER.push("RARITY");
		RARE_MOON_RARITY = BUILDER.comment("Percent chance of a rare moon appearance each night.").defineInRange("RARITY", 20, 0, 100);
		BUILDER.push("WEIGHT");
		BUILDER.comment("Likelihood of each moon type relative to other rare moon types.");
		BLOOD_MOON_WEIGHT = BUILDER.defineInRange("BLOOD_MOON", 1, 0, 100);
		FORTUNE_MOON_WEIGHT = BUILDER.defineInRange("FORTUNE_MOON", 1, 0, 100);
		HARVEST_MOON_WEIGHT = BUILDER.defineInRange("HARVEST_MOON", 1, 0, 100);
		BLUE_MOON_WEIGHT = BUILDER.defineInRange("BLUE_MOON", 1, 0, 100);
		BUILDER.pop();
		BUILDER.pop();

		BUILDER.push("EFFECTS");
		BUILDER.push("BLOOD_MOON");
		BLOOD_MOON_MULTIPLIER = BUILDER.comment("Damage multipliter during blood moon.").defineInRange("DAMAGE_MULTIPLIER", 2.0F, 1.0F, 100.0F);
		BUILDER.pop();
		BUILDER.push("FORTUNE_MOON");
		BUILDER.comment("The list of which loot will be multiplied during a Fortune Moon can be adjusted by editing data/raremoon/tags/items/fortune_moon_increased.json.");
		FORTUNE_MOON_MULTIPLIER = BUILDER.comment("Loot multipliter during fortune moon.").defineInRange("LOOT_MULTIPLIER", 2, 1, 100);
		BUILDER.pop();
		BUILDER.push("HARVEST_MOON");
		BUILDER.comment("The list of which loot will be multiplied during a Harvest Moon can be adjusted by editing data/raremoon/tags/items/harvest_moon_increased.json.");
		HARVEST_MOON_MULTIPLIER = BUILDER.comment("Loot multipliter during harvest moon.").defineInRange("LOOT_MULTIPLIER", 2, 1, 100);
		BUILDER.pop();
		BUILDER.push("BLUE_MOON");
		BUILDER.comment("The list of which effect may be applied during a Blue Moon can be adjusted by editing data/raremoon/tags/mob_effect/blue_moon_randomized.json.");
		BLUE_MOON_DURATION = BUILDER.comment("Duration of random effects during blue moon in seconds.").defineInRange("EFFECT_DURATION", 15, 0, 16777215);
		BLUE_MOON_COOLDOWN = BUILDER.comment("Time between random effects during blue moon in seconds.").defineInRange("EFFECT_COOLDOWN", 15, 0, 16777215);
		BUILDER.pop();
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
