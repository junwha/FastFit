package com.cse364.storage;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class RatingStorage {
    private HashMap<Integer, ArrayList<Rating>> userMap;
    private HashMap<Integer, ArrayList<Rating>> movieMap;

    RatingStorage(){
        userMap = new HashMap<Integer, ArrayList<Rating>>();
        movieMap = new HashMap<Integer, ArrayList<Rating>>();
    }

    public void add(Rating rating){
        if (!userMap.containsKey(rating.user.getId())) {
            userMap.put(rating.user.getId(), new ArrayList<Rating>());
        }
        if (!movieMap.containsKey(rating.movie.getId())) {
            movieMap.put(rating.movie.getId(), new ArrayList<Rating>());
        }

        movieMap.get(rating.movie.getId()).add(rating);
        userMap.get(rating.user.getId()).add(rating);
    }

    public List<Rating> getRatingsByMovie(Movie movie){
        ArrayList<Rating> ratings = movieMap.get(movie.getId());
        if (ratings == null) { ratings = new ArrayList<>(); }
        return ratings;
    }

    public List<Rating> getRatingsByUser(User user){
        ArrayList<Rating> ratings = userMap.get(user.getId());
        if (ratings == null) { ratings = new ArrayList<>(); }
        return ratings;
    }
}
