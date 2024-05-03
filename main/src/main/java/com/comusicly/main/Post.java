package com.comusicly.main;

public class Post {
	
	private int id;
	private String text;
	private String imageUrl;
	private int songId;
	private int playlistId;
	private int userId;
	
	public Post(int id, String text, String imageUrl, int songId, int playlistId, int userId) {
		super();
		this.id = id;
		this.text = text;
		this.imageUrl = imageUrl;
		this.songId = songId;
		this.playlistId = playlistId;
		this.userId = userId;
	}
	
	public Post createTextPost() {
		
		//TODO: Add post to database with @imageUrl, @songId and @playlistId as null values
		
	}
	
	public Post createImagePost() {
		
		//TODO: Add post to database with @text, @songId and @playlistId as null values
		
	}
	
	public Post createSongPost() {
		
		//TODO: Add post to database with @imageUrl, @text and @playlistId as null values
		
	}
	
	public Post createPlaylistPost() {
		
		//TODO: Add post to database with @imageUrl, @songId and @text as null values
		
	}

	
	
	
}
