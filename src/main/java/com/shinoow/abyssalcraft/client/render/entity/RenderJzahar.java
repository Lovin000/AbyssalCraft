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
package com.shinoow.abyssalcraft.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

import com.shinoow.abyssalcraft.client.model.entity.ModelJzahar;
import com.shinoow.abyssalcraft.common.entity.EntityJzahar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJzahar extends RenderLiving {

	protected ModelJzahar model;

	private static final ResourceLocation mobTexture = new ResourceLocation("abyssalcraft:textures/model/boss/J'zahar.png");

	public RenderJzahar (ModelJzahar ModelJzahar, float f)
	{
		super(ModelJzahar, f);
		model = (ModelJzahar)mainModel;
	}

	public void doRender(EntityJzahar entity, double par2, double par4, double par6, float par8, float par9)
	{
		BossStatus.setBossStatus(entity, false);
		super.doRender(entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return mobTexture;
	}
}