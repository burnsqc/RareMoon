package com.raremoon.registration.deferred;

import com.raremoon.RareMoon;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RareMoonSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.Keys.SOUND_EVENTS, RareMoon.MOD_ID);

	private RareMoonSoundEvents() {
	}

	public static void init() {
		SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<SoundEvent> BLOOD_MOON = SOUND_EVENTS.register("blood_moon", () -> new SoundEvent(new ResourceLocation(RareMoon.MOD_ID, "blood_moon")));
	public static final RegistryObject<SoundEvent> FORTUNE_MOON = SOUND_EVENTS.register("fortune_moon", () -> new SoundEvent(new ResourceLocation(RareMoon.MOD_ID, "fortune_moon")));
	public static final RegistryObject<SoundEvent> HARVEST_MOON = SOUND_EVENTS.register("harvest_moon", () -> new SoundEvent(new ResourceLocation(RareMoon.MOD_ID, "harvest_moon")));
	public static final RegistryObject<SoundEvent> BLUE_MOON = SOUND_EVENTS.register("blue_moon", () -> new SoundEvent(new ResourceLocation(RareMoon.MOD_ID, "blue_moon")));
}
