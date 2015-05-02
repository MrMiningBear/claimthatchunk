package com.miningbear.claimthatchunk.handlers.events;

import com.miningbear.claimthatchunk.lib.Constants;

import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {
	
	@SubscribeEvent
	public void worldSaveEvent(WorldEvent.Save event) {
		if ( event.world.provider.dimensionId == 0 ) {
			Constants.data.saveData();
		}
	}
	
}
