package com.miningbear.claimthatchunk.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChunkData implements Serializable {
	private double chunkX;
	private double chunkZ;
	private int dimension;
	private String owner;
	private List<String> members = new ArrayList<String>();

	public void setChunkX(double chunkX) {
		this.chunkX = chunkX;
	}

	public double getChunkX() {
		return this.chunkX;
	}

	public void setChunkZ(double chunkZ) {
		this.chunkZ = chunkZ;
	}

	public double getChunkZ() {
		return this.chunkZ;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public int getDimension() {
		return this.dimension;
	}

	public void setOwner(String playername) {
		this.owner = playername;
	}

	public String getOwner() {
		return this.owner;
	}

	public boolean addMember(String playername) {
		int id = this.isMember(playername);

		if (id == -1) {
			this.members.add(playername);
			return true;
		}
		
		return false;
	}

	public boolean removeMember(String playername) {
		int id = this.isMember(playername);
		
		if (id != -1) {
			this.members.remove(id);
			return true;
		}
		
		return false;
	}
	
	public List<String> getMembersArray() {
		return this.members;
	}

	public int isMember(String playername) {
		for (int i=0; i<this.members.size(); i++) {
			if ( this.members.get(i).compareToIgnoreCase(playername) == 0 ) {
				return i;
			}
		}
		
		return -1;
	}
}
