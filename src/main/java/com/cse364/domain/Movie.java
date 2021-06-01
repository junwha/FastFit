package com.cse364.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document(collection="movie")
@EqualsAndHashCode(of="id")
public class Movie {

    @Id
    int id;
    @NonNull String title;
    @DBRef
    @NonNull List<Genre> genres;
    @NonNull String link;

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
        for (Genre genre : genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }

    /**
     * Returns how much genres the two movies have in common.
     */
    public int numOfMatchingGenres(Movie movie) {
        int num = 0;
        for (Genre genre : this.genres) {
            if (movie.hasGenre(genre)) { num++; }
        }
        return num;
    }
}
