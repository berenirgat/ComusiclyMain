package com.comusicly.main;

import java.util.ArrayList;
public class Playlist {
    private String playlistName;
    private String pictureURL;
    private int playlistID;
    private int creatorID;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist (String playlistName, String pictureURL, int playlistID, int creatorID) {
        
        this.playlistName = playlistName;
        this.pictureURL = pictureURL;
        this.playlistID = playlistID;
        this.creatorID = creatorID;
    }
}