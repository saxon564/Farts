package com.saxon564.farts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class RegisterSounds {
	private static int size = 0;
	
	public static void init(){
		size = SoundEvent.REGISTRY.getKeys().size();

		Farts.FART = register("farts");
		Farts.WATER_FART = register("water_farts");
	}
	
	public static SoundEvent register(String name){
		ResourceLocation location = new ResourceLocation(FartsReference.MODID, name);
		SoundEvent e = new SoundEvent(location);
		
		size++;
		return e;
	}
}