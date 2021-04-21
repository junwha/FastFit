package com.cse364.domain;

public class Genre {
    // Since each genre has no associated id, we use its display name as id.
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public boolean equals(Genre genre) {
        return name == genre.name;
    }

    // Getters
    public String getName() { return name; }
}
