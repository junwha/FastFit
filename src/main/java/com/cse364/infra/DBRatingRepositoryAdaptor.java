package com.cse364.infra;

import com.cse364.database.repositories.DBRatingRepository;
import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.RatingRepository;
import com.cse364.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBRatingRepositoryAdaptor implements RatingRepository  {
    private HashMap<Integer, List<Rating>> userMap = new HashMap();
    private HashMap<Integer, List<Rating>> movieMap = new HashMap();

    @Autowired
    DBRatingRepository ratings;

    public DBRatingRepositoryAdaptor() {}

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
        return ratings.filterByMovie(movie);
    }

    public List<Rating> filterByUser(User user) {
        return ratings.filterByUser(user);
    }
}
