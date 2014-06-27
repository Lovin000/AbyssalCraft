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
package com.shinoow.abyssalcraft.core.api.addon;

public interface IACAddon {

	/**
	 * The add-on type
	 * @param DIMENSION Adds one or more dimensions
	 * @param STRUCTURE Adds generated structures
	 * @param MOB Adds mobs
	 * @param GENERATION Adds world generation (ores, trees etc)
	 */
	public static enum AddonType {
		DIMENSION, STRUCTURE, MOB, GENERATION
	}
}