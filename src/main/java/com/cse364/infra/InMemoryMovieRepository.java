package com.cse364.infra;

import java.util.*;

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

    public Movie get(int id) {
        return movies.get(id);
    }

    public Movie get(String title) {
        for (Movie mov : movies.values()) {
            if (Objects.equals(title.toLowerCase().replaceAll("\\s", ""), mov.getTitle().toLowerCase().replaceAll("\\s", ""))) {
                return mov;
            }
        }
        
        return null;
    }

    public List<Movie> all() {
        return new ArrayList(movies.values());
    }   
}
