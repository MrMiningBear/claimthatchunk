package com.miningbear.claimthatchunk;

import net.minecraftforge.common.MinecraftForge;

import com.miningbear.claimthatchunk.commands.CommandAddMember;
import com.miningbear.claimthatchunk.commands.CommandClaimLand;
import com.miningbear.claimthatchunk.commands.CommandListMembers;
import com.miningbear.claimthatchunk.commands.CommandRemoveMember;
import com.miningbear.claimthatchunk.commands.CommandUnclaimLand;
import com.miningbear.claimthatchunk.handlers.events.PlayerEventHandler;
import com.miningbear.claimthatchunk.handlers.events.WorldEventHandler;
import com.miningbear.claimthatchunk.lib.Constants;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Constants.modid, name = Constants.name, version = Constants.version, acceptableRemoteVersions = "*")
public class ClaimThatChunk {

	PlayerEventHandler playerEvents = new PlayerEventHandler();
	WorldEventHandler worldEvents = new WorldEventHandler();

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandClaimLand());
		event.registerServerCommand(new CommandUnclaimLand());
		event.registerServerCommand(new CommandAddMember());
		event.registerServerCommand(new CommandRemoveMember());
		event.registerServerCommand(new CommandListMembers());
		
		Constants.data.loadData();

		FMLCommonHandler.instance().bus().register(playerEvents);
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());

		FMLCommonHandler.instance().bus().register(worldEvents);
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
