package com.animania.client.handler;

import com.animania.client.render.amphibians.RenderDartFrogs;
import com.animania.client.render.amphibians.RenderFrogs;
import com.animania.client.render.amphibians.RenderToad;
import com.animania.client.render.chickens.RenderChickLeghorn;
import com.animania.client.render.chickens.RenderChickOrpington;
import com.animania.client.render.chickens.RenderChickPlymouthRock;
import com.animania.client.render.chickens.RenderChickRhodeIslandRed;
import com.animania.client.render.chickens.RenderChickWyandotte;
import com.animania.client.render.chickens.RenderHenLeghorn;
import com.animania.client.render.chickens.RenderHenOrpington;
import com.animania.client.render.chickens.RenderHenPlymouthRock;
import com.animania.client.render.chickens.RenderHenRhodeIslandRed;
import com.animania.client.render.chickens.RenderHenWyandotte;
import com.animania.client.render.chickens.RenderRoosterLeghorn;
import com.animania.client.render.chickens.RenderRoosterOrpington;
import com.animania.client.render.chickens.RenderRoosterPlymouthRock;
import com.animania.client.render.chickens.RenderRoosterRhodeIslandRed;
import com.animania.client.render.chickens.RenderRoosterWyandotte;
import com.animania.client.render.cows.RenderBullAngus;
import com.animania.client.render.cows.RenderBullFriesian;
import com.animania.client.render.cows.RenderBullHereford;
import com.animania.client.render.cows.RenderBullHolstein;
import com.animania.client.render.cows.RenderBullLonghorn;
import com.animania.client.render.cows.RenderCalfAngus;
import com.animania.client.render.cows.RenderCalfFriesian;
import com.animania.client.render.cows.RenderCalfHereford;
import com.animania.client.render.cows.RenderCalfHolstein;
import com.animania.client.render.cows.RenderCalfLonghorn;
import com.animania.client.render.cows.RenderCowAngus;
import com.animania.client.render.cows.RenderCowFriesian;
import com.animania.client.render.cows.RenderCowHereford;
import com.animania.client.render.cows.RenderCowHolstein;
import com.animania.client.render.cows.RenderCowLonghorn;
import com.animania.client.render.goats.RenderBuckAlpine;
import com.animania.client.render.goats.RenderBuckAngora;
import com.animania.client.render.goats.RenderBuckFainting;
import com.animania.client.render.goats.RenderBuckKiko;
import com.animania.client.render.goats.RenderBuckKinder;
import com.animania.client.render.goats.RenderBuckNigerianDwarf;
import com.animania.client.render.goats.RenderBuckPygmy;
import com.animania.client.render.goats.RenderDoeAlpine;
import com.animania.client.render.goats.RenderDoeAngora;
import com.animania.client.render.goats.RenderDoeFainting;
import com.animania.client.render.goats.RenderDoeKiko;
import com.animania.client.render.goats.RenderDoeKinder;
import com.animania.client.render.goats.RenderDoeNigerianDwarf;
import com.animania.client.render.goats.RenderDoePygmy;
import com.animania.client.render.goats.RenderKidAlpine;
import com.animania.client.render.goats.RenderKidAngora;
import com.animania.client.render.goats.RenderKidFainting;
import com.animania.client.render.goats.RenderKidKiko;
import com.animania.client.render.goats.RenderKidKinder;
import com.animania.client.render.goats.RenderKidNigerianDwarf;
import com.animania.client.render.goats.RenderKidPygmy;
import com.animania.client.render.horses.RenderFoalDraftHorse;
import com.animania.client.render.horses.RenderMareDraftHorse;
import com.animania.client.render.horses.RenderStallionDraftHorse;
import com.animania.client.render.peacocks.RenderPeachickBlue;
import com.animania.client.render.peacocks.RenderPeachickCharcoal;
import com.animania.client.render.peacocks.RenderPeachickOpal;
import com.animania.client.render.peacocks.RenderPeachickPeach;
import com.animania.client.render.peacocks.RenderPeachickPurple;
import com.animania.client.render.peacocks.RenderPeachickTaupe;
import com.animania.client.render.peacocks.RenderPeachickWhite;
import com.animania.client.render.peacocks.RenderPeacockBlue;
import com.animania.client.render.peacocks.RenderPeacockCharcoal;
import com.animania.client.render.peacocks.RenderPeacockOpal;
import com.animania.client.render.peacocks.RenderPeacockPeach;
import com.animania.client.render.peacocks.RenderPeacockPurple;
import com.animania.client.render.peacocks.RenderPeacockTaupe;
import com.animania.client.render.peacocks.RenderPeacockWhite;
import com.animania.client.render.peacocks.RenderPeafowlBlue;
import com.animania.client.render.peacocks.RenderPeafowlCharcoal;
import com.animania.client.render.peacocks.RenderPeafowlOpal;
import com.animania.client.render.peacocks.RenderPeafowlPeach;
import com.animania.client.render.peacocks.RenderPeafowlPurple;
import com.animania.client.render.peacocks.RenderPeafowlTaupe;
import com.animania.client.render.peacocks.RenderPeafowlWhite;
import com.animania.client.render.pigs.RenderHogDuroc;
import com.animania.client.render.pigs.RenderHogHampshire;
import com.animania.client.render.pigs.RenderHogLargeBlack;
import com.animania.client.render.pigs.RenderHogLargeWhite;
import com.animania.client.render.pigs.RenderHogOldSpot;
import com.animania.client.render.pigs.RenderHogYorkshire;
import com.animania.client.render.pigs.RenderPigletDuroc;
import com.animania.client.render.pigs.RenderPigletHampshire;
import com.animania.client.render.pigs.RenderPigletLargeBlack;
import com.animania.client.render.pigs.RenderPigletLargeWhite;
import com.animania.client.render.pigs.RenderPigletOldSpot;
import com.animania.client.render.pigs.RenderPigletYorkshire;
import com.animania.client.render.pigs.RenderSowDuroc;
import com.animania.client.render.pigs.RenderSowHampshire;
import com.animania.client.render.pigs.RenderSowLargeBlack;
import com.animania.client.render.pigs.RenderSowLargeWhite;
import com.animania.client.render.pigs.RenderSowOldSpot;
import com.animania.client.render.pigs.RenderSowYorkshire;
import com.animania.client.render.rodents.RenderFerretGrey;
import com.animania.client.render.rodents.RenderFerretWhite;
import com.animania.client.render.rodents.RenderHamster;
import com.animania.client.render.rodents.RenderHedgehog;
import com.animania.client.render.rodents.RenderHedgehogAlbino;
import com.animania.client.render.tileEntity.TileEntityHamsterWheelRenderer;
import com.animania.client.render.tileEntity.TileEntityNestRenderer;
import com.animania.client.render.tileEntity.TileEntityTroughRenderer;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
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
import com.animania.common.entities.goats.EntityBuckAlpine;
import com.animania.common.entities.goats.EntityBuckAngora;
import com.animania.common.entities.goats.EntityBuckFainting;
import com.animania.common.entities.goats.EntityBuckKiko;
import com.animania.common.entities.goats.EntityBuckKinder;
import com.animania.common.entities.goats.EntityBuckNigerianDwarf;
import com.animania.common.entities.goats.EntityBuckPygmy;
import com.animania.common.entities.goats.EntityDoeAlpine;
import com.animania.common.entities.goats.EntityDoeAngora;
import com.animania.common.entities.goats.EntityDoeFainting;
import com.animania.common.entities.goats.EntityDoeKiko;
import com.animania.common.entities.goats.EntityDoeKinder;
import com.animania.common.entities.goats.EntityDoeNigerianDwarf;
import com.animania.common.entities.goats.EntityDoePygmy;
import com.animania.common.entities.goats.EntityKidAlpine;
import com.animania.common.entities.goats.EntityKidAngora;
import com.animania.common.entities.goats.EntityKidFainting;
import com.animania.common.entities.goats.EntityKidKiko;
import com.animania.common.entities.goats.EntityKidKinder;
import com.animania.common.entities.goats.EntityKidNigerianDwarf;
import com.animania.common.entities.goats.EntityKidPygmy;
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.EntityPeachickOpal;
import com.animania.common.entities.peacocks.EntityPeachickPeach;
import com.animania.common.entities.peacocks.EntityPeachickPurple;
import com.animania.common.entities.peacocks.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.EntityPeacockOpal;
import com.animania.common.entities.peacocks.EntityPeacockPeach;
import com.animania.common.entities.peacocks.EntityPeacockPurple;
import com.animania.common.entities.peacocks.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.EntityPeafowlTaupe;
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
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler
{

	public static void preInit() {
		renderEntitiesFactory();
	}

	public static void init() {
		renderTileEntity();
	}

	static void renderEntitiesFactory() {

		//Goats
		RenderingRegistry.registerEntityRenderingHandler(EntityKidAlpine.class, RenderKidAlpine.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckAlpine.class, RenderBuckAlpine.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeAlpine.class, RenderDoeAlpine.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidAngora.class, RenderKidAngora.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckAngora.class, RenderBuckAngora.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeAngora.class, RenderDoeAngora.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidFainting.class, RenderKidFainting.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckFainting.class, RenderBuckFainting.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeFainting.class, RenderDoeFainting.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidKiko.class, RenderKidKiko.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckKiko.class, RenderBuckKiko.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeKiko.class, RenderDoeKiko.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidKinder.class, RenderKidKinder.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckKinder.class, RenderBuckKinder.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeKinder.class, RenderDoeKinder.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidNigerianDwarf.class, RenderKidNigerianDwarf.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckNigerianDwarf.class, RenderBuckNigerianDwarf.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeNigerianDwarf.class, RenderDoeNigerianDwarf.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidPygmy.class, RenderKidPygmy.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckPygmy.class, RenderBuckPygmy.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoePygmy.class, RenderDoePygmy.FACTORY);


		// Frogs
		RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDartFrogs.class, RenderDartFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);

		// Chickens
		RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, RenderHenPlymouthRock.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class, RenderRoosterPlymouthRock.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class, RenderChickPlymouthRock.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, RenderHenLeghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, RenderRoosterLeghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickLeghorn.class, RenderChickLeghorn.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, RenderHenOrpington.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterOrpington.class, RenderRoosterOrpington.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickOrpington.class, RenderChickOrpington.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, RenderHenWyandotte.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterWyandotte.class, RenderRoosterWyandotte.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, RenderChickWyandotte.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class, RenderHenRhodeIslandRed.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterRhodeIslandRed.class, RenderRoosterRhodeIslandRed.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickRhodeIslandRed.class, RenderChickRhodeIslandRed.FACTORY);

		// Cows
		RenderingRegistry.registerEntityRenderingHandler(EntityCowHolstein.class, RenderCowHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHolstein.class, RenderBullHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHolstein.class, RenderCalfHolstein.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowFriesian.class, RenderCowFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullFriesian.class, RenderBullFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfFriesian.class, RenderCalfFriesian.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowAngus.class, RenderCowAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullAngus.class, RenderBullAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfAngus.class, RenderCalfAngus.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowLonghorn.class, RenderCowLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullLonghorn.class, RenderBullLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfLonghorn.class, RenderCalfLonghorn.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowHereford.class, RenderCowHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHereford.class, RenderBullHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHereford.class, RenderCalfHereford.FACTORY);

		// Pigs
		RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, RenderSowYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, RenderHogYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletYorkshire.class, RenderPigletYorkshire.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, RenderSowOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, RenderHogOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletOldSpot.class, RenderPigletOldSpot.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, RenderSowLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, RenderHogLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeBlack.class, RenderPigletLargeBlack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, RenderSowLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, RenderHogLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeWhite.class, RenderPigletLargeWhite.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, RenderSowDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, RenderHogDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletDuroc.class, RenderPigletDuroc.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, RenderSowHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, RenderHogHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletHampshire.class, RenderPigletHampshire.FACTORY);

		// Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, RenderHamster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, RenderHedgehog.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, RenderHedgehogAlbino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, RenderFerretGrey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, RenderFerretWhite.FACTORY);

		// Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, RenderPeacockBlue.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBlue.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, RenderPeachickBlue.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, RenderPeacockWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, RenderPeachickWhite.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockCharcoal.class, RenderPeacockCharcoal.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlCharcoal.class, RenderPeafowlCharcoal.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickCharcoal.class, RenderPeachickCharcoal.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockOpal.class, RenderPeacockOpal.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlOpal.class, RenderPeafowlOpal.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickOpal.class, RenderPeachickOpal.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPeach.class, RenderPeacockPeach.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPeach.class, RenderPeafowlPeach.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPeach.class, RenderPeachickPeach.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPurple.class, RenderPeacockPurple.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPurple.class, RenderPeafowlPurple.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPurple.class, RenderPeachickPurple.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockTaupe.class, RenderPeacockTaupe.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlTaupe.class, RenderPeafowlTaupe.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickTaupe.class, RenderPeachickTaupe.FACTORY);

		// Horses
		RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class, RenderStallionDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMareDraftHorse.class, RenderMareDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFoalDraftHorse.class, RenderFoalDraftHorse.FACTORY);



	}

	static void renderTileEntity() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, new TileEntityTroughRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileEntityNestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHamsterWheel.class, new TileEntityHamsterWheelRenderer());
	}
}
