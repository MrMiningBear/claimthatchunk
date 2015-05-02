package com.miningbear.claimthatchunk.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;

import com.miningbear.claimthatchunk.lib.Constants;

public class CommandUnclaimLand implements ICommand {
	private List aliases;

	public CommandUnclaimLand() {
		this.aliases = new ArrayList();
		this.aliases.add("unclaimland");
		this.aliases.add("ucl");
		this.aliases.add("uclaim");
	}
	
	@Override
	public String getCommandName() {
		return "unclaimland";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/unclaimland";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		EntityPlayer player;
        
        if(icommandsender instanceof EntityPlayer){
                player = (EntityPlayer)icommandsender;
                
                ChunkCoordinates coords = icommandsender.getPlayerCoordinates();
        		double chunkX = Math.floor(coords.posX / 16);
        		double chunkZ = Math.floor(coords.posZ / 16);
        		
        		if ( Constants.data.unprotectChunk( chunkX, chunkZ, player ) ) {
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Successfully unclaimed the chunk!"));
        		} else {
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Constants.data.getReason()));
        		}
        }
        else {
        	icommandsender.addChatMessage(new ChatComponentText("This command can only be done via a player!"));
        }		

        return;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		return new ArrayList();
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
