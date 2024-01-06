package com.raremoon.registration.deferred;

import com.mojang.serialization.Codec;
import com.raremoon.RareMoon;
import com.raremoon.listeners.FortuneMoonLootModifier;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class GlobalLootModifierSerializers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RareMoon.MOD_ID);

	public static void init() {
		GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(RareMoon.MOD_EVENT_BUS);
	}

	public static final RegistryObject<Codec<FortuneMoonLootModifier>> FORTUNE_MOON_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("blocks/fortune_moon_loot_modifier", FortuneMoonLootModifier.CODEC);
}