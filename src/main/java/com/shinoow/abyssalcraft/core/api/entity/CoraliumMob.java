/**AbyssalCraft Core
 *Copyright 2012-2014 Shinoow
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package com.shinoow.abyssalcraft.core.api.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

/**
 * Bridge class for mobs that are immune to the Coralium Plague
 */
public class CoraliumMob extends EntityMob {

	/**
	 * Bridge class for mobs that are immune to the Coralium Plague
	 */
	public CoraliumMob(World par1World) {
		super(par1World);
		experienceValue = 10;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
}