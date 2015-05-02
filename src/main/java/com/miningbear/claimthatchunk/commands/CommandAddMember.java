package com.miningbear.claimthatchunk.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;

import com.miningbear.claimthatchunk.lib.Constants;

public class CommandAddMember extends CommandBase {
	private List aliases;
	
	public CommandAddMember() {
		this.aliases = new ArrayList();
		this.aliases.add("addmember");
		this.aliases.add("addmem");
	}
	
	@Override
	public String getCommandName() {
		return "addmember";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/addmember <name>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if ( astring.length == 0 ) {
			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid arguments! Usage: /addmember <name>"));
			return;
		}
		
		EntityPlayer player;
        
        if(icommandsender instanceof EntityPlayer){
                player = (EntityPlayer)icommandsender;
                
                ChunkCoordinates coords = icommandsender.getPlayerCoordinates();
        		double chunkX = Math.floor(coords.posX / 16);
        		double chunkZ = Math.floor(coords.posZ / 16);
        
        		if ( Constants.data.addMember( chunkX, chunkZ, player, astring[0] ) ) {
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Successfully added " + astring[0] + " to the chunk!"));
        		} else {
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Constants.data.getReason()));
        		}
        }
        else {
        	icommandsender.addChatMessage(new ChatComponentText("This command can only be done via a player!"));
        }
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, this.getListOfPlayerUsernames()) : null;
	}
	
	protected String[] getListOfPlayerUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

	@Override
	public boolean isUsernameIndex(String[] astring, int id) {
		return false;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
}
