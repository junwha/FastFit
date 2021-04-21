package com.cse364.domain;

import java.util.List;

public interface RatingRepository {
    /**
     * Returns a list of ratings given to the specified movie.
     */
    List<Rating> filterByMovie(Movie movie);

    /**
     * Returns a list of ratings given by the specified user.
     */
    List<Rating> filterByUser(User user);
}
