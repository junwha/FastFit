package com.cse364.storage;

public class Genre {
    // Since each genre has no associated id, we use its display name as id.
    private String name;

    /**
     * WARNING: Don't use this constructor directly!
     * Please access Genre data using GenreStorage instead.
     */
    Genre(String name) {
        this.name = name;
    }

    boolean equals(Genre genre) {
        return name == genre.name;
    }

    // Getters
    public String getName() { return name; }
}
