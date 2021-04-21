package com.cse364.infra;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.cse364.domain.*;

public class InMemoryRatingRepository implements RatingRepository  {
    private HashMap<Integer, ArrayList<Rating>> userMap;
    private HashMap<Integer, ArrayList<Rating>> movieMap;

    public InMemoryRatingRepository() {
        userMap = new HashMap<Integer, ArrayList<Rating>>();
        movieMap = new HashMap<Integer, ArrayList<Rating>>();
    }

    public void add(Rating rating){
        if (!userMap.containsKey(rating.getUser().getId())) {
            userMap.put(rating.getUser().getId(), new ArrayList<Rating>());
        }
        if (!movieMap.containsKey(rating.getMovie().getId())) {
            movieMap.put(rating.getMovie().getId(), new ArrayList<Rating>());
        }

        movieMap.get(rating.getMovie().getId()).add(rating);
        userMap.get(rating.getUser().getId()).add(rating);
    }

    public List<Rating> filterByMovie(Movie movie) {
        ArrayList<Rating> ratings = movieMap.get(movie.getId());
        if (ratings == null) { ratings = new ArrayList<>(); }
        return ratings;
    }

    public List<Rating> filterByUser(User user) {
        ArrayList<Rating> ratings = userMap.get(user.getId());
        if (ratings == null) { ratings = new ArrayList<>(); }
        return ratings;
    }
}
