package com.emmanuel.reproductor;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.lang.reflect.Field;

public class Song {
    private String name;
    private String author;
    private int songID;
    private int albumID;
    private MediaPlayer mediaPlayer;
    private Context context;

    public Song(String songID, String name, String author, String albumID, Context context) {
        this.name = name;
        this.author = author;
        this.context = context;

        Class songs = R.raw.class;
        Class albums = R.drawable.class;

        try {
            Field songField = songs.getField(songID);
            this.songID = songField.getInt(null);

            Field authorField = albums.getField(albumID);
            this.albumID = authorField.getInt(null);

            //mediaPlayer = MediaPlayer.create(context, this.songID);
        } catch (Exception e) {
            Log.e("Song", "Error al cargar song.", e);
        }
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getAlbumID() {
        return albumID;
    }

    public MediaPlayer getMediaPlayer() {
        mediaPlayer = MediaPlayer.create(context, this.songID);
        return mediaPlayer;
    }
}
