package com.comusicly.main;

import java.sql.*;


public class Song {
	private int ID;
	private int creatorID;
	private String name; 
	private String pictureURL;
	private String artist;
	private Connection connection; 
	
    public Song (int ID, int creatorID, String name, String pictureURL, String artist) {
    	this.ID = ID;
    	this.creatorID = ID;
    	this.name = name;
    	this.pictureURL = pictureURL;
    	this.artist = artist;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public Song getSongById(int songId) throws SQLException {
	    String query = "SELECT * FROM songs WHERE id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, songId);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int creatorId = resultSet.getInt("creator_id");
	            String songName = resultSet.getString("name");
	            String pictureURL = resultSet.getString("picture_url");
	            String artist = resultSet.getString("artist");
	            return new Song(songId, creatorId, songName, pictureURL, artist);
	        }
	    }
	    return null; // Return null if song with given ID is not found
	}


}
