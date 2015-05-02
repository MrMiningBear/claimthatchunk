package com.miningbear.claimthatchunk.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

import com.miningbear.claimthatchunk.util.ChunkData;

public class DataHandler {
	private List<ChunkData> chunkData = new ArrayList<ChunkData>();
	private String reason = "";

	public boolean protectChunk(double chunkX, double chunkZ, EntityPlayer player) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				if (cd.getOwner() == player.getDisplayName()) {
					this.reason = "You already own this chunk!";
				} else {
					this.reason = "Someone else already owns this chunk!";
				}
				
				return false;
			}
		}
		
		ChunkData cd = new ChunkData();
		cd.setChunkX(chunkX);
		cd.setChunkZ(chunkZ);
		cd.setOwner(player.getDisplayName());
		cd.setDimension(player.dimension);
		this.chunkData.add(cd);
		
		return true;
	}

	public boolean unprotectChunk(double chunkX, double chunkZ, EntityPlayer player) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				if (cd.getOwner() == player.getDisplayName()) {
					this.chunkData.remove(i);
					return true;
				} else {
					this.reason = "You are not the owner of this chunk!";
					return false;
				}				
			}
		}
		
		this.reason = "This chunk isn't protected!";
		return false;
	}

	public boolean addMember(double chunkX, double chunkZ, EntityPlayer player, String playername) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				if (cd.getOwner() == player.getDisplayName() ) {
					if ( cd.addMember(playername) ) {
						this.chunkData.set(i, cd);
						return true;
					} else {
						this.reason = playername + " is already a member of this chunk!";
						return false;
					}
				} else {
					this.reason = "You are not the owner of this chunk!";
					return false;
				}
			}
		}
		
		this.reason = "This chunk isn't protected!";
		return false;
	}

	public boolean removeMember(double chunkX, double chunkZ, EntityPlayer player, String playername) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				if (cd.getOwner() == player.getDisplayName()) {
					if ( cd.removeMember(playername) ) {
						this.chunkData.set(i, cd);
						return true;
					} else {
						this.reason = playername + " isn't a member of this chunk!";
						return false;
					}
				} else {
					this.reason = "You are not the owner of this chunk!";
					return false;
				}
			}
		}
		
		this.reason = "This chunk isn't protected!";
		return false;
	}
	
	public List<String> getMembers(double chunkX, double chunkZ, EntityPlayer player) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				return cd.getMembersArray();
			}
		}
		
		this.reason = "This chunk isn't protected!";
		return null;
	}
		
	public void loadData() {
		File f = new File("data/protectedchunks.db");
		if ( f.exists() ) {
			try {
				FileInputStream f_in = new FileInputStream("data/protectedchunks.db");

				try {
					ObjectInputStream obj_in = new ObjectInputStream (f_in);

					try {
						Object obj = obj_in.readObject();
						
						this.chunkData = (List<ChunkData>)obj;
						
						obj_in.close();
						f_in.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveData() {
		File f = new File("data/");
		if ( !f.exists() ) {
			f.mkdir();
		}
		
		try {
			FileOutputStream f_out = new FileOutputStream("data/protectedchunks.db");
			try {
				ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
				
				obj_out.writeObject ( this.chunkData );

				obj_out.close();
				f_out.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean canBuild( double chunkX, double chunkZ, EntityPlayer player ) {
		for (int i=0; i<this.chunkData.size(); i++) {
			ChunkData cd = this.chunkData.get(i);
			
			if ( cd.getChunkX() == chunkX && cd.getChunkZ() == chunkZ && cd.getDimension() == player.dimension ) {
				if (cd.getOwner() == player.getDisplayName()) {
					return true;
				}
				else if (cd.isMember(player.getDisplayName()) != -1) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		//return false;
		return true;
	}
	
	public String getReason() {
		return this.reason;
	}
}
