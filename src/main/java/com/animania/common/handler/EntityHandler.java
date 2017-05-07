package com.animania.common.handler;

import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.helper.RegistryHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler {

	public static void preInit() {
		// COWS
		if (AnimaniaConfig.entity.spawnAnimaniaCows) {
			RegistryHelper.Entity.registerEntity(EntityCowHolstein.class, "animania.CowHolstein",
					AnimaniaConfig.entity.CowHolsteinID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityBullHolstein.class, "animania.BullHolstein",
					AnimaniaConfig.entity.BullHolsteinID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityCalfHolstein.class, "animania.CalfHolstein",
					AnimaniaConfig.entity.CalfHolsteinID, 64, 3, true);
			RegistryHelper.Entity.addSpawn(EntityCowHolstein.class, AnimaniaConfig.entity.spawnProbabilityCows, 1,
					AnimaniaConfig.entity.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

			RegistryHelper.Entity.registerEntity(EntityCowFriesian.class, "animania.CowFriesian",
					AnimaniaConfig.entity.CowFriesianID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityBullFriesian.class, "animania.BullFriesian",
					AnimaniaConfig.entity.BullFriesianID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityCalfFriesian.class, "animania.CalfFriesian",
					AnimaniaConfig.entity.CalfFriesianID, 64, 3, true);
			EntityRegistry.addSpawn(EntityCowFriesian.class, AnimaniaConfig.entity.spawnProbabilityCows, 1,
					AnimaniaConfig.entity.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS);

			RegistryHelper.Entity.registerEntity(EntityCowAngus.class, "animania.CowAngus",
					AnimaniaConfig.entity.CowAngusID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityBullAngus.class, "animania.BullAngus",
					AnimaniaConfig.entity.BullAngusID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityCalfAngus.class, "animania.CalfAngus",
					AnimaniaConfig.entity.CalfAngusID, 64, 3, true);
			EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.entity.spawnProbabilityCows, 1,
					AnimaniaConfig.entity.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.MESA,
					Biomes.MUSHROOM_ISLAND);

			RegistryHelper.Entity.registerEntity(EntityCowLonghorn.class, "animania.CowLonghorn",
					AnimaniaConfig.entity.CowLonghornID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityBullLonghorn.class, "animania.BullLonghorn",
					AnimaniaConfig.entity.BullLonghornID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityCalfLonghorn.class, "animania.CalfLonghorn",
					AnimaniaConfig.entity.CalfLonghornID, 64, 3, true);
			EntityRegistry.addSpawn(EntityCowLonghorn.class, AnimaniaConfig.entity.spawnProbabilityCows, 1,
					AnimaniaConfig.entity.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.SAVANNA);

			RegistryHelper.Entity.registerEntity(EntityCowHereford.class, "animania.CowHereford",
					AnimaniaConfig.entity.CowHerefordID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityBullHereford.class, "animania.BullHereford",
					AnimaniaConfig.entity.BullHerefordID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityCalfHereford.class, "animania.CalfHereford",
					AnimaniaConfig.entity.CalfHerefordID, 64, 3, true);
			EntityRegistry.addSpawn(EntityCowHereford.class, AnimaniaConfig.entity.spawnProbabilityCows, 1,
					AnimaniaConfig.entity.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.FOREST_HILLS);
		}

		// CHICKENS
		if (AnimaniaConfig.entity.spawnAnimaniaChickens) {
			RegistryHelper.Entity.registerEntity(EntityHenPlymouthRock.class, "animania.HenPlymouthRock",
					AnimaniaConfig.entity.HenPlymouthRockID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenPlymouthRock.class, AnimaniaConfig.entity.spawnProbabilityHens, 1,
					AnimaniaConfig.entity.numberChickenFamilies, EnumCreatureType.CREATURE,
					Biomes.EXTREME_HILLS_WITH_TREES, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE,
					Biomes.FOREST_HILLS);

			RegistryHelper.Entity.registerEntity(EntityRoosterPlymouthRock.class, "animania.RoosterPlymouthRock",
					AnimaniaConfig.entity.RoosterPlymouthRockID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityChickPlymouthRock.class, "animania.ChickPlymouthRock",
					AnimaniaConfig.entity.ChickPlymouthRockID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHenLeghorn.class, "animania.HenLeghorn",
					AnimaniaConfig.entity.HenLeghornID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenLeghorn.class, AnimaniaConfig.entity.spawnProbabilityHens, 1,
					AnimaniaConfig.entity.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS);

			RegistryHelper.Entity.registerEntity(EntityRoosterLeghorn.class, "animania.RoosterLeghorn",
					AnimaniaConfig.entity.RoosterLeghornID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityChickLeghorn.class, "animania.ChickLeghorn",
					AnimaniaConfig.entity.ChickLeghornID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHenOrpington.class, "animania.HenOrpington",
					AnimaniaConfig.entity.HenOrpingtonID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenOrpington.class, AnimaniaConfig.entity.spawnProbabilityHens, 1,
					AnimaniaConfig.entity.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE,
					Biomes.SWAMPLAND);

			RegistryHelper.Entity.registerEntity(EntityRoosterOrpington.class, "animania.RoosterOrpington",
					AnimaniaConfig.entity.RoosterOrpingtonID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityChickOrpington.class, "animania.ChickOrpington",
					AnimaniaConfig.entity.ChickOrpingtonID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHenWyandotte.class, "animania.HenWyandotte",
					AnimaniaConfig.entity.HenWyandotteID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenWyandotte.class, AnimaniaConfig.entity.spawnProbabilityHens, 1,
					AnimaniaConfig.entity.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

			RegistryHelper.Entity.registerEntity(EntityRoosterWyandotte.class, "animania.RoosterWyandotte",
					AnimaniaConfig.entity.RoosterWyandotteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityChickWyandotte.class, "animania.ChickWyandotte",
					AnimaniaConfig.entity.ChickWyandotteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHenRhodeIslandRed.class, "animania.HenRhodeIslandRed",
					AnimaniaConfig.entity.HenRhodeIslandRedID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, AnimaniaConfig.entity.spawnProbabilityHens, 1,
					AnimaniaConfig.entity.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

			RegistryHelper.Entity.registerEntity(EntityRoosterRhodeIslandRed.class, "animania.RoosterRhodeIslandRed",
					AnimaniaConfig.entity.RoosterRhodeIslandRedID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityChickRhodeIslandRed.class, "animania.ChickRhodeIslandRed",
					AnimaniaConfig.entity.ChickRhodeIslandRedID, 64, 3, true);
		}

		// PIGS
		if (AnimaniaConfig.entity.spawnAnimaniaPigs) {
			RegistryHelper.Entity.registerEntity(EntitySowYorkshire.class, "animania.SowYorkshire",
					AnimaniaConfig.entity.SowYorkshireID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogYorkshire.class, "animania.HogYorkshire",
					AnimaniaConfig.entity.HogYorkshireID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletYorkshire.class, "animania.PigletYorkshire",
					AnimaniaConfig.entity.PigletYorkshireID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowYorkshire.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS);

			RegistryHelper.Entity.registerEntity(EntitySowOldSpot.class, "animania.SowOldSpot",
					AnimaniaConfig.entity.SowOldSpotID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogOldSpot.class, "animania.HogOldSpot",
					AnimaniaConfig.entity.HogOldSpotID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletOldSpot.class, "animania.PigletOldSpot",
					AnimaniaConfig.entity.PigletOldSpotID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowOldSpot.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

			RegistryHelper.Entity.registerEntity(EntitySowLargeBlack.class, "animania.SowLargeBlack",
					AnimaniaConfig.entity.SowLargeBlackID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogLargeBlack.class, "animania.HogLargeBlack",
					AnimaniaConfig.entity.HogLargeBlackID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletLargeBlack.class, "animania.PigletLargeBlack",
					AnimaniaConfig.entity.PigletLargeBlackID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowLargeBlack.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.SWAMPLAND,
					Biomes.BIRCH_FOREST);

			RegistryHelper.Entity.registerEntity(EntitySowLargeWhite.class, "animania.SowLargeWhite",
					AnimaniaConfig.entity.SowLargeWhiteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogLargeWhite.class, "animania.HogLargeWhite",
					AnimaniaConfig.entity.HogLargeWhiteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletLargeWhite.class, "animania.PigletLargeWhite",
					AnimaniaConfig.entity.PigletLargeWhiteID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowLargeWhite.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.FOREST_HILLS,
					Biomes.BIRCH_FOREST_HILLS);

			RegistryHelper.Entity.registerEntity(EntitySowDuroc.class, "animania.SowDuroc",
					AnimaniaConfig.entity.SowDurocID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogDuroc.class, "animania.HogDuroc",
					AnimaniaConfig.entity.HogDurocID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletDuroc.class, "animania.PigletDuroc",
					AnimaniaConfig.entity.PigletDurocID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowDuroc.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE);

			RegistryHelper.Entity.registerEntity(EntitySowHampshire.class, "animania.SowHampshire",
					AnimaniaConfig.entity.SowHampshireID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityHogHampshire.class, "animania.HogHampshire",
					AnimaniaConfig.entity.HogHampshireID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPigletHampshire.class, "animania.PigletHampshire",
					AnimaniaConfig.entity.PigletHampshireID, 64, 3, true);
			EntityRegistry.addSpawn(EntitySowHampshire.class, AnimaniaConfig.entity.spawnProbabilitySows, 1,
					AnimaniaConfig.entity.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS,
					Biomes.EXTREME_HILLS_WITH_TREES);
		}

		// RODENTS
		if (AnimaniaConfig.entity.spawnAnimaniaRodents) {

			RegistryHelper.Entity.registerEntity(EntityHamster.class, "animania.Hamster",
					AnimaniaConfig.entity.HamsterMaleID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHamster.class, AnimaniaConfig.entity.spawnProbabilityHamsters, 1, 2,
					EnumCreatureType.CREATURE, Biomes.BEACH, Biomes.DESERT, Biomes.DESERT_HILLS);

			RegistryHelper.Entity.registerEntity(EntityFerretGrey.class, "animania.FerretGrey",
					AnimaniaConfig.entity.FerretMaleGreyID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityFerretWhite.class, "animania.FerretWhite",
					AnimaniaConfig.entity.FerretMaleWhiteID, 64, 3, true);

			EntityRegistry.addSpawn(EntityFerretGrey.class, AnimaniaConfig.entity.spawnProbabilityFerrets, 1, 2,
					EnumCreatureType.CREATURE, Biomes.SAVANNA);
			EntityRegistry.addSpawn(EntityFerretWhite.class, AnimaniaConfig.entity.spawnProbabilityFerrets, 1, 2,
					EnumCreatureType.CREATURE, Biomes.SAVANNA);

			RegistryHelper.Entity.registerEntity(EntityHedgehog.class, "animania.Hedgehog",
					AnimaniaConfig.entity.HedgehogMaleID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHedgehog.class, AnimaniaConfig.entity.spawnProbabilityHedgehogs, 1, 2,
					EnumCreatureType.CREATURE, Biomes.FOREST);

			RegistryHelper.Entity.registerEntity(EntityHedgehogAlbino.class, "animania.HedgehogAlbino",
					AnimaniaConfig.entity.HedgehogMaleAlbinoID, 64, 3, true);
			EntityRegistry.addSpawn(EntityHedgehogAlbino.class, AnimaniaConfig.entity.spawnProbabilityHedgehogs, 1, 2,
					EnumCreatureType.CREATURE, Biomes.SWAMPLAND);
		}

		// PEACOCKS
		if (AnimaniaConfig.entity.spawnAnimaniaPeacocks) {
			RegistryHelper.Entity.registerEntity(EntityPeacockBlue.class, "animania.PeacockBlue",
					AnimaniaConfig.entity.PeacockBlueID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPeafowlBlue.class, "animania.PeafowlBlue",
					AnimaniaConfig.entity.PeafowlBlueID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPeachickBlue.class, "animania.PeachickBlue",
					AnimaniaConfig.entity.PeachickBlueID, 64, 3, true);

			EntityRegistry.addSpawn(EntityPeacockBlue.class, AnimaniaConfig.entity.spawnProbabilityPeacocks, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
			EntityRegistry.addSpawn(EntityPeafowlBlue.class, AnimaniaConfig.entity.spawnProbabilityPeacocks, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
			EntityRegistry.addSpawn(EntityPeachickBlue.class, AnimaniaConfig.entity.spawnProbabilityPeacocks / 2, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);

			RegistryHelper.Entity.registerEntity(EntityPeacockWhite.class, "animania.PeacockWhite",
					AnimaniaConfig.entity.PeacockWhiteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPeafowlWhite.class, "animania.PeafowlWhite",
					AnimaniaConfig.entity.PeafowlWhiteID, 64, 3, true);

			RegistryHelper.Entity.registerEntity(EntityPeachickWhite.class, "animania.PeachickWhite",
					AnimaniaConfig.entity.PeachickWhiteID, 64, 3, true);

			EntityRegistry.addSpawn(EntityPeacockWhite.class, AnimaniaConfig.entity.spawnProbabilityPeacocks, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
			EntityRegistry.addSpawn(EntityPeafowlWhite.class, AnimaniaConfig.entity.spawnProbabilityPeacocks, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
			EntityRegistry.addSpawn(EntityPeachickWhite.class, AnimaniaConfig.entity.spawnProbabilityPeacocks / 2, 1, 1,
					EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		}

		/*
		 * //HORSES if (AnimaniaConfig.entity.spawnAnimaniaHorses) {
		 * EntityRegistry.registerModEntity(EntityStallionDraftHorse.class,
		 * "animania.StallionDraftHorse",
		 * AnimaniaConfig.entity.StallionDraftHorseID, this, 64, 3, true); }
		 */

		int entityID = 0;
		RegistryHelper.Entity.registerEntity(EntityFrogs.class, "animania.frog", entityID++, 64, 3, true, 0, 0);
		RegistryHelper.Entity.addSpawn(EntityFrogs.class, 20, 1, 3, EnumCreatureType.AMBIENT,
				RegistryHelper.Entity.getBiomes(BiomeDictionary.Type.SWAMP));
	}

}