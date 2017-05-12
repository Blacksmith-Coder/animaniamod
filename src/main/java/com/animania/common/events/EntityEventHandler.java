package com.animania.common.events;

import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler
{

	@SubscribeEvent
	public void onEntityTakeDamage(LivingHurtEvent event)
	{
		float amount = event.getAmount();
		EntityLivingBase entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof EntityAnimal)
		{
			EntityAnimal animal = (EntityAnimal) entity;
			
			if (source == DamageSource.FALL)
			{
				if(animal.getLeashed())
				{
					event.setAmount(amount * AnimaniaConfig.gameRules.fallDamageReduceMultiplier);
				}
			}
			
			if (animal.isRiding()) {
				event.setCanceled(true);
			}
		}

	}

}
