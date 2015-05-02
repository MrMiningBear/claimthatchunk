package com.miningbear.claimthatchunk.commands.user;

import java.util.ArrayList;
import java.util.List;

import com.miningbear.claimthatchunk.lib.Constants;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;

public class CommandChunkInfo extends CommandBase {
	private List aliases;

	public CommandChunkInfo() {
		this.aliases = new ArrayList();
		this.aliases.add("chunkinfo");
	}

	@Override
	public String getCommandName() {
		return "chunkinfo";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/chunkinfo";
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
        		
        		String owner = Constants.data.getChunkOwner( chunkX, chunkZ, player );
        		
        		if ( owner != null ) {
        			List<String> members = Constants.data.getMembers(chunkX, chunkZ, player);
        			String membersFormatted = "";
        			for (int i=0; i<members.size(); i++) {
        				membersFormatted = membersFormatted + ", " + members.get(i);
        			}
        			
        			if ( membersFormatted.length() > 0 ) {
        				membersFormatted = membersFormatted.substring(1, membersFormatted.length());
        			}
        			
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "== Chunk Information =="));
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + " Owner: " + owner));
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + " Members: " + EnumChatFormatting.YELLOW + membersFormatted));
        		} else {
        			icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "The chunk is not owned"));
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
}
