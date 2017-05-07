package com.animania.client.render.rodents;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHedgehog;
import com.animania.common.entities.rodents.EntityHedgehog;


@SideOnly(Side.CLIENT)
public class RenderHedgehog extends RenderLiving<EntityHedgehog>
{
	private static final ResourceLocation HEDGEHOG_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog.png");
	private static final ResourceLocation HEDGEHOG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/hedgehog_blink.png");
	private static final ResourceLocation SONIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sonic.png");
	private static final ResourceLocation SANIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sanic.png");

	
	public RenderHedgehog(RenderManager rm)
	{
		super(rm, new ModelHedgehog(), 0.3F);
	}

	protected void preRenderScale(EntityHedgehog entity, float f)
	{
		GL11.glScalef(0.7F, 0.7F, 0.7F); 

		if (entity.getCustomNameTag().equals("Sanic")) {
			GL11.glRotatef(20, -1, 0, 1);
			GL11.glScalef(1.2F, 1.7F, 1.6F); 
		}

	}

	@Override
	protected void preRenderCallback(EntityHedgehog entityliving, float f)
	{
		preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHedgehog entity)
	{
		if (entity.getCustomNameTag().equals("Sonic")) {
			return SONIC_TEXTURES;
		} else if (entity.getCustomNameTag().equals("Sanic")) {
			return SANIC_TEXTURES;
		} else {
			int blinkTimer = entity.blinkTimer;
			if (blinkTimer < 5 && blinkTimer >= 0) {
				return HEDGEHOG_TEXTURES_BLINK;
			} else {
				return HEDGEHOG_TEXTURES;
			}
		}
	}

	@Override
	public ModelHedgehog getMainModel()
	{
		return (ModelHedgehog)super.getMainModel();
	}
}