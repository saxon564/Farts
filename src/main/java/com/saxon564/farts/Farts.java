package com.saxon564.farts;

import com.saxon564.farts.proxies.CommonProxyFarts;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FartsReference.MODID, name = FartsReference.MODNAME, version = FartsReference.VERSION)
public class Farts {
	@SidedProxy(clientSide = "com.saxon564.farts.client.ClientProxyFarts", serverSide = "com.saxon564.farts.proxies.CommonProxyFarts")
	public static CommonProxyFarts proxy;

	@Instance("farts")
	public static Farts instance;
	
	public static int tickDelay;
	public static boolean allowCreative;
	public static SoundEvent FART;
	public static SoundEvent WATER_FART;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		Configuration config = new Configuration(
	            event.getSuggestedConfigurationFile());
		tickDelay = config.get("Fart", "Seconds Between Farts", 5).getInt();
		allowCreative = config.get("Fart", "Allow Farts in Creative", false).getBoolean(false);
		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		RegisterSounds.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
