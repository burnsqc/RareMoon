package com.raremoon.registration.dynamic;

import com.raremoon.RareMoon;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public final class RareMoonDamageTypeTags {
	private RareMoonDamageTypeTags() {
	}

	public static final TagKey<DamageType> BLOOD_MOON_INCREASED = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(RareMoon.MOD_ID, "blood_moon_increased"));
}
