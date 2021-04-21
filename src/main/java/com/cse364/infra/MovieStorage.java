package com.cse364.infra;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import com.cse364.domain.*;

public class MovieStorage {
    private HashMap<Integer, Movie> movies = new HashMap<>();

    public MovieStorage() { }

    /**
     * Adds a movie to the storage.
     */
    public void add(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    /**
     * Returns a Movie by id.
     * If there is no movie with given id, it returns `null`.
     */
    public Movie getMovie(Integer id) {
        return movies.get(id);
    }

    /**
     * Returns an unmodifiable hashmap of all movies.
     */
    public Map<Integer, Movie> allMovies() {
        return Collections.unmodifiableMap(movies);
    }   
}
