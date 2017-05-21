package com.animania.client.render.amphibians;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelFrog;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityTreeFrogs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTreeFrogs<T extends EntityTreeFrogs> extends RenderLiving<T> {// RenderPlayer
	public static final Factory FACTORY = new Factory();

	// Need to move in main class
	private static final String modid = "animania", frogsBaseDir = "textures/entity/amphibians/treefrogs/";
	private static final ResourceLocation[] FROGS_TEXTURES = new ResourceLocation[] {
			new ResourceLocation(modid, frogsBaseDir + "red_tree_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "blue_tree_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "yellow_tree_frog.png") };

	public RenderTreeFrogs(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFrog(), 0.05F);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	@Override
	protected void preRenderCallback(T entityIn, float partialTickTime) {
		
		GlStateManager.scale(0.2D, 0.2D, 0.2D);
		float f1 = 1.2F;
		float f2 = (entityIn.prevSquishFactor + (entityIn.squishFactor -entityIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		switch (entity.getFrogsType()) {
		case 0:
		default:
			return FROGS_TEXTURES[0];
		case 1:
			return FROGS_TEXTURES[1];
		case 2:
			return FROGS_TEXTURES[2];
		
		}
	}

	public static class Factory<T extends EntityTreeFrogs> implements IRenderFactory<T> {
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderTreeFrogs(manager);
		}
	}
}
