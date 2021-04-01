package com.cse364;

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

    String name() {
        return name;
    }

    boolean equals(Genre genre) {
        return name == genre.name;
    }
}
