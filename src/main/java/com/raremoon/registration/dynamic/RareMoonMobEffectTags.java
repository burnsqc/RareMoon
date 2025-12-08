package com.raremoon.registration.dynamic;

import com.raremoon.RareMoon;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public final class RareMoonMobEffectTags {
	private RareMoonMobEffectTags() {
	}

	public static final TagKey<MobEffect> BLUE_MOON_RANDOMIZED = TagKey.create(Registry.MOB_EFFECT_REGISTRY, new ResourceLocation(RareMoon.MOD_ID, "blue_moon_randomized"));
}
