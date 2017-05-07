package com.animania.client.render.peacocks;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPeacock;
import com.animania.common.entities.peacocks.EntityPeacockBlue;


@SideOnly(Side.CLIENT)
public class RenderPeacockBlue extends RenderLiving<EntityPeacockBlue>
{

	public RenderPeacockBlue(RenderManager rm)
	{
		super(rm, new ModelPeacock(), 0.32F);
	}

	@Override
	protected float handleRotationFloat(EntityPeacockBlue livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityPeacockBlue entityliving, float f)
    {
        preRenderScale(entityliving, f);
    }

	protected void preRenderScale(EntityPeacockBlue entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityPeacockBlue entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

}
