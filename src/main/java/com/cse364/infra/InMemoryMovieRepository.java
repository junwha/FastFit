package com.cse364.infra;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.cse364.domain.*;

public class InMemoryMovieRepository implements MovieRepository {
    private HashMap<Integer, Movie> movies = new HashMap<>();

    public InMemoryMovieRepository() { }

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
    public Movie get(int id) {
        return movies.get(id);
    }

    /**
     * Returns a list of all movies.
     */
    public List<Movie> all() {
        return new ArrayList(movies.values());
    }   
}
