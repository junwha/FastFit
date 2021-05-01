package com.cse364.domain;

import java.util.List;
import java.util.ArrayList;

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
    public boolean hasGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie)) { return false; }
        Movie movie = (Movie) o;
        return id.equals(movie.id);
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
