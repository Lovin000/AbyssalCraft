/**AbyssalCraft
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
package com.shinoow.abyssalcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.shinoow.abyssalcraft.AbyssalCraft;

public class Darkstone extends Block
{
	public Darkstone()
	{
		super(Material.rock);
		setCreativeTab(AbyssalCraft.tabBlock);
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return AbyssalCraft.Darkstone_cobble.getItemDropped(0, random, j);
	}

}
