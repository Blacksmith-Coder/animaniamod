package com.animania.client.render.cows;

import java.util.Random;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelCowHereford;
import com.animania.common.entities.cows.EntityCowHereford;

@SideOnly(Side.CLIENT)
public class RenderCowHereford extends RenderLiving
{
	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_hereford.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_hereford_blink.png");
	Random rand = new Random();

	public RenderCowHereford(RenderManager rm)
	{
		super(rm, new ModelCowHereford(), 0.5F);
	}

	protected void preRenderScale(EntityCowHereford entity, float f)
	{
		GL11.glScalef(1.34F, 1.34F, 1.34F); 

	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		preRenderScale((EntityCowHereford)entityliving, f);
	}

	protected ResourceLocation getCowTextures(EntityCowHereford par1EntityCow)
	{
		return cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(EntityCowHereford par1EntityCow)
	{
		return cowTexturesBlink;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityCowHereford entity = (EntityCowHereford)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}

}
