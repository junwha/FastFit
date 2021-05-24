package com.cse364.domain;

import java.util.List;

public interface MovieRepository {
    /**
     * Returns a Movie by id.
     * If there is no movie with given id, it returns `null`.
     */
    Movie get(int id);

    /**
     * Returns a Movie by title.
     * if no movie with given name, return 'null'
     */
    Movie get(String title);

    /**
     * Returns a list of all movies.
     */
    List<Movie> all();

}
