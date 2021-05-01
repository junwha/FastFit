package com.cse364.domain;

public class Genre {
    // Since each genre has no associated id, we use its display name as id.
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Genre)) { return false; }
        Genre genre = (Genre) o;
        return name.equals(genre.name);
    }

    // Getters
    public String getName() { return name; }
}
