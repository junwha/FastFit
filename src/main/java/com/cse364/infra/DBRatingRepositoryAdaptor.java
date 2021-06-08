package com.cse364.infra;

import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.RatingRepository;
import com.cse364.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBRatingRepositoryAdaptor implements RatingRepository  {
    DBRatingRepository ratings;

    public DBRatingRepositoryAdaptor(DBRatingRepository ratings) {
        this.ratings = ratings;
    }

    public DBRatingRepositoryAdaptor(List<Rating> ratings){
        for(Rating rating: ratings){
            add(rating);
        }
    }

    /**
     * Adds a rating to the storage.
     */
    public void add(Rating rating){
        ratings.insert(rating);
    }

    public List<Rating> filterByMovie(Movie movie) {
        return ratings.filterByMovie(movie.getId());
    }

    public List<Rating> filterByUser(User user) {
        return ratings.filterByUser(user.getId());
    }
}
