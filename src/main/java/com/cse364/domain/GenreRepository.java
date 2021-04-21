package com.cse364.domain;

import java.util.List;

public interface GenreRepository {
    /**
     * Returns a `Genre` corresponding to the given name.
     * The search is case-insensitive.
     * Also, special characters and whitespaces are ignored.
     * If the name is not a valid genre name, it returns `null`.
     */
    Genre searchByName(String name);
}
