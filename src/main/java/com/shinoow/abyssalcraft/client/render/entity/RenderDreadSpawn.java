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
import net.minecraft.util.ResourceLocation;

import com.shinoow.abyssalcraft.client.model.entity.ModelDreadSpawn;
import com.shinoow.abyssalcraft.client.model.entity.ModelDreadSpawn2;
import com.shinoow.abyssalcraft.client.model.entity.ModelDreadSpawn3;
import com.shinoow.abyssalcraft.common.entity.EntityDreadSpawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDreadSpawn extends RenderLiving {

	protected ModelDreadSpawn model;
	protected ModelDreadSpawn2 model2;
	protected ModelDreadSpawn3 model3;

	private static final ResourceLocation mobTexture = new ResourceLocation("abyssalcraft:textures/model/elite/Dread_guard.png");

	public RenderDreadSpawn()
	{
		super(new ModelDreadSpawn2(), 0.5F);
	}

	public void doRender(EntityDreadSpawn entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
//		switch(entity.getDreadMorph()){
//		case 0:
//			this.mainModel = model;
//			break;
//		case 1:
//			this.mainModel = model2;
//			break;
//		case 2:
//			this.mainModel = model3;
//		}
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		doRender((EntityDreadSpawn)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return mobTexture;
	}
}