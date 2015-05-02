package com.miningbear.claimthatchunk.handlers.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

import com.miningbear.claimthatchunk.lib.Constants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler {
	@SubscribeEvent
	public void bucketUse( FillBucketEvent event ) {
		EntityPlayer player = event.entityPlayer;
		Entity eevent = event.entity;
		double chunkX = Math.floor(eevent.posX/ 16);
		double chunkZ = Math.floor(eevent.posZ / 16);
		
		if ( !Constants.data.canBuild(chunkX, chunkZ, player) ) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void interactionEvent( PlayerInteractEvent event ) {
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			EntityPlayer player = event.entityPlayer;
			Entity eevent = event.entity;
			double chunkX = Math.floor(eevent.posX/ 16);
			double chunkZ = Math.floor(eevent.posZ / 16);
			
			if ( !Constants.data.canBuild(chunkX, chunkZ, player) ) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void hoeEvent( UseHoeEvent event ) {
		EntityPlayer player = event.entityPlayer;
		Entity eevent = event.entity;
		double chunkX = Math.floor(eevent.posX/ 16);
		double chunkZ = Math.floor(eevent.posZ / 16);
		
		if ( !Constants.data.canBuild(chunkX, chunkZ, player) ) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void blockPlace( PlaceEvent event ) {
		EntityPlayer player = event.player;
		double chunkX = Math.floor(event.x / 16);
		double chunkZ = Math.floor(event.z / 16);
		
		if ( !Constants.data.canBuild(chunkX, chunkZ, player) ) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void blockBreak( BreakEvent event ) {
		EntityPlayer player = event.getPlayer();
		double chunkX = Math.floor(event.x / 16);
		double chunkZ = Math.floor(event.z / 16);
		
		if ( !Constants.data.canBuild(chunkX, chunkZ, player) ) {
			event.setCanceled(true);
		}
		
		//if (!player.canCommandSenderUseCommand(2, "")) {
			//event.setCanceled(true);
		//}
	}
}
