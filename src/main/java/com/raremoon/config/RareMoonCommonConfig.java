package com.raremoon.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
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
	public static final ConfigValue<Double> BLOOD_MOON_MULTIPLIER;
	public static final ConfigValue<Integer> FORTUNE_MOON_MULTIPLIER;
	public static final ConfigValue<Integer> HARVEST_MOON_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLUE_MOON_DURATION;
	public static final ForgeConfigSpec.ConfigValue<Integer> BLUE_MOON_COOLDOWN;

	private RareMoonCommonConfig() {
	}

	static {
		BUILDER.comment("RAREMOON COMMON CONFIG\n");
		BUILDER.comment("In single-player, these settings will affect only you. In multi-player, these settings will affect all players on the server.  Please consider your players when making changes.");
		BUILDER.comment("These settings have been set to defaults selected by the RareMoon development team.  It is recommended to make a backup of this file before modifying it.\n");

		BUILDER.push("RARITY");
		RARE_MOON_RARITY = BUILDER.comment("Percent chance of a rare moon appearance each night.").defineInRange("Rarity", 20, 0, 100);
		BLOOD_MOON_WEIGHT = BUILDER.comment("Likelihood of a blood moon relative to other rare moons.").defineInRange("Blood Moon Weight", 1, 0, 100);
		FORTUNE_MOON_WEIGHT = BUILDER.comment("Likelihood of a fortune moon relative to other rare moons.").defineInRange("Fortune Moon Weight", 1, 0, 100);
		HARVEST_MOON_WEIGHT = BUILDER.comment("Likelihood of a harvest moon relative to other rare moons.").defineInRange("Harvest Moon Weight", 1, 0, 100);
		BLUE_MOON_WEIGHT = BUILDER.comment("Likelihood of a blue moon relative to other rare moons.").defineInRange("Blue Moon Weight", 1, 0, 100);
		BUILDER.pop();

		BUILDER.push("EFFECTS");
		BLOOD_MOON_MULTIPLIER = BUILDER.comment("Damage multipliter during blood moon.").defineInRange("Blood Moon Damage Multiplier", 2.0F, 1.0F, 100.0F);
		FORTUNE_MOON_MULTIPLIER = BUILDER.comment("Loot multipliter during fortune moon.").defineInRange("Fortune Moon Loot Multiplier", 2, 1, 100);
		HARVEST_MOON_MULTIPLIER = BUILDER.comment("Loot multipliter during harvest moon.").defineInRange("Harvest Moon Loot Multiplier", 2, 1, 100);
		BLUE_MOON_DURATION = BUILDER.comment("Duration of random effects during blue moon in seconds.").defineInRange("Blue Moon Effect Duration", 15, 0, 16777215);
		BLUE_MOON_COOLDOWN = BUILDER.comment("Time between random effects during blue moon in seconds.").defineInRange("Blue Moon Effect Cooldown", 15, 0, 16777215);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
