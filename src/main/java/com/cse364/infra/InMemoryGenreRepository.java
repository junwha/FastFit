package com.cse364.infra;

import java.util.HashMap;
import java.util.List;
import com.cse364.domain.*;

public class InMemoryGenreRepository implements GenreRepository {
    // A map from normalized genre names (search names) to actual Genre objects.
    private HashMap<String, Genre> genres = new HashMap<>();

    InMemoryGenreRepository() { }

    /**
     * Initializes InMemoryGenreRpository with given genre names.
     */
    public InMemoryGenreRepository(List<String> genreNames) {
        for (String name: genreNames) {
            Genre genre = new Genre(name);
            genres.put(getSearchName(name), genre);
        }
    }

    public Genre searchByName(String name) {
        return genres.get(getSearchName(name));
    }

    /**
     * Returns a lowercased string of the given name,
     * with all special characters and whitespaces removed.
     * This method is for normalizing the Genre names for easier search.
     */
    private static String getSearchName(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
