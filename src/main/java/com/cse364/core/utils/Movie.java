package com.cse364.core.utils;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private List<Genre> genres;
    private String link;

    public Movie(int id, String title, List<Genre> genres){
        this.id = id;
        this.title = title;
        this.genres = genres;
    }


    /**
     * Returns whether this movie has given genre.
     */
    public boolean hasGenre(Genre genre) {
        for (Genre g: genres) {
            if (g.equals(genre)) { return true; }
        }
        return false;
    }

    /**
     * Returns whether this movie has all given genres.
     */
    public boolean hasGenres(Genre[] genres) {
        for (Genre genre: genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }

    /**
     * Returns whether this movie has all given genres.
     */
    public boolean hasGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }

    // Setters
    public void setLink(String link) {
        this.link = link;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public List<Genre> getGenres() { return genres; }
    public String getLink() { return link; }
}
