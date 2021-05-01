package com.cse364.domain;

import java.util.List;
import java.util.ArrayList;

public class Movie {
    private int id;
    private String title;
    private List<Genre> genres;
    private String link;

    public Movie(int id, String title, List<Genre> genres, String link){
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.link = link;
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
     * Returns whether this movie at least one of all given genres.
     */
    public boolean hasOneOfGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (hasGenre(genre)) { return true; }
        }
        return false;
    }

    /**
     * Returns whether this movie has all given genres.
     */
    public boolean hasAllGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie)) { return false; }
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public List<Genre> getGenres() { return genres; }
    public String getLink() { return link; }
}
