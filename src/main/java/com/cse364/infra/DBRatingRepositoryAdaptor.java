package com.cse364.infra;

import com.cse364.database.schemas.RatingSchema;
import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.RatingRepository;
import com.cse364.domain.User;
import java.util.List;
import java.util.stream.Collectors;

public class DBRatingRepositoryAdaptor implements RatingRepository  {
    DBRatingRepository ratings;

    public DBRatingRepositoryAdaptor(DBRatingRepository ratings) {
        this.ratings = ratings;
    }

    public List<Rating> filterByMovie(Movie movie) {
        return ratings.filterByMovie(movie.getId())
                .stream()
                .map(RatingSchema::toDomainObject)
                .collect(Collectors.toList());
    }

    public List<Rating> filterByUser(User user) {
        return ratings.filterByUser(user.getId())
                .stream()
                .map(RatingSchema::toDomainObject)
                .collect(Collectors.toList());
    }
}
