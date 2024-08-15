package com.example.wrmusic_project;

public class Item {
    private final int imageResId;
    private final String title;
    private final String artist;
    private final String genre;
    private final String description;
    private final String price;
    private final int trackCount;

    public Item(int imageResId, String title, String artist, String genre, String description, String price, int trackCount) {
        this.imageResId = imageResId;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.trackCount = trackCount;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getTrackCount() {
        return trackCount;
    }
}
