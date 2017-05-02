package com.animania.render.chickens;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.entities.chickens.EntityHenPlymouthRock;
import com.animania.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.models.ModelHen;

@SideOnly(Side.CLIENT)
public class RenderHenPlymouthRock extends RenderLiving<EntityHenPlymouthRock>
{

	public RenderHenPlymouthRock(RenderManager rm)
	{
		super(rm, new ModelHen(), 0.3F);
	}

	protected float handleRotationFloat(EntityHenPlymouthRock livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
    protected void preRenderCallback(EntityHenPlymouthRock entityliving, float f)
    {
        preRenderScale((EntityHenPlymouthRock)entityliving, f);
    }

	protected void preRenderScale(EntityHenPlymouthRock entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F); 
        
        double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		BlockPos pos = new BlockPos(x, y, z);
		
		Block blockchk = entity.world.getBlockState(pos).getBlock();

		if (blockchk == Animania.blockNest) {
			GlStateManager.translate(-0.25F, 0.35F, -0.25F);
		}

		
    }
	
	
	@Override
	protected ResourceLocation getEntityTexture(EntityHenPlymouthRock entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

}
