package com.saxon564.farts.events;

import java.util.Random;

import com.saxon564.farts.Farts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class FartsEventHandler {
	
	private static int t=1;
	static int flatulence = 0;
	static boolean water = false;
	static Random rand = new Random();
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = (EntityPlayer) event.player;
		World world = player.world;
		
		if (world.isRemote && player.isSneaking() && !player.isSpectator() && (!player.capabilities.isCreativeMode || Farts.allowCreative)) {
			if (t <= 1) {
				t = com.saxon564.farts.Farts.tickDelay * 20;
				
				if (player.isInWater()) {
					world.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), Farts.FART, SoundCategory.PLAYERS,
	               1.0F, 0.25F);
					runParticles(world, player, true);
					water = true;
				} else {
					world.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), Farts.FART, SoundCategory.PLAYERS,
				               1.0F, 1F);
					runParticles(world, player, false);
				}
			}
		}
		
		if (water) {
			flatulence++;
		}
		
		//Play Bubbles
		if (flatulence >= 100) {
			world.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), Farts.WATER_FART, SoundCategory.PLAYERS,
		               1.0F, 1F);
			flatulence = 0;
			water = false;
		}
		
		if (world.isRemote && !player.isSneaking()) {
			if (t > 1) {
				t--;
			}
		}
	}
	
	public static void runParticles(World world, EntityPlayer player, boolean bubbles) {
		for (int i = 0; i < 20; i++) {
			float yaw = player.rotationYaw;
			Vec3d vec = player.getLook(yaw);
			double posX = ((double)player.posX - (vec.x * 0.25)) + (double)(rand.nextFloat() * player.width * 0.75F) - (player.width * 0.5);
			double posY = player.posY + 0.8D + (double)(rand.nextFloat() * (player.height / 2));
			double posZ = ((double)player.posZ  - (vec.z * 0.25)) + (double)(rand.nextFloat() * player.width * 0.75F) - (player.width * 0.5);
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX, posY, posZ, d0, d1, d2, new int[0]);
            for (int f = 0; f < 10; f++) {
            	world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX, posY, posZ, d0, d1, d2, new int[0]);
            }
		}
	}

}
