package com.animania.common.handler;

import net.minecraft.util.DamageSource;

public class DamageSourceHandler {

	// DamageSource
	public static DamageSource bullDamage;

	public static void preInit() {
		// DAMAGE
		bullDamage = new DamageSource("bull").setDamageBypassesArmor();
	}
}
