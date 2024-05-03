package com.comusicly.main;

import java.util.ArrayList;
import java.sql.*;

public class Playlist {
    private String name;
    private String pictureURL;
    private int ID;
    private int creatorID;
    private Connection connection;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist (String name, String pictureURL, int ID, int creatorID) throws SQLException {
        
        this.name = name;
        this.pictureURL = pictureURL;
        this.ID = ID;
        this.creatorID = creatorID;
    }

	public String getPlaylistName() {
		return name;
	}

	public void setPlaylistName(String playlistName) {
		this.name = playlistName;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public int getPlaylistID() {
		return ID;
	}

	public void setPlaylistID(int playlistID) {
		this.ID = playlistID;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	public Playlist createPlaylist(User currentUser) throws SQLException {
		String query = "INSERT INTO playlists (name, picture_url, creator_id) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
	        statement.setString(2, pictureURL);
	        statement.setInt(3, creatorID);
	        statement.executeUpdate();
	        resultSet = statement.getGeneratedKeys();
	        
	        if (resultSet.next()) {
	            int id = resultSet.getInt(1) + 1;
	            int currentUserID = currentUser.getId();
	            return new Playlist(name, pictureURL, id, currentUserID);
	        }
		}
		finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	    }
		return null;
	}
	
	public void deletePlaylist( int ID) throws SQLException {
		String query = "DELETE FROM playlists WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
        }	
	}
	
	public void addSong (int ID, int songID) throws SQLException {
		String query = "INSERT INTO playlist_songs (playlist_id, song_name, artist) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            statement.setInt(2, songID);
            statement.executeUpdate();
        } 
	}
	
	public void removeSong (int ID, int songID) throws SQLException{
		String query = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_name = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,ID);
            statement.setInt(2, songID);
            statement.executeUpdate();
        }
	}
	
	
	public ArrayList<Song> getSongsInPlaylist(int playlistId) throws SQLException {
	    ArrayList<Song> songs = new ArrayList<>();
	    String query = "SELECT * FROM playlist_songs WHERE playlist_id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, playlistId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int songId = resultSet.getInt("song_id");
	            Song song = getSongById(songId); 
	            if (song != null) {
	                songs.add(song);
	            }
	        }
	    }
	    return songs;
	}
	
	public Song getSongById(int songId) throws SQLException {
	    String query = "SELECT * FROM songs WHERE id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, songId);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int creatorId = resultSet.getInt("creatorid");
	            String songName = resultSet.getString("name");
	            String pictureURL = resultSet.getString("pictureurl");
	            String artist = resultSet.getString("artist");
	            return new Song(songId, creatorId, songName, pictureURL, artist);
	        }
	    }
	    return null; 
	}


}