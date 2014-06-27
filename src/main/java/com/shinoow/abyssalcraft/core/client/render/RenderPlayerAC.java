package com.shinoow.abyssalcraft.core.client.render;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.shinoow.abyssalcraft.core.client.model.ModelStarSpawnPlayer;
import com.shinoow.abyssalcraft.core.util.ItemList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlayerAC extends RenderPlayer {

	ModelStarSpawnPlayer model = new ModelStarSpawnPlayer(0.0F);

	public RenderPlayerAC()
	{
		super();
		this.mainModel = model;
		this.modelBipedMain = (ModelStarSpawnPlayer) this.mainModel;
	}

	public ModelBiped getModel()
	{
		return this.modelBipedMain;
	}

	@Override
	protected void renderEquippedItems(AbstractClientPlayer par1AbstractClientPlayer, float par2)
	{
		RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre(par1AbstractClientPlayer, this, par2);
		if (MinecraftForge.EVENT_BUS.post(event))
		{
			return;
		}

		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		super.renderEquippedItems(par1AbstractClientPlayer, par2);
		super.renderArrowsStuckInEntity(par1AbstractClientPlayer, par2);
		ItemStack itemstack = par1AbstractClientPlayer.inventory.armorItemInSlot(3);

		if (itemstack != null && event.renderHelmet)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			float f1;

			if (itemstack.getItem() instanceof ItemBlock)
			{
				IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
				boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

				if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
				{
					f1 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(f1, -f1, -f1);
				}

				this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack, 0);
			}
			else if (itemstack.getItem() == Items.skull)
			{
				f1 = 1.0625F;
				GL11.glScalef(f1, -f1, -f1);
				GameProfile gameprofile = null;

				if (itemstack.hasTagCompound())
				{
					NBTTagCompound nbttagcompound = itemstack.getTagCompound();

					if (nbttagcompound.hasKey("SkullOwner", 10))
					{
						gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
					}
					else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))
					{
						gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
					}
				}

				TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), gameprofile);
			}

			GL11.glPopMatrix();
		}

		float f3;

		if (par1AbstractClientPlayer.getCommandSenderName().equals("deadmau5") && par1AbstractClientPlayer.func_152123_o())
		{
			this.bindTexture(par1AbstractClientPlayer.getLocationSkin());

			for (int j = 0; j < 2; ++j)
			{
				GL11.glColor4f(1F, 1F, 1F, 1F);
				float f10 = par1AbstractClientPlayer.prevRotationYaw + (par1AbstractClientPlayer.rotationYaw - par1AbstractClientPlayer.prevRotationYaw) * par2 - (par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2);
				float f2 = par1AbstractClientPlayer.prevRotationPitch + (par1AbstractClientPlayer.rotationPitch - par1AbstractClientPlayer.prevRotationPitch) * par2;
				GL11.glPushMatrix();
				GL11.glRotatef(f10, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.375F * (float)(j * 2 - 1), 0.0F, 0.0F);
				GL11.glTranslatef(0.0F, -0.375F, 0.0F);
				GL11.glRotatef(-f2, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-f10, 0.0F, 1.0F, 0.0F);
				f3 = 1.3333334F;
				GL11.glScalef(f3, f3, f3);
				this.modelBipedMain.renderEars(0.0625F);
				GL11.glPopMatrix();
			}
		}
		if(par1AbstractClientPlayer.getCommandSenderName().equals("shinoow") || par1AbstractClientPlayer.getCommandSenderName().equals("Oblivionaire")) {

			this.bindTexture(new ResourceLocation("abyssalcraft:textures/model/tentacles.png"));

			for (int j = 0; j < 1; ++j) {
				GL11.glColor4f(1F, 1F, 1F, 1F);
				float f10 = par1AbstractClientPlayer.prevRotationYawHead + (par1AbstractClientPlayer.rotationYawHead - par1AbstractClientPlayer.prevRotationYawHead) * par2 - (par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2);
				float f2 = par1AbstractClientPlayer.prevRotationPitch + (par1AbstractClientPlayer.rotationPitch - par1AbstractClientPlayer.prevRotationPitch) * par2;
				GL11.glPushMatrix();
				GL11.glFrontFace(GL11.GL_CW);
				GL11.glRotatef(f10, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0, -0.22F, 0);
				GL11.glScalef(1, 1, 1);
				model.renderTentacles(0.0625F);
				GL11.glFrontFace(GL11.GL_CCW);
				GL11.glPopMatrix();
			}
		}

		boolean flag = par1AbstractClientPlayer.func_152122_n();
		flag = event.renderCape && flag;
		float f5;

		if (flag && !par1AbstractClientPlayer.isInvisible() && !par1AbstractClientPlayer.getHideCape())
		{
			this.bindTexture(par1AbstractClientPlayer.getLocationCape());
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
			double d3 = par1AbstractClientPlayer.field_71091_bM + (par1AbstractClientPlayer.field_71094_bP - par1AbstractClientPlayer.field_71091_bM) * (double)par2 - (par1AbstractClientPlayer.prevPosX + (par1AbstractClientPlayer.posX - par1AbstractClientPlayer.prevPosX) * (double)par2);
			double d4 = par1AbstractClientPlayer.field_71096_bN + (par1AbstractClientPlayer.field_71095_bQ - par1AbstractClientPlayer.field_71096_bN) * (double)par2 - (par1AbstractClientPlayer.prevPosY + (par1AbstractClientPlayer.posY - par1AbstractClientPlayer.prevPosY) * (double)par2);
			double d0 = par1AbstractClientPlayer.field_71097_bO + (par1AbstractClientPlayer.field_71085_bR - par1AbstractClientPlayer.field_71097_bO) * (double)par2 - (par1AbstractClientPlayer.prevPosZ + (par1AbstractClientPlayer.posZ - par1AbstractClientPlayer.prevPosZ) * (double)par2);
			f5 = par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2;
			double d1 = (double)MathHelper.sin(f5 * (float)Math.PI / 180.0F);
			double d2 = (double)(-MathHelper.cos(f5 * (float)Math.PI / 180.0F));
			float f6 = (float)d4 * 10.0F;

			if (f6 < -6.0F)
			{
				f6 = -6.0F;
			}

			if (f6 > 32.0F)
			{
				f6 = 32.0F;
			}

			float f7 = (float)(d3 * d1 + d0 * d2) * 100.0F;
			float f8 = (float)(d3 * d2 - d0 * d1) * 100.0F;

			if (f7 < 0.0F)
			{
				f7 = 0.0F;
			}

			float f9 = par1AbstractClientPlayer.prevCameraYaw + (par1AbstractClientPlayer.cameraYaw - par1AbstractClientPlayer.prevCameraYaw) * par2;
			f6 += MathHelper.sin((par1AbstractClientPlayer.prevDistanceWalkedModified + (par1AbstractClientPlayer.distanceWalkedModified - par1AbstractClientPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f9;

			if (par1AbstractClientPlayer.isSneaking())
			{
				f6 += 25.0F;
			}
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glRotatef(6.0F + f7 / 2.0F + f6, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f8 / 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-f8 / 2.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			this.modelBipedMain.renderCloak(0.0625F);
			GL11.glPopMatrix();
		}

		ItemStack itemstack1 = par1AbstractClientPlayer.inventory.getCurrentItem();

		if (itemstack1 != null && event.renderItem)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

			if (par1AbstractClientPlayer.fishEntity != null)
			{
				itemstack1 = new ItemStack(Items.stick);
			}

			EnumAction enumaction = null;

			if (par1AbstractClientPlayer.getItemInUseCount() > 0)
			{
				enumaction = itemstack1.getItemUseAction();
			}

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));

			if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType()))
			{
				f3 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				f3 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-f3, -f3, f3);
			}
			else if (itemstack1.getItem() == Items.bow || itemstack1.getItem() == ItemList.coralium_longbow)
			{
				f3 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f3, -f3, f3);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else if (itemstack1.getItem().isFull3D())
			{
				f3 = 0.625F;

				if (itemstack1.getItem().shouldRotateAroundWhenRendering())
				{
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				if (par1AbstractClientPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block)
				{
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(f3, -f3, f3);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else
			{
				f3 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f3, f3, f3);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			float f4;
			int k;
			float f12;

			if (itemstack1.getItem().requiresMultipleRenderPasses())
			{
				for (k = 0; k < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k)
				{
					int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
					f12 = (float)(i >> 16 & 255) / 255.0F;
					f4 = (float)(i >> 8 & 255) / 255.0F;
					f5 = (float)(i & 255) / 255.0F;
					GL11.glColor4f(f12, f4, f5, 1.0F);
					this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, k);
				}
			}
			else
			{
				k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
				float f11 = (float)(k >> 16 & 255) / 255.0F;
				f12 = (float)(k >> 8 & 255) / 255.0F;
				f4 = (float)(k & 255) / 255.0F;
				GL11.glColor4f(f11, f12, f4, 1.0F);
				this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, 0);
			}

			GL11.glPopMatrix();
		}
		MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Specials.Post(par1AbstractClientPlayer, this, par2));
	}
}
