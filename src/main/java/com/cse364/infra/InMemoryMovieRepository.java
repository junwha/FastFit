package com.cse364.infra;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.cse364.domain.*;

public class InMemoryMovieRepository implements MovieRepository {
    private HashMap<Integer, Movie> movies = new HashMap<>();

    InMemoryMovieRepository() { }

    /**
     * Adds a movie to the storage.
     */
    void add(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie get(int id) {
        return movies.get(id);
    }

    public List<Movie> all() {
        return new ArrayList(movies.values());
    }   
}
