package com.animania.config;

import net.minecraftforge.common.config.Config.Comment;

public class CommonConfig
{

	public static class Entity
	{

		// COWS
		@Comment("Holstein Cow Entity ID")
		public int CowHolsteinID = 500;

		@Comment("Holstein Bull Entity ID")
		public int BullHolsteinID = 501;

		@Comment("Holstein Calf Entity ID")
		public int CalfHolsteinID = 502;

		@Comment("Fresian Cow Entity ID")
		public int CowFriesianID = 503;

		@Comment("Fresian Bull Entity ID")
		public int BullFriesianID = 504;

		@Comment("Fresian Calf Entity ID")
		public int CalfFriesianID = 505;

		@Comment("Angus Cow Entity ID")
		public int CowAngusID = 506;

		@Comment("Angus Bull Entity ID")
		public int BullAngusID = 507;

		@Comment("Angus Calf Entity ID")
		public int CalfAngusID = 508;

		@Comment("Longhorn Cow Entity ID")
		public int CowLonghornID = 509;

		@Comment("Longhorn Bull Entity ID")
		public int BullLonghornID = 510;

		@Comment("Longhorn Calf Entity ID")
		public int CalfLonghornID = 511;

		@Comment("Hereford Cow Entity ID")
		public int CowHerefordID = 512;

		@Comment("Hereford Bull Entity ID")
		public int BullHerefordID = 513;

		@Comment("Hereford Calf Entity ID")
		public int CalfHerefordID = 514;

		// RODENTS
		@Comment("Hamster Male Entity ID")
		public int HamsterMaleID = 520;

		@Comment("Grey Ferret Male Entity ID")
		public int FerretMaleGreyID = 523;

		@Comment("White Ferret Male Entity ID")
		public int FerretMaleWhiteID = 526;

		@Comment("Hedgehog Male Entity ID")
		public int HedgehogMaleID = 529;

		@Comment("Albino Hedgehog Male Entity ID")
		public int HedgehogMaleAlbinoID = 550;

		// CHICKENS

		@Comment("Hen Plymouth Rock Entity ID")
		public int HenPlymouthRockID = 530;

		@Comment("Rooster Plymouth Rock Entity ID")
		public int RoosterPlymouthRockID = 531;

		@Comment("Chick Plymouth Rock Entity ID")
		public int ChickPlymouthRockID = 532;

		@Comment("Hen Leghorn Entity ID")
		public int HenLeghornID = 533;

		@Comment("Rooster Leghorn Entity ID")
		public int RoosterLeghornID = 534;

		@Comment("Chick Leghorn Entity ID")
		public int ChickLeghornID = 535;

		@Comment("Hen Orpington Entity ID")
		public int HenOrpingtonID = 536;

		@Comment("Rooster Orpington Entity ID")
		public int RoosterOrpingtonID = 537;

		@Comment("Chick Orpington Entity ID")
		public int ChickOrpingtonID = 538;

		@Comment("Hen Rode Island Red Entity ID")
		public int HenRhodeIslandRedID = 539;

		@Comment("Rooster Rode Island Red Entity ID")
		public int RoosterRhodeIslandRedID = 540;

		@Comment("Chick Rode Island Red Entity ID")
		public int ChickRhodeIslandRedID = 541;

		@Comment("Hen Wyandotte Entity ID")
		public int HenWyandotteID = 542;

		@Comment("Rooster Wyandotte Entity ID")
		public int RoosterWyandotteID = 543;

		@Comment("Chick Wyandotte Entity ID")
		public int ChickWyandotteID = 544;

		// PEAFOWL

		@Comment("Indian Blue Peacock Entity ID")
		public int PeacockBlueID = 580;

		@Comment("Indian Blue Peafowl Entity ID")
		public int PeafowlBlueID = 581;

		@Comment("Indian Blue Peachick Entity ID")
		public int PeachickBlueID = 582;

		@Comment("Indian White Peacock Entity ID")
		public int PeacockWhiteID = 583;

		@Comment("Indian White Peafowl Entity ID")
		public int PeafowlWhiteID = 584;

		@Comment("Indian White Peachick Entity ID")
		public int PeachickWhiteID = 585;

		// PIGS

		@Comment("Sow Yorkshire Entity ID")
		public int SowYorkshireID = 600;

		@Comment("Hog Yorkshire Entity ID")
		public int HogYorkshireID = 601;

		@Comment("Piglet Yorkshire Entity ID")
		public int PigletYorkshireID = 602;

		@Comment("Sow Old Spot Entity ID")
		public int SowOldSpotID = 603;

		@Comment("Hog Old Spot Entity ID")
		public int HogOldSpotID = 604;

		@Comment("Piglet Old Spot Entity ID")
		public int PigletOldSpotID = 605;

		@Comment("Sow Large Black Entity ID")
		public int SowLargeBlackID = 606;

		@Comment("Hog Large Black Entity ID")
		public int HogLargeBlackID = 607;

		@Comment("Piglet Large Black Entity ID")
		public int PigletLargeBlackID = 608;

		@Comment("Sow Large White Entity ID")
		public int SowLargeWhiteID = 609;

		@Comment("Hog Large Black Entity ID")
		public int HogLargeWhiteID = 610;

		@Comment("Piglet Large Black Entity ID")
		public int PigletLargeWhiteID = 611;

		@Comment("Sow Duroc Entity ID")
		public int SowDurocID = 612;

		@Comment("Hog Duroc Entity ID")
		public int HogDurocID = 613;

		@Comment("Piglet Duroc Entity ID")
		public int PigletDurocID = 614;

		@Comment("Sow Hampshire Entity ID")
		public int SowHampshireID = 615;

		@Comment("Hog Hampshire Entity ID")
		public int HogHampshireID = 616;

		@Comment("Piglet Hampshire Entity ID")
		public int PigletHampshireID = 617;

		// HORSES

		@Comment("Stallion Draft Horse Entity ID")
		public int MareDraftHorseID = 701;

		@Comment("Mare Draft Horse Entity ID")
		public int StallionDraftHorseID = 702;

		@Comment("Foal Draft Horse Entity ID")
		public int FoalDraftHorseID = 703;

	}

	public static class GameRules
	{

		@Comment("Foods give bonus effects")
		public boolean foodsGiveBonusEffects = true;

		@Comment("Show mod update notification at startup")
		public boolean showModUpdateNotification = true;

		@Comment("Show male parts (modesty flag)")
		public boolean showParts = true;

		@Comment("Show particles when animals are unhappy")
		public boolean showUnhappyParticles = true;

		@Comment("Enable recipes to exchange special meats for vanilla")
		public boolean enableVanillaMeatRecipes = false;

		@Comment("Remove vanilla Cows")
		public boolean replaceVanillaCows = true;

		@Comment("Remove vanilla Pigs")
		public boolean replaceVanillaPigs = true;

		@Comment("Remove vanilla Chickens")
		public boolean replaceVanillaChickens     = true;
		
		@Comment("Remove vanilla Rabbits")
		public boolean replaceVanillaRabbits     = true;
		
		@Comment("Remove vanilla Sheep")
		public boolean replaceVanillaSheep     = true;
		
		@Comment("Remove vanilla Horses")
		public boolean replaceVanillaHorses     = false;

		@Comment("Allow eggs to be thrown")
		public boolean allowEggThrowing = false;

		@Comment("Allow dispensers to place seeds")
		public boolean allowSeedDispenserPlacement = true;

		@Comment("Shift-Right-Click for Seed Placement")
		public boolean shiftSeedPlacement = false;

		@Comment("Animals starve to death when not fed and watered")
		public boolean animalsStarve = false;

		@Comment("Allow random mobs to spawn on Animania animals")
		public boolean allowMobRiding = true;

		@Comment("Allow the trough to be automated with hoppers/pipes")
		public boolean allowTroughAutomation = true;

		@Comment("Multiplier for reducing fall damage when animals are leashed")
		public float fallDamageReduceMultiplier = 0.45f;

		@Comment("Capacity of RF that the Hamster Wheel has")
		public int hamsterWheelCapacity = 200000;

		@Comment("RF/tick that the Hamster wheel generates while in use")
		public int hamsterWheelRFGeneration = 20;

		@Comment("Ticks that the hamster stays in the wheel before it needs more food")
		public int hamsterWheelUseTime = 900;

		@Comment("Ticks that a Cheese takes to mature")
		public int cheeseMaturityTime = 24000;

		@Comment("Food Items that can be placed in the trough")
		public String[] troughFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem", "minecraft:apple", "minecraft:carrot" , "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato"};
	
		@Comment("Water blocks removed after large animal drinks")
		public boolean waterRemovedAfterDrinking = true;
		
		@Comment("Plant blocks removed after animal eats")
		public boolean plantsRemovedAfterEating = true;
		
		@Comment("Animals do not have to eat or drink")
		public boolean ambianceMode = false;

		
	}

	public static class Spawn
	{
		@Comment("Spawn Animania Chickens in world")
		public boolean spawnAnimaniaChickens = true;
		@Comment("Spawn Animania Cows in world")
		public boolean spawnAnimaniaCows = true;
		@Comment("Spawn Animania Pigs in world")
		public boolean spawnAnimaniaPigs = true;
		@Comment("Spawn Animania Rodents in world")
		public boolean spawnAnimaniaRodents = true;
		@Comment("Spawn Animania Peacocks in world")
		public boolean spawnAnimaniaPeacocks = true;
		@Comment("Spawn Animania Horses in world")
		public boolean spawnAnimaniaHorses = true;
		@Comment("Spawn Animania Amphibians in world")
		public boolean spawnAnimaniaAmphibians = true;
		@Comment("Spawn Animania Goats in world")
		public boolean spawnAnimaniaGoats = true;
		@Comment("Spawn Animania Sheep in world")
		public boolean spawnAnimaniaSheep    = true;
		@Comment("Spawn Animania Rabbits in world")
		public boolean spawnAnimaniaRabbits    = true;

		@Comment("Spawn limit for Cows in loaded chunks")
		public int spawnLimitCows = 40;
		@Comment("Spawn limit for Pigs in loaded chunks")
		public int spawnLimitPigs = 40;
		@Comment("Spawn limit for Chickens in loaded chunks")
		public int spawnLimitChickens = 40;
		@Comment("Spawn limit for Hedgehogs in loaded chunks")
		public int spawnLimitHedgehogs = 20;
		@Comment("Spawn limit for Ferrets in loaded chunks")
		public int spawnLimitFerrets = 20;
		@Comment("Spawn limit for Hamsters in loaded chunks")
		public int spawnLimitHamsters = 24;
		@Comment("Spawn limit for Peacocks in loaded chunks")
		public int spawnLimitPeacocks = 24;
		@Comment("Spawn limit for Amphibians in loaded chunks")
		public int spawnLimitAmphibians = 24;
		@Comment("Spawn limit for Horses in loaded chunks")
		public int spawnLimitHorses = 30;
		@Comment("Spawn limit for Goats in loaded chunks")
		public int spawnLimitGoats = 40;
		@Comment("Spawn limit for Sheep in loaded chunks")
		public int spawnLimitSheep = 40;
		@Comment("Spawn limit for Rabbits in loaded chunks")
		public int spawnLimitRabbits = 36;

		@Comment("Spawn probability Cows")
		public int spawnProbabilityCows = 6;
		@Comment("Spawn probability Horses")
		public int spawnProbabilityHorses = 6;
		@Comment("Spawn probability Pigs")
		public int spawnProbabilitySows = 6;
		@Comment("Spawn probability Hens")
		public int spawnProbabilityHens = 6;
		@Comment("Spawn probability Hedgehogs")
		public int spawnProbabilityHedgehogs = 8;
		@Comment("Spawn probability Ferrets")
		public int spawnProbabilityFerrets = 8;
		@Comment("Spawn probability Hamsters")
		public int spawnProbabilityHamsters = 8;
		@Comment("Spawn probability Peacocks")
		public int spawnProbabilityPeacocks = 8;
		@Comment("Spawn probability Amphibians")
		public int spawnProbabilityAmphibians = 6;
		@Comment("Spawn probability Goats")
		public int spawnProbabilityGoats = 6;
		@Comment("Spawn probability Sheep")
		public int spawnProbabilitySheep = 6;
		@Comment("Spawn probability Rabbits")
		public int spawnProbabilityRabbits = 4;
		

		@Comment("Number of potential Cow families per chunk")
		public int numberCowFamilies = 1;
		@Comment("Number of potential Pig families per chunk")
		public int numberPigFamilies = 1;
		@Comment("Number of potential Chicken families per chunk")
		public int numberChickenFamilies = 2;
		@Comment("Number of potential Horse families per chunk")
		public int numberHorseFamilies = 1;
		@Comment("Number of potential Goat families per chunk")
		public int numberGoatFamilies = 1;
		@Comment("Number of potential Sheep families per chunk")
		public int numberSheepFamilies = 2;
		@Comment("Number of potential Rabbit families per chunk")
		public int numberRabbitFamilies = 1;

	}

	public static class CareAndFeeding
	{
		@Comment("Ticks before next incremental growth")
		public int childGrowthTick = 200;
		@Comment("Ticks between feedings")
		public int feedTimer = 12000;
		@Comment("Ticks between drinking water")
		public int waterTimer = 8000;
		@Comment("Ticks between playing")
		public int playTimer = 12000;
		@Comment("Ticks between laying eggs")
		public int laidTimer = 2000;
		@Comment("Ticks between dropping feathers")
		public int featherTimer = 12000;
		@Comment("Ticks between birthings")
		public int gestationTimer = 24000;
		@Comment("Hand feed animals to initiate breeding")
		public boolean manualBreeding = false;
		@Comment("Ticks before wool regrowth after shearing")
		public int woolRegrowthTimer = 8000;
		@Comment("Ticks between animals taking starvation damage")
		public int starvationTimer = 400;
		@Comment("Egg hatch chance (1/x)")
		public int eggHatchChance = 2;
		@Comment("Ticks between using Salt Lick")
		public int saltLickTick = 8000;
		@Comment("Maximum uses of the salt lick")
		public int saltLickMaxUses = 200;
		
		@Comment("Food Items that cows can eat")
		public String[] cowFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem"  };
	
		@Comment("Food Items that goats can eat")
		public String[] goatFood = { "minecraft:wheat", "minecraft:string", "minecraft:stick", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem"  };
	
		@Comment("Food Items that horses can eat")
		public String[] horseFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "minecraft:apple", "minecraft:carrot" };
	
		@Comment("Food Items that pigs can eat")
		public String[] pigFood = { "minecraft:carrot", "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato"};
	
		@Comment("Food Items that sheep can eat")
		public String[] sheepFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem"};
		
		@Comment("Food Items that rabbits can eat")
		public String[] rabbitFood = { "minecraft:wheat", "minecraft:carrot", "minecraft:beetroot", "minecraft:apple"};
	}

	public static class Drops
	{

		@Comment("Enable Animal Drops from Config")
		public boolean customMobDrops = true;
		@Comment("Enable Animals Drop their custom meat types")
		public boolean oldMeatDrops = false;
		@Comment("Set Custom Chicken Drop (if enabled)")
		public String chickenDrop = "animania:raw_prime_chicken";
		@Comment("Set Custom Pig Drop (if enabled)")
		public String pigDrop = "animania:raw_prime_pork";
		@Comment("Set Custom Cow Drop (if enabled)")
		public String cowDrop = "animania:raw_prime_beef";
		@Comment("Set Custom Horse Drop (if enabled)")
		public String horseDrop = "";
		@Comment("Set Custom Blue Peacock Drop (if enabled)")
		public String peacockBlueDrop = "animania:blue_peacock_feather";
		@Comment("Set Custom Charcoal Peacock Drop (if enabled)")
		public String peacockCharcoalDrop = "animania:charcoal_peacock_feather";
		@Comment("Set Custom Opal Peacock Drop (if enabled)")
		public String peacockOpalDrop = "animania:opal_peacock_feather";
		@Comment("Set Custom Peach Peacock Drop (if enabled)")
		public String peacockPeachDrop = "animania:peach_peacock_feather";
		@Comment("Set Custom Purple Peacock Drop (if enabled)")
		public String peacockPurpleDrop = "animania:purple_peacock_feather";
		@Comment("Set Custom Taupe Peacock Drop (if enabled)")
		public String peacockTaupeDrop = "animania:taupe_peacock_feather";
		@Comment("Set Custom White Peacock Drop (if enabled)")
		public String peacockWhiteDrop = "animania:white_peacock_feather";
		@Comment("Set Custom Ferret Drop (if enabled)")
		public String ferretDrop = "";
		@Comment("Set Custom Hamster Drop (if enabled)")
		public String hamsterDrop = "animania:hamster_food";
		@Comment("Set Custom Hedgehog Drop (if enabled)")
		public String hedgehogDrop = "";
		@Comment("Set Custom Frog Drop")
		public String frogDrop = "animania:raw_frog_legs";
		@Comment("Set Custom Toad Drop")
		public String toadDrop = "";
		@Comment("Set Custom Dart Frog Drop")
		public String dartFrogDrop = "";
		@Comment("Set Custom Goat Drop")
		public String goatDrop = "animania:raw_chevon";
		@Comment("Set Custom Sheep Drop")
		public String sheepDrop = "animania:raw_prime_mutton";
		@Comment("Set Custom Rabbit Drop")
		public String  rabbitDrop         = "animania:raw_prime_rabbit";
		@Comment("Allow Animania Chickens to drop Eggs")
		public Boolean chickensDropEggs = false;
		@Comment("Allow Animania Chickens to drop Feathers")
		public Boolean chickensDropFeathers = true;
	}

	public static class FoodValues
	{
		@Comment("Food Value Overrides")
		public String[] foodValueOverrides = new String[] {};
	}

}
