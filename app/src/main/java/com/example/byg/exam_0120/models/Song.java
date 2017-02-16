package com.example.byg.exam_0120.models;

/**
 * Created by byg on 2017-02-16.
 */

public class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Song{");
        sb.append("title='").append(title).append('\'');
        sb.append(", artist='").append(artist).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
