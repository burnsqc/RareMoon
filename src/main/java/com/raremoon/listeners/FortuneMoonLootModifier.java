package com.raremoon.listeners;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.raremoon.RareMoon;
import com.raremoon.config.RareMoonCommonConfig;
import com.raremoon.registration.dynamic.RareMoonItemTags;
import com.raremoon.util.MoonType;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class FortuneMoonLootModifier extends LootModifier {
	private static final Marker LOOT_MODIFIER = MarkerManager.getMarker("LOOT_MODIFIER");
	public static final Supplier<Codec<FortuneMoonLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, FortuneMoonLootModifier::new)));

	public FortuneMoonLootModifier(final LootItemCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		boolean isOverworld = context.getLevel().dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD;
		boolean isFortuneMoon = RareMoonOverworldExtension.getData(context.getLevel().getServer()).getMoonType() == MoonType.FORTUNE;

		if (isOverworld && isFortuneMoon) {
			for (ItemStack loot : generatedLoot) {
				boolean isFortuneMoonIncreased = loot.is(RareMoonItemTags.FORTUNE_MOON_INCREASED);
				if (isFortuneMoonIncreased) {
					int countOrig = loot.getCount();
					loot.setCount(countOrig * RareMoonCommonConfig.FORTUNE_MOON_MULTIPLIER.get());
					RareMoon.LOGGER.trace(LOOT_MODIFIER, "Loot " + loot.getItem() + " count changed from " + countOrig + " to " + loot.getCount() + " due to Fortune Moon.");
				}
			}
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
