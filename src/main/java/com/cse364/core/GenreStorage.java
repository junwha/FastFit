package com.cse364.core;

import java.util.HashMap;

public class GenreStorage {
    // A map from normalized genre names (search names) to actual Genre objects.
    private HashMap<String, Genre> genres = new HashMap<>();

    /**
     * Initializes GenreStorage.
     */
    GenreStorage(String[] genreNames) {
        for (String name: genreNames) {
            Genre genre = new Genre(name);
            genres.put(getSearchName(name), genre);
        }
    }

    /**
     * Returns a `Genre` corresponding to the given name.
     * The search is case-insensitive.
     * Also, special characters and whitespaces are ignored.
     * If the name is not a valid genre name, it returns `null`.
     */
    public Genre getGenre(String name) {
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
