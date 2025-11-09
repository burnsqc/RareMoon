package com.raremoon.util;

import com.raremoon.RareMoon;
import com.raremoon.registration.deferred.RareMoonSoundEvents;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public enum MoonType {
	NORMAL(0, new ResourceLocation("textures/environment/moon_phases.png"), Component.translatable(""), Component.translatable("commands.moon.set.normal"), null, 0.0F, 0.0F, 0.0F), 
	BLOOD(1, new ResourceLocation(RareMoon.MOD_ID, "textures/environment/blood_moon_phases.png"), Component.translatable("raremoon.rise.blood").withStyle((style) -> style.withColor(0xFFAAAA)), Component.translatable("commands.moon.set.blood"), RareMoonSoundEvents.BLOOD_MOON.get(), 0.14F, 0.0F, 0.0F), 
	FORTUNE(2, new ResourceLocation(RareMoon.MOD_ID, "textures/environment/fortune_moon_phases.png"), Component.translatable("raremoon.rise.fortune").withStyle((style) -> style.withColor(0xFFFFAA)), Component.translatable("commands.moon.set.fortune"), RareMoonSoundEvents.FORTUNE_MOON.get(), 0.1F, 0.1F, 0.0F), 
	HARVEST(3, new ResourceLocation(RareMoon.MOD_ID, "textures/environment/harvest_moon_phases.png"), Component.translatable("raremoon.rise.harvest").withStyle((style) -> style.withColor(0xAAFFAA)), Component.translatable("commands.moon.set.harvest"), RareMoonSoundEvents.HARVEST_MOON.get(), 0.0F, 0.1F, 0.0F), 
	BLUE(4, new ResourceLocation(RareMoon.MOD_ID, "textures/environment/blue_moon_phases.png"), Component.translatable("raremoon.rise.blue").withStyle((style) -> style.withColor(0xAAAAFF)), Component.translatable("commands.moon.set.blue"), RareMoonSoundEvents.BLUE_MOON.get(), 0.0F, 0.0F, 0.15F);

	private int id;
	private ResourceLocation texture;
	private Component notificationMessage;
	private Component commandResponseMessage;
	private SoundEvent notificationSound;
	private float red;
	private float green;
	private float blue;

	MoonType(int id, ResourceLocation texture, Component notificationMessage, Component commandResponseMessage, SoundEvent notificationSound, float skyRed, float skyGreen, float skyBlue) {
		this.id = id;
		this.texture = texture;
		this.notificationMessage = notificationMessage;
		this.commandResponseMessage = commandResponseMessage;
		this.notificationSound = notificationSound;
		this.red = skyRed;
		this.green = skyGreen;
		this.blue = skyBlue;
	}

	public int getID() {
		return id;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	public Component getNotificationMessage() {
		return notificationMessage;
	}

	public Component getCommandResponseMessage() {
		return commandResponseMessage;
	}

	public SoundEvent getNotificationSound() {
		return notificationSound;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public static MoonType getMoonType(int id) {
		MoonType ret = MoonType.NORMAL;
		for (MoonType moonType : MoonType.values()) {
			if (moonType.id == id) {
				ret = moonType;
			}
		}
		return ret;
	}
}
