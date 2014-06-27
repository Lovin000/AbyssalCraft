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
package com.shinoow.abyssalcraft.core;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.shinoow.abyssalcraft.core.common.CommonProxy;
import com.shinoow.abyssalcraft.core.handlers.CraftingHandler;
import com.shinoow.abyssalcraft.core.network.PacketPipeline;
import com.shinoow.abyssalcraft.core.util.CoreLogger;

import cpw.mods.fml.client.FMLFileResourcePack;
import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Core extends DummyModContainer {

	public static final String version = "1.1.0";
	public static final String modid = "accore";
	public static final String name = "AbyssalCraft Core";

	public Core() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId       = modid;
		meta.name        = name;
		meta.version     = version;
		meta.credits     = "shinoow (coding)";
		meta.authorList  = Arrays.asList("shinoow");
		meta.description = "Library used by AbyssalCraft mods - contains API as well";
		meta.url         = "http://adf.ly/FQarm";
		meta.updateUrl   = "http://adf.ly/FQarm";
		meta.screenshots = new String[0];
		meta.logoFile    = "/assets/abyssalcraft/textures/logo_core.png";
	}

	@SidedProxy(clientSide = "com.shinoow.abyssalcraft.core.client.ClientProxy",
			serverSide = "com.shinoow.abyssalcraft.core.common.CommonProxy")
	public static CommonProxy proxy;

	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {

		CoreLogger.info("Pre-initializing Core.");
		packetPipeline.initialise();
		FMLCommonHandler.instance().bus().register(new CraftingHandler());
		proxy.PreInit();
	}

	@Subscribe
	public void Init(FMLInitializationEvent event) {

		CoreLogger.info("Initializing Core.");
		proxy.Init();
		proxy.registerRenderThings();
	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent event) {

		CoreLogger.info("Post-initializing Core.");
		packetPipeline.postInitialise();
		proxy.PostInit();
		CoreLogger.info("Core loaded.");
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Override
	public File getSource() {
		return ACCorePlugin.coreLocation;
	}

	@Override
	public Class<?> getCustomResourcePackClass() {
		return getSource().isDirectory() ? FMLFolderResourcePack.class : FMLFileResourcePack.class;
	}

	@Override
	public List<String> getOwnedPackages() {
		return ImmutableList.of(
				"com.shinoow.abyssalcraft.core",
				"com.shinoow.abyssalcraft.core.api",
				"com.shinoow.abyssalcraft.core.api.addon",
				"com.shinoow.abyssalcraft.core.api.entity",
				"com.shinoow.abyssalcraft.core.api.network",
				"com.shinoow.abyssalcraft.core.api.render",
				"com.shinoow.abyssalcraft.core.client",
				"com.shinoow.abyssalcraft.core.client.model",
				"com.shinoow.abyssalcraft.core.client.render",
				"com.shinoow.abyssalcraft.core.common",
				"com.shinoow.abyssalcraft.core.handlers",
				"com.shinoow.abyssalcraft.core.network",
				"com.shinoow.abyssalcraft.core.network.packet",
				"com.shinoow.abyssalcraft.core.util"
				);
	}
}