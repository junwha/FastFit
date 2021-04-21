package com.cse364.domain;

import java.util.List;

public interface RatingRepository {
    List<Rating> filterByMovie(Movie movie);
    List<Rating> filterByUser(User user);
}
