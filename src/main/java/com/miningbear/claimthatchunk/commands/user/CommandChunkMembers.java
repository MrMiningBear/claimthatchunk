package com.miningbear.claimthatchunk.commands.user;

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

public class CommandChunkMembers extends CommandBase {
	private List aliases;
	
	public CommandChunkMembers() {
		this.aliases = new ArrayList();
		this.aliases.add("chunkmembers");
	}
	
	@Override
	public String getCommandName() {
		return "chunkmembers";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/chunkmembers";
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
        		
        		List<String> members = Constants.data.getMembers(chunkX, chunkZ, player);
        		
        		if ( members != null ) {
        			if ( members.size() > 0 ) {
        				icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Chunk Members"));
        				for ( int i=0; i<members.size(); i++ ) {
        					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "  " + i + ": " + members.get(i)));
        				}
        			} else {
        				icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Thre isn't any registered members for this chunk!"));
        			}
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
		return new ArrayList();
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