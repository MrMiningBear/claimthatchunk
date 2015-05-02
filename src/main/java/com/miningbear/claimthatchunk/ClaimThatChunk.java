package com.miningbear.claimthatchunk;

import java.io.IOException;

import net.minecraftforge.common.MinecraftForge;

import com.miningbear.claimthatchunk.commands.user.CommandAddMember;
import com.miningbear.claimthatchunk.commands.user.CommandChunkMembers;
import com.miningbear.claimthatchunk.commands.user.CommandChunkInfo;
import com.miningbear.claimthatchunk.commands.user.CommandClaimChunk;
import com.miningbear.claimthatchunk.commands.user.CommandRemoveMember;
import com.miningbear.claimthatchunk.commands.user.CommandUnclaimChunk;
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
	public void serverLoad(FMLServerStartingEvent event) throws ClassNotFoundException, IOException {
		event.registerServerCommand(new CommandClaimChunk());
		event.registerServerCommand(new CommandUnclaimChunk());
		event.registerServerCommand(new CommandAddMember());
		event.registerServerCommand(new CommandRemoveMember());
		event.registerServerCommand(new CommandChunkMembers());
		event.registerServerCommand(new CommandChunkInfo());
		
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
