package com.animania.common;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AnimaniaAchievements {

	public static AchievementPage page;

	public static Achievement Leghorn;
	public static Achievement Orpington;
	public static Achievement PlymouthRock;
	public static Achievement RhodeIslandRed;
	public static Achievement Wyandotte;
	public static Achievement Chickens;

	public static Achievement Angus;
	public static Achievement Friesian;
	public static Achievement Hereford;
	public static Achievement Holstein;
	public static Achievement Longhorn;
	public static Achievement Cows;

	public static Achievement IndiaBlue;
	public static Achievement White;
	public static Achievement Peacocks;

	public static Achievement Duroc;
	public static Achievement Hampshire;
	public static Achievement LargeBlack;
	public static Achievement LargeWhite;
	public static Achievement OldSpot;
	public static Achievement Yorkshire;
	public static Achievement Pigs;

	public static Achievement Hamsters;

	public static Achievement WhiteFerret;
	public static Achievement GreyFerret;
	public static Achievement Ferrets;

	public static Achievement Hedgehog;
	public static Achievement AlbinoHedgehog;
	public static Achievement Hedgehogs;

	public static Achievement Sonic;
	public static Achievement Sanic;

	public static void init() {

		// Register Achievements
		AnimaniaAchievements.Angus = new Achievement("angus", "an.angus", -2, -2, ItemHandler.entityeggcalfangus,
				(Achievement) null).registerStat();
		AnimaniaAchievements.Friesian = new Achievement("friesian", "an.friesian", 0, -2,
				ItemHandler.entityeggcalffriesian, (Achievement) null).registerStat();
		AnimaniaAchievements.Hereford = new Achievement("hereford", "an.hereford", 2, -2,
				ItemHandler.entityeggcalfhereford, (Achievement) null).registerStat();
		AnimaniaAchievements.Holstein = new Achievement("holstein", "an.holstein", 4, -2,
				ItemHandler.entityeggcalfholstein, (Achievement) null).registerStat();
		AnimaniaAchievements.Longhorn = new Achievement("longhorn", "an.longhorn", 6, -2,
				ItemHandler.entityeggcalflonghorn, (Achievement) null).registerStat();
		AnimaniaAchievements.Cows = new Achievement("cows", "an.cows", 8, -2, ItemHandler.milkBucketHolstein,
				AnimaniaAchievements.Longhorn).registerStat();

		AnimaniaAchievements.Leghorn = new Achievement("leghorn", "an.leghorn", -2, 0,
				ItemHandler.entityeggchickleghorn, (Achievement) null).registerStat();
		AnimaniaAchievements.Orpington = new Achievement("orpington", "an.orpington", 0, 0,
				ItemHandler.entityeggchickorpington, (Achievement) null).registerStat();
		AnimaniaAchievements.PlymouthRock = new Achievement("plymouthrock", "an.plymouthrock", 2, 0,
				ItemHandler.entityeggchickplymouth, (Achievement) null).registerStat();
		AnimaniaAchievements.RhodeIslandRed = new Achievement("rhodeislandred", "an.rhodeislandred", 4, 0,
				ItemHandler.entityeggchickred, (Achievement) null).registerStat();
		AnimaniaAchievements.Wyandotte = new Achievement("wyandotte", "an.wyandotte", 6, 0,
				ItemHandler.entityeggchickwyandotte, (Achievement) null).registerStat();
		AnimaniaAchievements.Chickens = new Achievement("chickens", "an.chickens", 8, 0, ItemHandler.brownEgg,
				AnimaniaAchievements.Wyandotte).registerStat();

		AnimaniaAchievements.Duroc = new Achievement("duroc", "an.duroc", -2, 2, ItemHandler.entityeggpigletduroc,
				(Achievement) null).registerStat();
		AnimaniaAchievements.Hampshire = new Achievement("hampshire", "an.hampshire", 0, 2,
				ItemHandler.entityeggpiglethampshire, (Achievement) null).registerStat();
		AnimaniaAchievements.LargeBlack = new Achievement("largeblack", "an.largeblack", 2, 2,
				ItemHandler.entityeggpigletlargeblack, (Achievement) null).registerStat();
		AnimaniaAchievements.LargeWhite = new Achievement("largewhite", "an.largewhite", 4, 2,
				ItemHandler.entityeggpigletlargewhite, (Achievement) null).registerStat();
		AnimaniaAchievements.OldSpot = new Achievement("oldspot", "an.oldspot", 6, 2,
				ItemHandler.entityeggpigletoldspot, (Achievement) null).registerStat();
		AnimaniaAchievements.Yorkshire = new Achievement("yorkshire", "an.yorkshire", 8, 2,
				ItemHandler.entityeggpigletyorkshire, (Achievement) null).registerStat();
		AnimaniaAchievements.Pigs = new Achievement("pigs", "an.pigs", 10, 2, ItemHandler.bucketSlop,
				AnimaniaAchievements.Yorkshire).registerStat();

		AnimaniaAchievements.IndiaBlue = new Achievement("indiablue", "an.indiablue", -2, 4,
				ItemHandler.entityeggpeachickblue, (Achievement) null).registerStat();
		AnimaniaAchievements.White = new Achievement("white", "an.white", 0, 4, ItemHandler.entityeggpeachickwhite,
				(Achievement) null).registerStat();
		AnimaniaAchievements.Peacocks = new Achievement("Peacocks", "an.peacocks", 2, 4,
				ItemHandler.entityeggpeachickblue, AnimaniaAchievements.White).registerStat();

		AnimaniaAchievements.WhiteFerret = new Achievement("whiteferret", "an.whiteferret", -2, 6,
				ItemHandler.entityeggferretwhite, (Achievement) null).registerStat();
		AnimaniaAchievements.GreyFerret = new Achievement("greyferret", "an.greyferret", 0, 6,
				ItemHandler.entityeggferretgrey, (Achievement) null).registerStat();
		AnimaniaAchievements.Ferrets = new Achievement("ferrets", "an.ferrets", 2, 6, ItemHandler.entityeggferretwhite,
				AnimaniaAchievements.GreyFerret).registerStat();

		AnimaniaAchievements.Hamsters = new Achievement("hamsters", "an.hamsters", -2, 8, ItemHandler.hamsterFood,
				(Achievement) null).registerStat();

		AnimaniaAchievements.Hedgehog = new Achievement("hedgehog", "an.hedgehog", -2, 10,
				ItemHandler.entityegghedgehog, (Achievement) null).registerStat();
		AnimaniaAchievements.AlbinoHedgehog = new Achievement("albinohedgehog", "an.albinohedgehog", 0, 10,
				ItemHandler.entityegghedgehogalbino, (Achievement) null).registerStat();
		AnimaniaAchievements.Hedgehogs = new Achievement("hedgehogs", "an.hedgehogs", 2, 10, Items.MUTTON,
				(Achievement) null).registerStat();

		AnimaniaAchievements.Sonic = new Achievement("sonic", "an.hedgehogsonic", 10, 10, ItemHandler.entityegghedgehog,
				(Achievement) null).registerStat().setSpecial();
		AnimaniaAchievements.Sanic = new Achievement("sanic", "an.hedgehogsanic", 12, 10, ItemHandler.entityegghedgehog,
				(Achievement) null).registerStat().setSpecial();

		AchievementPage.registerAchievementPage(AnimaniaAchievements.page = new AchievementPage("Animania",
				new Achievement[] { AnimaniaAchievements.Leghorn, AnimaniaAchievements.Orpington,
						AnimaniaAchievements.PlymouthRock, AnimaniaAchievements.RhodeIslandRed,
						AnimaniaAchievements.Wyandotte, AnimaniaAchievements.Chickens, AnimaniaAchievements.Angus,
						AnimaniaAchievements.Friesian, AnimaniaAchievements.Hereford, AnimaniaAchievements.Holstein,
						AnimaniaAchievements.Longhorn, AnimaniaAchievements.Cows, AnimaniaAchievements.Duroc,
						AnimaniaAchievements.Hampshire, AnimaniaAchievements.LargeBlack,
						AnimaniaAchievements.LargeWhite, AnimaniaAchievements.OldSpot, AnimaniaAchievements.Yorkshire,
						AnimaniaAchievements.Pigs, AnimaniaAchievements.IndiaBlue, AnimaniaAchievements.White,
						AnimaniaAchievements.Peacocks, AnimaniaAchievements.WhiteFerret,
						AnimaniaAchievements.GreyFerret, AnimaniaAchievements.Ferrets, AnimaniaAchievements.Hamsters,
						AnimaniaAchievements.Hedgehog, AnimaniaAchievements.AlbinoHedgehog,
						AnimaniaAchievements.Hedgehogs, AnimaniaAchievements.Sonic, AnimaniaAchievements.Sanic }));

	}

}
