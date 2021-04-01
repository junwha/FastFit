package com.cse364;
import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    public int id;
    public String title;
    public Genre[] genres;
    public ArrayList<Rating> ratings;

    Movie(int id, String title, List<Genre> genres){
        this.id = id;
        this.title = title;
        this.genres = genres.toArray(Genre[]::new);
        ratings = new ArrayList<Rating>();
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
}

